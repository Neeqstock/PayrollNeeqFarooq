package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import constants.ContractTypes;

@Entity
@Table(name = "Employee")
@DiscriminatorValue(ContractTypes.hourly)
public class HourlyEmployee extends Employee{
	
	// FIELDS
	private float rate;

	public float getSalary() {
		return rate;
	}

	public void setSalary(float salary) {
		this.rate = salary;
	}
	
}
