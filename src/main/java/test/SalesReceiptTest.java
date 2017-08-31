package test;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import constants.ContractTypes;
import constants.PaymentMethods;
import control.AdminControl;
import control.SalesReceiptControl;
import dao.DatabaseCleaner;
import junit.framework.Assert;
import model.FlatEmployee;
import model.SalesReceipt;

@RunWith(Arquillian.class)
public class SalesReceiptTest {

	@Inject
	DatabaseCleaner databaseCleaner;
	@Inject
	AdminControl adminControl;
	@Inject
	SalesReceiptControl salesReceiptControl;
	
	@Before
	public void cleanDatabase(){
		databaseCleaner.clean();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addSalesReceiptTest(){
		
		boolean testOk = false;
		
		// Values
		int amount = 100;
		Date receiptDate = new Date(2017, 7, 1);
		String company = "TestCompany";
		
		// Create employee
		FlatEmployee flatEmployee = new FlatEmployee(
				"Test",
				"Employee",
				"TestAddress",
				ContractTypes.flat,
				PaymentMethods.pickup,
				1000,
				50);
		adminControl.addFlatEmployee(flatEmployee);
		
		// Create salesReceipt with values
		SalesReceipt salesReceipt = new SalesReceipt(
				flatEmployee, 
				amount, 
				receiptDate, 
				company);
		salesReceiptControl.addSalesReceipt(salesReceipt);
		
		// See if salesReceipt has been added
		List<SalesReceipt> salesReceiptList = salesReceiptControl.getSalesReceiptsOfEmployee(flatEmployee);
		for(SalesReceipt receipt : salesReceiptList){
			if(receipt.getAmount() == amount && receipt.getReceiptDate().equals(receiptDate) && receipt.getCompany().equalsIgnoreCase(company)){
				testOk = true;
				break;
			}
		}
		Assert.assertTrue("addSalesReceiptTest - OK", testOk);
		
	}
}
