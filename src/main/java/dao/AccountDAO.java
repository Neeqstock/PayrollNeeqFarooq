package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import constants.AccountTypes;
import model.Account;

@Stateless
public class AccountDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	public void addAccount(Account account) {
		entityManager.persist(account);
		entityManager.flush();
	}

	public List<Account> getAccounts() {
		return entityManager.createQuery("SELECT * FROM Account;", Account.class).getResultList();
	}

	public Account getAccount(String username, String password) {
		return entityManager.createQuery("SELECT accountID FROM Account WHERE username = '" + username + "' AND password ='" + password + "';" , Account.class).getSingleResult();
	}

	public int getEmployeeID(int accountID) {
		return entityManager.createQuery("SELECT employeeID FROM Account WHERE accountID = '" + accountID + "';" , Integer.class).getSingleResult();
	}
}
