package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;

/**
 * Database management for the Account entity.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class AccountDAO {

	@PersistenceContext
	EntityManager entityManager;

	public void addAccount(Account account) {
		entityManager.persist(account);
		entityManager.flush();
	}

	public List<Account> getAccounts() {
		return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
	}

	public Account getAccount(String username, String password) {
		return entityManager.createQuery(
				"SELECT a FROM Account a WHERE username = '" + username + "' AND password ='" + password + "'",
				Account.class).getSingleResult();
	}

	public int getEmployeeID(int accountID) {
		return entityManager.createQuery("SELECT a FROM Account a WHERE accountID = '" + accountID + "'", Account.class)
				.getSingleResult().getEmployee().getEmployeeID();
	}
}
