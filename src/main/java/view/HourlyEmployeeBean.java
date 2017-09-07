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
import control.TimeCardControl;
import model.HourlyEmployee;
import model.TimeCard;
import tools.DateTypesWrapper;

/**
 * Logic for the hourlyEmployeeInterface.xhtml view.
 * 
 * @author neeqstock
 *
 */
@SessionScoped
@ManagedBean
@Named("hourlyEmployeeBean")
public class HourlyEmployeeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7681333923195815640L;
	@Inject
	EmployeeControl employeeControl;
	@Inject
	TimeCardControl timeCardControl;

	// FIELDS

	private int employeeID;
	private HourlyEmployee hourlyEmployee;

	private List<TimeCard> timeCardsList;
	private TimeCard selectedTimeCard;
	private TimeCard timeCardToAdd;

	private java.sql.Date firstDate;
	private java.sql.Date lastDate;
	private java.sql.Date timeCardToAddDate;

	private DateTypesWrapper dateTypesWrapper;

	// POSTCONSTUCTOR

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		employeeID = (Integer) session.getAttribute("employeeID");
		hourlyEmployee = employeeControl.getHourlyEmployee(employeeID);

		dateTypesWrapper = new DateTypesWrapper();

		selectedTimeCard = new TimeCard();
		timeCardToAdd = new TimeCard();
	}

	// VIEW METHODS

	public void addTimeCard(ActionEvent actionEvent) {
		// TimeCard preparation
		timeCardToAdd.setEmployee(hourlyEmployee);
		timeCardToAdd.setPaid(false);
		timeCardToAdd.setTimeCardDate(timeCardToAddDate);

		timeCardControl.addTimeCard(timeCardToAdd);
		addMessage("Timecard added!");
		fetchTimeCards(firstDate, lastDate);

		timeCardToAdd = new TimeCard();
	}

	public void listTimeCards(ActionEvent actionEvent) {
		fetchTimeCards(firstDate, lastDate);
	}

	private void fetchTimeCards(Date date1, Date date2) {
		if (date1 != null & date2 != null) {
			timeCardsList = timeCardControl.getTimeCardsBetweenDates(hourlyEmployee, date1, date2);
		}
	}

	public void deleteTimeCard(ActionEvent actionEvent) {
		timeCardControl.deleteTimeCard(selectedTimeCard);
		addMessage("Timecard deleted!");
		fetchTimeCards(firstDate, lastDate);
	}

	public void submitModifications(ActionEvent actionEvent) {
		employeeControl.updateHourlyEmployee(hourlyEmployee);
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

	public java.util.Date getTimeCardToAddDate() {
		return dateTypesWrapper.sqlToUtil(timeCardToAddDate);
	}

	public void setTimeCardToAddDate(java.util.Date date) {
		this.timeCardToAddDate = dateTypesWrapper.utilToSql(date);
	}

	// Others

	public List<TimeCard> getTimeCardsList() {
		return timeCardsList;
	}

	public void setTimeCardsList(List<TimeCard> timeCardsList) {
		this.timeCardsList = timeCardsList;
	}

	public TimeCard getSelectedTimeCard() {
		return selectedTimeCard;
	}

	public void setSelectedTimeCard(TimeCard selectedTimeCard) {
		this.selectedTimeCard = selectedTimeCard;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public HourlyEmployee getHourlyEmployee() {
		return hourlyEmployee;
	}

	public void setHourlyEmployee(HourlyEmployee hourlyEmployee) {
		this.hourlyEmployee = hourlyEmployee;
	}

	public TimeCard getTimeCardToAdd() {
		return timeCardToAdd;
	}

	public void setTimeCardToAdd(TimeCard timeCardToAdd) {
		this.timeCardToAdd = timeCardToAdd;
	}

}