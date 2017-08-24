package test;

import java.sql.Date;
import java.util.ArrayList;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import constants.ContractTypes;
import constants.PaymentMethods;
import dao.DatabaseCleaner;
import junit.framework.Assert;
import model.FlatEmployee;
import model.SalesReceipt;
import model.ServiceCharge;
import model.TimeCard;

@RunWith(Arquillian.class)
public class ServiceChargeTest {

	@Inject
	DatabaseCleaner databaseCleaner;
	@Inject
	AdminController adminController;
	@Inject 
	ServiceChargeController serviceChargeController;
	
	@Before
	public void cleanDatabase(){
		databaseCleaner.clean();
	}
	
	@Test
	public void addServiceChargeTest(){
		
		boolean testOk = false;
		
		// Create employee before
				FlatEmployee flatEmployee = new FlatEmployee(
						"Test",
						"Employee",
						"TestAddress",
						ContractTypes.flat,
						PaymentMethods.pickup,
						1000,
						50);
						flatEmployee.setInUnion(true);
				adminController.addEmployee(flatEmployee);
		
		// Values
		Date chargeDate = new Date(2017, 7, 1);
		float amount = 30;
		
		// Create serviceCharge with values
		ServiceCharge serviceCharge = new ServiceCharge(flatEmployee, chargeDate, amount);

		serviceChargeController.addServiceCharge(serviceCharge);
		
		// See if salesReceipt has been added
		ArrayList<ServiceCharge> serviceChargeList = serviceChargeController.getServiceChargesOfEmployee(flatEmployee);
		for(ServiceCharge charge : serviceChargeList){
			if(charge.getChargeDate().equals(chargeDate) && charge.getAmount() == amount){
				testOk = true;
				break;
			}
		}
		Assert.assertTrue("addSalesReceiptTest - OK", testOk);
		
	}
}
