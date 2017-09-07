package tools;

/**
 * A "translation" entity that makes login data transfer between functions more
 * comfortable.
 * 
 * @author neeqstock
 *
 */
public class LoginEntity {

	private int employeeID;
	private int accountID;
	private boolean isAdmin;
	private String contractType;

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

}
