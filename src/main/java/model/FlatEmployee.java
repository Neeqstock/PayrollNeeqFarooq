package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import constants.ContractTypes;

@Entity
@Table(name = "Employee")
@DiscriminatorValue(ContractTypes.flat)
public class FlatEmployee extends Employee{
	
	// FIELDS
	private float salary;
	private float commissionRate;

	// GETTERS AND SETTERS
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public float getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(float commissionRate) {
		this.commissionRate = commissionRate;
	}
	
}
