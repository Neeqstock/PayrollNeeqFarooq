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
	
	// CONSTRUCTOR
	public HourlyEmployee(String name, String surname, String address, String contractType, String methodOfPayment,
			float rate) {
		super(name, surname, address, contractType, methodOfPayment);
		this.rate = rate;
	}
	public HourlyEmployee(){
		super();
	}
	
	public float getSalary() {
		return rate;
	}



	public void setSalary(float salary) {
		this.rate = salary;
	}
	
}
