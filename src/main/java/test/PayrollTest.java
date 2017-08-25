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
import model.HourlyEmployee;
import model.Payment;
import model.SalesReceipt;
import model.ServiceCharge;
import model.TimeCard;

/**
 * Test for the whole payroll system (business calculations)
 * @author neeqstock
 *
 */
@RunWith(Arquillian.class)
public class PayrollTest {

	@Inject
	DatabaseCleaner databaseCleaner;
	@Inject
	bbb; // TODO
	
	@Before
	public void cleanDatabase(){
		databaseCleaner.clean();
	}
	
	@Test
	public void runPayrollTest(){
		
		boolean testFlatOk = false;
		boolean testHourlyOk = false;
		
		// Create employees
		FlatEmployee flatEmployee = new FlatEmployee("ImAFlatEmployee", "ImTheSurname", "ACity", ContractTypes.flat, PaymentMethods.bank, 1000, 50);
			flatEmployee.setInUnion(true);
			flatEmployee.setUnionDues(50);
			flatEmployee.setBankAccount("ABC123");
		HourlyEmployee hourlyEmployee = new HourlyEmployee("ImAHourlyEmployee", "ImASurname", "ImAnotherAddress", ContractTypes.hourly, PaymentMethods.mailed, 10);
		
		adminController.addEmployee(flatEmployee);
		adminController.addEmployee(hourlyEmployee);
		
		// Create a salesReceipt
		SalesReceipt salesReceipt = new SalesReceipt(flatEmployee, 100, new Date(2017, 7, 5), "RandomCompany");
		salesReceiptController.addSalesReceipt(salesReceipt);
		
		// Create a serviceCharge (for the flatEmployee)
		ServiceCharge serviceCharge = new ServiceCharge(flatEmployee, new Date(2017, 7, 10), 50);
		serviceChargeController.addServiceCharge(serviceCharge);
		
		// Create a timeCard (for the hourlyEmployee)
		TimeCard timeCard = new TimeCard(hourlyEmployee, new Date(2017, 7, 5), 5);
		timeCardController.addTimeCard(timeCard);
		
		// Run the payroll
		payrollController.runPayroll(new Date(2017, 8, 1));
		
		// Expected values
		float flatEmployeeExpectedPayment = 700;
		float hourlyEmployeePayment = 50;
		
		// See if salesReceipt has been added
		ArrayList<Payment> paymentListFlat = paymentController.getPaymentsOfEmployee(flatEmployee);
		if(paymentListFlat.get(0).getPaymentAmount() == flatEmployeeExpectedPayment){
			testFlatOk = true;
		}
		ArrayList<Payment> paymentListHourly = paymentController.getPaymentsOfEmployee(hourlyEmployee);
		if(paymentListHourly.get(0).getPaymentAmount() == hourlyEmployeeExpectedPayment){
			testHourlyOk = true;
		}

		Assert.assertTrue("payrollFlatTest - OK", testFlatOk);
		Assert.assertTrue("payrollHourlyTest - OK", testHourlyOk);
		
	}
}

