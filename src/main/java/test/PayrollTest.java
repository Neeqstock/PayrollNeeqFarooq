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
import control.PayrollControl;
import control.SalesReceiptControl;
import control.ServiceChargeControl;
import control.TimeCardControl;
import dao.DatabaseCleaner;
import junit.framework.Assert;
import model.FlatEmployee;
import model.HourlyEmployee;
import model.Payment;
import model.SalesReceipt;
import model.ServiceCharge;
import model.TimeCard;

/**
 * Test for the whole payroll system (business calculations).
 * 
 * @author neeqstock
 *
 */
@RunWith(Arquillian.class)
public class PayrollTest {

	@Inject
	DatabaseCleaner databaseCleaner;
	@Inject
	AdminControl adminControl;
	@Inject
	PayrollControl payrollControl;
	@Inject
	SalesReceiptControl salesReceiptControl;
	@Inject
	ServiceChargeControl serviceChargeControl;
	@Inject
	TimeCardControl timeCardControl;

	@Before
	public void cleanDatabase() {
		databaseCleaner.clean();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void runPayrollTest() {

		boolean testFlatOk = false;
		boolean testHourlyOk = false;

		// Create employees
		FlatEmployee flatEmployee = new FlatEmployee("ImAFlatEmployee", "ImTheSurname", "ACity", ContractTypes.flat,
				PaymentMethods.bank, 1000, 50);
		flatEmployee.setInUnion(true);
		flatEmployee.setUnionDues(50);
		flatEmployee.setBankAccount("ABC123");
		HourlyEmployee hourlyEmployee = new HourlyEmployee("ImAHourlyEmployee", "ImASurname", "ImAnotherAddress",
				ContractTypes.hourly, PaymentMethods.mailed, 10);

		adminControl.addFlatEmployee(flatEmployee);
		adminControl.addHourlyEmployee(hourlyEmployee);

		// Create a salesReceipt
		SalesReceipt salesReceipt = new SalesReceipt(flatEmployee, 100, new Date(2017, 7, 5), "RandomCompany");
		salesReceiptControl.addSalesReceipt(salesReceipt);

		// Create a serviceCharge (for the flatEmployee)
		ServiceCharge serviceCharge = new ServiceCharge(flatEmployee, new Date(2017, 7, 10), 50);
		serviceChargeControl.addServiceCharge(serviceCharge);

		// Create a timeCard (for the hourlyEmployee)
		TimeCard timeCard = new TimeCard(hourlyEmployee, new Date(2017, 7, 5), 5);
		timeCardControl.addTimeCard(timeCard);

		// Run the payroll
		payrollControl.runPayroll(new Date(2017, 8, 1));

		// Expected values
		float flatEmployeeExpectedPayment = 700;
		float hourlyEmployeeExpectedPayment = 50;

		// See if salesReceipt has been added
		List<Payment> paymentListFlat = payrollControl.getPayments(flatEmployee);
		if (paymentListFlat.get(0).getPaymentAmount() == flatEmployeeExpectedPayment) {
			testFlatOk = true;
		}
		List<Payment> paymentListHourly = payrollControl.getPayments(hourlyEmployee);
		if (paymentListHourly.get(0).getPaymentAmount() == hourlyEmployeeExpectedPayment) {
			testHourlyOk = true;
		}

		Assert.assertTrue("payrollFlatTest - OK", testFlatOk);
		Assert.assertTrue("payrollHourlyTest - OK", testHourlyOk);

	}
}
