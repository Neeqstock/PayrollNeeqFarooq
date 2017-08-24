package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


// The parent class of the employees. Even if hourly and flat employees are different entities, 
// they're saved in the same table.
@Entity
@Table(name = "Employee")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="contractType",discriminatorType=DiscriminatorType.STRING)

public class Employee {
	
	// KEY
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeID;
	
	// FIELDS
	private String name;
	private String surname;
	private String address;
	@Column(name="contractType", insertable=false, updatable=false)
	private String contractType;
	private String methodOfPayment;
	private String bankAccount;
	private Date lastPaid;
	private boolean inUnion;
	private float unionDues;
	
	// CONSTRUCTORS
	public Employee(String name, String surname, String address, String contractType, String methodOfPayment){
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.contractType = contractType;
		this.methodOfPayment = methodOfPayment;
	}
	
	// GETTERS, SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType){
		this.contractType = contractType;
	}
	public String getMethodOfPayment() {
		return methodOfPayment;
	}
	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}
	public Date getLastPaid() {
		return lastPaid;
	}
	public void setLastPaid(Date lastPaid) {
		this.lastPaid = lastPaid;
	}
	public float getUnionDues() {
		return unionDues;
	}
	public void setUnionDues(float unionDues) {
		this.unionDues = unionDues;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public boolean isInUnion() {
		return inUnion;
	}
	public void setInUnion(boolean inUnion) {
		this.inUnion = inUnion;
	}
	
	
}
