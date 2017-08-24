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
@Table(name = "SalesReceipt")

public class SalesReceipt {
	
	// KEY
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int receiptID;
	
	// FIELDS
	@ManyToOne
	@JoinColumn(name="employeeID",referencedColumnName="employeeID", nullable=false)
	private Employee employee;
	private float amount;
	private Date receiptDate;
	private String company;
	private String additionalInfo;
	private boolean paid;
	
	// CONSTRUCTOR
	public SalesReceipt(Employee employee, float amount, Date receiptDate, String company) {
		super();
		this.employee = employee;
		this.amount = amount;
		this.receiptDate = receiptDate;
		this.company = company;
		
		this.additionalInfo = "";
		this.paid = false;
	}
	public SalesReceipt(){};
	
	// GETTERS, SETTERS
	public float getAmount() {
		return amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	


}
