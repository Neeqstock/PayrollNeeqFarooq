package control;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.AccountDAO;
import dao.EmployeeDAO;
import model.Account;
import model.Employee;
import tools.LoginEntity;

/**
 * Controls the various actions available for the Account entity.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class AccountControl {

	@Inject
	AccountDAO accountDAO;
	@Inject
	EmployeeDAO employeeDAO;

	public void addAccount(Account account) {
		accountDAO.addAccount(account);
	}

	public List<Account> getAccounts() {
		return accountDAO.getAccounts();
	}

	/**
	 * In case of failure, returns -1. Else, returns the employeeID
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public LoginEntity validate(String username, String password) {
		LoginEntity loginEntity = new LoginEntity();
		Account account = accountDAO.getAccount(username, password);

		if (account != null) {
			int employeeID = accountDAO.getEmployeeID(account.getAccountID());
			Employee employee = employeeDAO.getEmployee(employeeID);

			loginEntity.setEmployeeID(employeeID);
			loginEntity.setAccountID(account.getAccountID());
			loginEntity.setAdmin(account.isAdmin());
			loginEntity.setContractType(employee.getContractType());

			return loginEntity;
		} else {
			return null;
		}
	}

}
