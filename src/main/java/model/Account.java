package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Account")

public class Account {
	
	// KEY
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountID;
	
	// FIELDS
	private boolean isAdmin;
	private String userName;
	private String password;
	@OneToOne
	@JoinColumn(name="employeeID", referencedColumnName="employeeID", nullable=true)
	private Employee employee;
	
	// CONSTRUCTOR
	public Account(boolean isAdmin, String userName, String password, Employee employee) {
		super();
		this.isAdmin = isAdmin;
		this.userName = userName;
		this.password = password;
		this.employee = employee;
	}
	
	// GETTERS, SETTERS
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
