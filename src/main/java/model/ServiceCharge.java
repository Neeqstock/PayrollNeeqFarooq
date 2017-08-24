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
	private Date chargeDate;
	private float amount;
	private boolean deducted;
	
	// GETTERS, SETTERS

	public float getAmount() {
		return amount;
	}
	public Date getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public boolean isDeducted() {
		return deducted;
	}
	public void setDeducted(boolean deducted) {
		this.deducted = deducted;
	}
	


}
