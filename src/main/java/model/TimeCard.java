package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TimeCard")

public class TimeCard {
	
	// KEY
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int timecardID;
	
	// FIELDS
	@ManyToOne
	@JoinColumn(name="employeeID",referencedColumnName="employeeID", nullable=false)
	private Employee employee;
	private Date timeCardDate;
	private int hoursWorked;
	private boolean paid;
	private String additionalInfo;
	
	// CONSTRUCTOR
	public TimeCard(Employee employee, Date timeCardDate, int hoursWorked) {
		super();
		this.employee = employee;
		this.timeCardDate = timeCardDate;
		this.hoursWorked = hoursWorked;
		this.paid = false;
	}
	public TimeCard(){
		super();
	}
	
	// GETTERS, SETTERS
	public int getHoursWorked() {
		return hoursWorked;
	}
	
	public Date getTimeCardDate() {
		return timeCardDate;
	}
	public void setTimeCardDate(Date timeCardDate) {
		this.timeCardDate = timeCardDate;
	}
	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}


}
