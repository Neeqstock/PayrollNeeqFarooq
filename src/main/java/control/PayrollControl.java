package control;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import constants.HourlyEmployeeConstants;
import constants.PaymentMethods;
import dao.EmployeeDAO;
import dao.PaymentDAO;
import dao.SalesReceiptDAO;
import dao.ServiceChargeDAO;
import dao.TimeCardDAO;
import model.Employee;
import model.FlatEmployee;
import model.HourlyEmployee;
import model.Payment;
import model.SalesReceipt;
import model.ServiceCharge;
import model.TimeCard;
import payment.IBankPayment;
import payment.IMailPayment;
import payment.IPickupPayment;
import payment.TestBankPayer;
import payment.TestMailPayer;
import payment.TestPickupPayer;
import tools.DateUtilities;

@Stateless
public class PayrollControl {

	@Inject
	EmployeeDAO employeeDAO;
	@Inject
	SalesReceiptDAO salesReceiptDAO;
	@Inject
	ServiceChargeDAO serviceChargeDAO;
	@Inject
	TimeCardDAO timeCardDAO;
	@Inject
	PaymentDAO paymentDAO;

	DateUtilities dateUtilities;
	Logger logger = Logger.getLogger(AdminControl.class);

	@PostConstruct
	public void init() {
		logger.info("AdminControl initialized.");
	}

	public void runPayroll(Date date) {
		List<FlatEmployee> flatEmployeesList = employeeDAO.getFlatEmployees();
		List<HourlyEmployee> hourlyEmployeesList = employeeDAO.getHourlyEmployees();

		payFlatEmployees(flatEmployeesList, date);
		payHourlyEmployees(hourlyEmployeesList, date);
	}

	private void payFlatEmployees(List<FlatEmployee> flatEmployeesList, Date date) {
		for (FlatEmployee employee : flatEmployeesList) {
			// Calculate amounts
			float flatPayments = getFlatPayments(date, employee);
			float receiptPayments = getReceiptPayments(date, employee);
			float unionDeductions = getUnionDeductions(date, employee);
			float totalPayment = flatPayments + receiptPayments - unionDeductions;

			// Update the employee
			employee.setLastPaid(date);
			employeeDAO.updateFlatEmployee(employee);

			// Send the payment
			sendFlatPayment(employee, totalPayment, flatPayments, receiptPayments, unionDeductions);

			// Record the payment in the database
			Payment payment = new Payment(date, totalPayment, employee);
			paymentDAO.addPayment(payment);
		}

	}

	private void payHourlyEmployees(List<HourlyEmployee> hourlyEmployeesList, Date date) {
		for (HourlyEmployee employee : hourlyEmployeesList) {
			// Calculate amounts
			float hourlyPayments = getHourlyPayments(date, employee);
			float unionDeductions = getUnionDeductions(date, employee);
			float totalPayment = hourlyPayments - unionDeductions;

			// Update the employee
			employee.setLastPaid(date);
			employeeDAO.updateHourlyEmployee(employee);

			// Send the payment
			sendHourlyPayment(employee, totalPayment, hourlyPayments, unionDeductions);

			// Record the payment in the database
			Payment payment = new Payment(date, totalPayment, employee);
			paymentDAO.addPayment(payment);
		}

	}

	/**
	 * Sends payments to a hourly employee, abstracting on how.
	 * 
	 * @param employee
	 * @param totalPayment
	 * @param hourlyPayments
	 * @param unionDeductions
	 */
	private void sendHourlyPayment(HourlyEmployee employee, float totalPayment, float hourlyPayments,
			float unionDeductions) {
		switch (employee.getMethodOfPayment()) {
		case PaymentMethods.bank:
			IBankPayment bankPayer = new TestBankPayer();
			bankPayer.payHourlyEmployee(employee.getName(), employee.getSurname(), employee.getBankAccount(),
					totalPayment, hourlyPayments, unionDeductions);
			break;
		case PaymentMethods.mailed:
			IMailPayment mailPayer = new TestMailPayer();
			mailPayer.payHourlyEmployee(employee.getName(), employee.getSurname(), employee.getAddress(), totalPayment,
					hourlyPayments, unionDeductions);
			break;
		case PaymentMethods.pickup:
			IPickupPayment pickupPayer = new TestPickupPayer();
			pickupPayer.payHourlyEmployee(employee.getName(), employee.getSurname(), employee.getAddress(),
					totalPayment, hourlyPayments, unionDeductions);
			break;
		}
	}

	/**
	 * Sends payments to a flat employee, abstracting on how.
	 * 
	 * @param employee
	 * @param totalPayment
	 * @param hourlyPayments
	 * @param unionDeductions
	 */
	private void sendFlatPayment(FlatEmployee employee, float totalPayment, float flatPayments, float receiptPayments,
			float unionDeductions) {
		switch (employee.getMethodOfPayment()) {
		case PaymentMethods.bank:
			IBankPayment bankPayer = new TestBankPayer();
			bankPayer.payFlatEmployee(employee.getName(), employee.getSurname(), employee.getBankAccount(),
					totalPayment, flatPayments, receiptPayments, unionDeductions);
			break;
		case PaymentMethods.mailed:
			IMailPayment mailPayer = new TestMailPayer();
			mailPayer.payFlatEmployee(employee.getName(), employee.getSurname(), employee.getAddress(), totalPayment,
					flatPayments, receiptPayments, unionDeductions);
			break;
		case PaymentMethods.pickup:
			IPickupPayment pickupPayer = new TestPickupPayer();
			pickupPayer.payFlatEmployee(employee.getName(), employee.getSurname(), employee.getAddress(), totalPayment,
					flatPayments, receiptPayments, unionDeductions);
			break;
		}

	}

	/**
	 * Calculates union deductions for a given employee.
	 * 
	 * @param date
	 * @param employee
	 * @return
	 */
	private float getUnionDeductions(Date date, Employee employee) {
		if (employee.isInUnion()) {
			Date startDate = employee.getLastPaid();

			List<ServiceCharge> serviceChargesOfEmployee = serviceChargeDAO.getServiceChargesOfEmployee(employee);
			float chargesAmount = 0;
			for (ServiceCharge serviceCharge : serviceChargesOfEmployee) {
				if (!serviceCharge.isDeducted() && serviceCharge.getChargeDate().before(date)) {
					chargesAmount += serviceCharge.getAmount();
					serviceCharge.setDeducted(true);
					serviceChargeDAO.updateCharge(serviceCharge);
				}
			}

			int sundaysCount = getSundaysNumber(date, startDate);
			float unionDuesAmount = employee.getUnionDues() * sundaysCount;

			return unionDuesAmount + chargesAmount;
		}
		else {
			return 0f;
		}
	}

	private int getSundaysNumber(Date date, Date start) {
		// Code taken from:
		// https://stackoverflow.com/questions/20527998/get-all-fridays-in-a-date-range-in-java
		String startDateString = start.toString();
		String endDateString = date.toString();

		DateTimeFormatter pattern = DateTimeFormat.forPattern("yyyy-mm-dd");

		DateTime startDate = pattern.parseDateTime(startDateString);
		DateTime endDate = pattern.parseDateTime(endDateString);

		int fridaysCount = 0;

		while (startDate.isBefore(endDate)) {
			if (startDate.getDayOfWeek() == DateTimeConstants.SUNDAY) {
				fridaysCount++;
			}
			startDate = startDate.plusDays(1);
		}
		return fridaysCount;
	}

	private float getReceiptPayments(Date date, FlatEmployee employee) {
		List<SalesReceipt> salesReceipts = salesReceiptDAO.getSalesReceiptsOfEmployee(employee);
		float total = 0;

		for (SalesReceipt salesReceipt : salesReceipts) {
			if (!salesReceipt.isPaid() && salesReceipt.getReceiptDate().before(date)) {
				total += salesReceipt.getAmount() * (employee.getCommissionRate() / 100);
				salesReceipt.setPaid(true);
				salesReceiptDAO.updateReceipt(salesReceipt);
			}
		}

		return total;
	}

	private float getFlatPayments(Date endDate, FlatEmployee employee) {
		Date startDate = employee.getLastPaid();
		float salary = employee.getSalary();

		// Code taken from:
		// https://stackoverflow.com/questions/16558898/get-difference-between-two-dates-in-months-using-java
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(startDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

		return diffMonth * salary;
	}

	private float getHourlyPayments(Date endDate, HourlyEmployee employee) {

		List<TimeCard> timeCardsToBePaid = timeCardDAO.getTimeCardsOfEmployee(employee);
		int totalHoursAmount = 0;
		int extraHoursAmount = 0;

		for (TimeCard timeCard : timeCardsToBePaid) {
			if (!timeCard.isPaid() && timeCard.getTimeCardDate().before(endDate)) {
				// Add normal hours amount
				totalHoursAmount += timeCard.getHoursWorked();
				// Check for extra hours
				if (timeCard.getHoursWorked() > HourlyEmployeeConstants.extra) {
					extraHoursAmount += HourlyEmployeeConstants.extra - timeCard.getHoursWorked();
				}
				timeCard.setPaid(true);
				timeCardDAO.updateTimeCard(timeCard);
			}
		}
		float hourlyPayments = totalHoursAmount * employee.getRate() + (HourlyEmployeeConstants.extraAmount - 1) * employee.getRate() * extraHoursAmount;
		return hourlyPayments;
	}

}
