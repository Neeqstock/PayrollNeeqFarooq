package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * An entity that describes a service charge, submittable by the union to charge
 * an employee which belongs to it.
 * 
 * @author neeqstock
 *
 */
@Entity
@Table(name = "ServiceCharge")
public class ServiceCharge {

	// KEY
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chargeID;

	// FIELDS
	@ManyToOne
	@JoinColumn(name = "employeeID", referencedColumnName = "employeeID", nullable = false)
	private Employee employee;
	private Date chargeDate;
	private float amount;
	private boolean deducted;

	// CONSTRUCTOR
	public ServiceCharge(Employee employee, Date chargeDate, float amount) {
		super();
		this.employee = employee;
		this.chargeDate = chargeDate;
		this.amount = amount;
		this.deducted = false;
	}

	public ServiceCharge() {
		super();
	}

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

	public int getChargeID() {
		return chargeID;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
