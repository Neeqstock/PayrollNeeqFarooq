package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import constants.ContractTypes;

@Entity
@Table(name = "Employee")
@DiscriminatorValue(ContractTypes.flat)
public class FlatEmployee extends Employee{
	
	// FIELDS
	private float salary;

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	
}
