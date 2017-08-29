package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Payment")

public class Payment {
	
	// KEY
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentID;
	
	// FIELDS
	private Date paymentDate;
	private float paymentAmount;
	@OneToOne
	@JoinColumn(name="employeeID", referencedColumnName="employeeID", nullable=true)
	private Employee employee;
	
	// CONSTRUCTOR
	public Payment(Date paymentDate, float paymentAmount, Employee employee) {
		super();
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.employee = employee;
	}
	public Payment(){}

	// GETTERS, SETTERS
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public float getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public int getPaymentID() {
		return paymentID;
	}
	


}
