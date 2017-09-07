package view;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import control.AccountControl;
import control.AdminControl;
import control.PayrollControl;
import control.ServiceChargeControl;
import model.Account;
import model.FlatEmployee;
import model.HourlyEmployee;
import model.Payment;
import model.ServiceCharge;
import tools.DateTypesWrapper;

/**
 * Logic for the adminInterface.xhtml view.
 * 
 * @author neeqstock
 *
 */
@SessionScoped
@ManagedBean
@Named("adminBean")
public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 732193638322517687L;

	@Inject
	AdminControl adminControl;
	@Inject
	AccountControl accountControl;
	@Inject
	ServiceChargeControl serviceChargeControl;
	@Inject
	PayrollControl payrollControl;

	// FIELDS
	private List<FlatEmployee> flatEmployeesList;
	private List<HourlyEmployee> hourlyEmployeesList;

	private List<ServiceCharge> serviceChargesList;

	private List<Payment> paymentsList;

	private FlatEmployee selectedFlatEmployee;
	private HourlyEmployee selectedHourlyEmployee;

	private FlatEmployee flatEmployeeToAdd;
	private HourlyEmployee hourlyEmployeeToAdd;
	private Account accountToAdd;

	private ServiceCharge selectedServiceCharge;

	private ServiceCharge serviceChargeToAdd;

	// DATE TYPES WRAPPER!

	private java.sql.Date chargeDate;
	private java.sql.Date runPayrollDate;

	private DateTypesWrapper dateTypesWrapper;

	// POSTCONSTUCTOR

	@PostConstruct
	public void init() {
		hourlyEmployeesList = adminControl.getHourlyEmployees();
		flatEmployeesList = adminControl.getFlatEmployees();

		serviceChargesList = serviceChargeControl.getServiceCharges();

		selectedFlatEmployee = new FlatEmployee();
		selectedHourlyEmployee = new HourlyEmployee();
		flatEmployeeToAdd = new FlatEmployee();
		hourlyEmployeeToAdd = new HourlyEmployee();
		accountToAdd = new Account();

		selectedServiceCharge = new ServiceCharge();
		serviceChargeToAdd = new ServiceCharge();

		dateTypesWrapper = new DateTypesWrapper();
	}

	// VIEW METHODS

	public void runPayroll(ActionEvent actionEvent) {
		payrollControl.runPayroll(runPayrollDate);
		addMessage("Payroll has been run!");
		fetchPaymentsForDate(runPayrollDate);
	}

	private void fetchPaymentsForDate(Date date) {
		paymentsList = payrollControl.getPayments(date);

	}

	public void selectFlatEmployeeToCharge(ActionEvent actionEvent) {
		serviceChargeToAdd.setEmployee(selectedFlatEmployee);
	}

	public void selectHourlyEmployeeToCharge(ActionEvent actionEvent) {
		serviceChargeToAdd.setEmployee(selectedHourlyEmployee);
	}

	public void submitServiceCharge(ActionEvent actionEvent) {
		// A workaround to wrap the date types :)
		serviceChargeToAdd.setChargeDate(chargeDate);

		addMessage("Service charge for employee '" + serviceChargeToAdd.getEmployee().getEmployeeID() + "' added!");
		serviceChargeControl.addServiceCharge(serviceChargeToAdd);
		serviceChargeToAdd = new ServiceCharge();
		fetchServiceCharges();
	}

	public void deleteSelectedCharge(ActionEvent actionEvent) {
		addMessage("Service charge '" + selectedServiceCharge.getChargeID() + "' deleted!");
		serviceChargeControl.deleteServiceCharge(selectedServiceCharge);
		fetchServiceCharges();
	}

	private void fetchServiceCharges() {
		serviceChargesList = serviceChargeControl.getServiceCharges();
	}

	public void submitModifyFlat(ActionEvent actionEvent) {
		addMessage("Employee '" + selectedFlatEmployee.getEmployeeID() + "' modified!");
		adminControl.updateFlatEmployee(selectedFlatEmployee);
		fetchFlatEmployees();
	}

	public void submitModifyHourly(ActionEvent actionEvent) {
		addMessage("Employee '" + selectedHourlyEmployee.getEmployeeID() + "' modified!");
		adminControl.updateHourlyEmployee(selectedHourlyEmployee);
		fetchHourlyEmployees();
	}

	public void deleteFlatEmployee(ActionEvent actionEvent) {
		addMessage("Employee '" + selectedFlatEmployee.getEmployeeID() + "' deleted!");
		adminControl.removeFlatEmployee(selectedFlatEmployee);
		fetchFlatEmployees();
	}

	public void deleteHourlyEmployee(ActionEvent actionEvent) {
		addMessage("Employee '" + selectedHourlyEmployee.getEmployeeID() + "' deleted!");
		adminControl.removeHourlyEmployee(selectedHourlyEmployee);
		fetchHourlyEmployees();
	}

	public void addFlatEmployee(ActionEvent actionEvent) {
		accountToAdd.setAdmin(false);
		accountToAdd.setEmployee(flatEmployeeToAdd);

		adminControl.addFlatEmployee(flatEmployeeToAdd);
		accountControl.addAccount(accountToAdd);

		fetchFlatEmployees();

		addMessage("Employee '" + flatEmployeeToAdd.getName() + " " + flatEmployeeToAdd.getSurname() + "' added!");

		flatEmployeeToAdd = new FlatEmployee();
		accountToAdd = new Account();
	}

	public void addHourlyEmployee(ActionEvent actionEvent) {
		accountToAdd.setAdmin(false);
		accountToAdd.setEmployee(hourlyEmployeeToAdd);

		adminControl.addHourlyEmployee(hourlyEmployeeToAdd);
		accountControl.addAccount(accountToAdd);

		fetchHourlyEmployees();
		addMessage("Employee '" + hourlyEmployeeToAdd.getName() + " " + hourlyEmployeeToAdd.getSurname() + "' added!");

		hourlyEmployeeToAdd = new HourlyEmployee();
		accountToAdd = new Account();
	}

	public void buttonAction(ActionEvent actionEvent) {
		System.out.println("Welcome to Primefaces!");

		addMessage("Welcome to Primefaces!");
	}

	public void testWhoIsSelected(ActionEvent actionEvent) {
		addMessage(selectedFlatEmployee.getName());
	}

	private void fetchFlatEmployees() {
		flatEmployeesList = adminControl.getFlatEmployees();
	}

	private void fetchHourlyEmployees() {
		hourlyEmployeesList = adminControl.getHourlyEmployees();

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// GETTERS AND SETTERS

	// Special getters/setters for wrapped dates
	public java.util.Date getChargeDate() {
		return dateTypesWrapper.sqlToUtil(chargeDate);
	}

	public void setChargeDate(java.util.Date chargeDate) {
		this.chargeDate = dateTypesWrapper.utilToSql(chargeDate);
	}

	public java.util.Date getRunPayrollDate() {
		return dateTypesWrapper.sqlToUtil(runPayrollDate);
	}

	public void setRunPayrollDate(java.util.Date runPayrollDate) {
		System.out.println("SETRUNDATE...");
		this.runPayrollDate = dateTypesWrapper.utilToSql(runPayrollDate);
	}

	public List<ServiceCharge> getServiceChargesList() {
		return serviceChargesList;
	}

	public ServiceCharge getSelectedServiceCharge() {
		return selectedServiceCharge;
	}

	public void setSelectedServiceCharge(ServiceCharge selectedServiceCharge) {
		this.selectedServiceCharge = selectedServiceCharge;
	}

	public void setServiceChargesList(List<ServiceCharge> serviceChargesList) {
		this.serviceChargesList = serviceChargesList;
	}

	public ServiceCharge getServiceChargeToAdd() {
		return serviceChargeToAdd;
	}

	public void setServiceChargeToAdd(ServiceCharge serviceChargeToAdd) {
		this.serviceChargeToAdd = serviceChargeToAdd;
	}

	public List<FlatEmployee> getFlatEmployeesList() {
		return flatEmployeesList;
	}

	public FlatEmployee getFlatEmployeeToAdd() {
		return flatEmployeeToAdd;
	}

	public void setFlatEmployeeToAdd(FlatEmployee flatEmployeeToAdd) {
		this.flatEmployeeToAdd = flatEmployeeToAdd;
	}

	public Account getAccountToAdd() {
		return accountToAdd;
	}

	public void setAccountToAdd(Account accountToAdd) {
		this.accountToAdd = accountToAdd;
	}

	public HourlyEmployee getHourlyEmployeeToAdd() {
		return hourlyEmployeeToAdd;
	}

	public void setHourlyEmployeeToAdd(HourlyEmployee hourlyEmployeeToAdd) {
		this.hourlyEmployeeToAdd = hourlyEmployeeToAdd;
	}

	public void setFlatEmployeesList(List<FlatEmployee> flatEmployeesList) {
		this.flatEmployeesList = flatEmployeesList;
	}

	public List<HourlyEmployee> getHourlyEmployeesList() {
		return hourlyEmployeesList;
	}

	public void setHourlyEmployeesList(List<HourlyEmployee> hourlyEmployeesList) {
		this.hourlyEmployeesList = hourlyEmployeesList;
	}

	public List<Payment> getPaymentsList() {
		return paymentsList;
	}

	public void setPaymentsList(List<Payment> paymentsList) {
		this.paymentsList = paymentsList;
	}

	public FlatEmployee getSelectedFlatEmployee() {
		return selectedFlatEmployee;
	}

	public void setSelectedFlatEmployee(FlatEmployee selectedFlatEmployee) {
		this.selectedFlatEmployee = selectedFlatEmployee;
	}

	public HourlyEmployee getSelectedHourlyEmployee() {
		return selectedHourlyEmployee;
	}

	public void setSelectedHourlyEmployee(HourlyEmployee selectedHourlyEmployee) {
		this.selectedHourlyEmployee = selectedHourlyEmployee;
	}

}