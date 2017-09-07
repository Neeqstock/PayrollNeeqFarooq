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
import javax.servlet.http.HttpSession;

import control.EmployeeControl;
import control.SalesReceiptControl;
import model.FlatEmployee;
import model.SalesReceipt;
import tools.DateTypesWrapper;

/**
 * Logic for the flatEmployeeInterface.xhtml view.
 * 
 * @author neeqstock
 *
 */
@SessionScoped
@ManagedBean
@Named("flatEmployeeBean")
public class FlatEmployeeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5498032000594387330L;

	@Inject
	EmployeeControl employeeControl;
	@Inject
	SalesReceiptControl salesReceiptControl;

	// FIELDS
	
	private int employeeID;
	private FlatEmployee flatEmployee;

	private List<SalesReceipt> salesReceiptsList;
	private SalesReceipt selectedSalesReceipt;
	private SalesReceipt salesReceiptToAdd;

	private java.sql.Date firstDate;
	private java.sql.Date lastDate;
	private java.sql.Date salesReceiptToAddDate;

	private DateTypesWrapper dateTypesWrapper;

	// POSTCONSTUCTOR

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		employeeID = (Integer) session.getAttribute("employeeID");
		flatEmployee = employeeControl.getFlatEmployee(employeeID);

		dateTypesWrapper = new DateTypesWrapper();
		
		selectedSalesReceipt = new SalesReceipt();
		salesReceiptToAdd = new SalesReceipt();
	}

	// VIEW METHODS

	public void addSalesReceipt(ActionEvent actionEvent){
		// SalesReceipt preparation
		salesReceiptToAdd.setEmployee(flatEmployee);
		salesReceiptToAdd.setPaid(false);
		salesReceiptToAdd.setReceiptDate(salesReceiptToAddDate);
		
		salesReceiptControl.addSalesReceipt(salesReceiptToAdd);
		addMessage("Sales receipt added!");
		fetchSalesReceipts(firstDate, lastDate);
		salesReceiptToAdd = new SalesReceipt();
	}
	
	public void listSalesReceipts(ActionEvent actionEvent) {
		fetchSalesReceipts(firstDate, lastDate);
	}

	private void fetchSalesReceipts(Date date1, Date date2) {
		if (date1 != null & date2 != null) {
			salesReceiptsList = salesReceiptControl.getSalesReceiptsBetweenDates(flatEmployee, date1, date2);
		}
	}

	public void deleteSalesReceipt(ActionEvent actionEvent) {
		salesReceiptControl.deleteSalesReceipt(selectedSalesReceipt);
		addMessage("Receipt deleted!");
		fetchSalesReceipts(firstDate, lastDate);
	}
	
	public void submitModifications(ActionEvent actionEvent) {
		employeeControl.updateFlatEmployee(flatEmployee);
		addMessage("Modifications submitted and saved!");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// GETTERS AND SETTERS

	// Special getters/setters for dates

	public java.util.Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(java.util.Date firstDate) {
		this.firstDate = dateTypesWrapper.utilToSql(firstDate);
	}

	public java.util.Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(java.util.Date lastDate) {
		this.lastDate = dateTypesWrapper.utilToSql(lastDate);
	}
	
	public java.util.Date getSalesReceiptToAddDate() {
		return dateTypesWrapper.sqlToUtil(salesReceiptToAddDate);
	}

	public void setSalesReceiptToAddDate(java.util.Date date) {
		this.salesReceiptToAddDate = dateTypesWrapper.utilToSql(date);
	}
	
	// Others
	
	public List<SalesReceipt> getSalesReceiptsList() {
		return salesReceiptsList;
	}

	public void setSalesReceiptsList(List<SalesReceipt> salesReceiptsList) {
		this.salesReceiptsList = salesReceiptsList;
	}
	
	public SalesReceipt getSelectedSalesReceipt() {
		return selectedSalesReceipt;
	}

	public void setSelectedSalesReceipt(SalesReceipt selectedSalesReceipt) {
		this.selectedSalesReceipt = selectedSalesReceipt;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public FlatEmployee getFlatEmployee() {
		return flatEmployee;
	}

	public void setFlatEmployee(FlatEmployee flatEmployee) {
		this.flatEmployee = flatEmployee;
	}
	
	public SalesReceipt getSalesReceiptToAdd() {
		return salesReceiptToAdd;
	}

	public void setSalesReceiptToAdd(SalesReceipt salesReceiptToAdd) {
		this.salesReceiptToAdd = salesReceiptToAdd;
	}

}