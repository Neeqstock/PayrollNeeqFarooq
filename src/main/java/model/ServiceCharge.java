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
@Table(name = "ServiceCharge")

public class ServiceCharge {
	
	// KEY
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int chargeID;
	
	// FIELDS
	@ManyToOne
	@JoinColumn(name="employeeID",referencedColumnName="employeeID", nullable=false)
	private Employee employee;
	private Date date;
	private float amount;
	
	// GETTERS, SETTERS
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	


}
