package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import constants.ContractTypes;

/**
 * An entity that describes an hourly employee. Father class: Employee.
 * 
 * @author neeqstock
 *
 */
@Entity
@Table(name = "Employee")
@DiscriminatorValue(ContractTypes.hourly)
public class HourlyEmployee extends Employee {

	// FIELDS
	private float rate;

	// CONSTRUCTOR
	public HourlyEmployee(String name, String surname, String address, String contractType, String methodOfPayment,
			float rate) {
		super(name, surname, address, contractType, methodOfPayment);
		this.rate = rate;
	}

	public HourlyEmployee() {
		super();
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public void setSalary(float rate) {
		this.rate = rate;
	}

}
