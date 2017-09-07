package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * A class used only to clean up the database.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class DatabaseCleaner {

	@PersistenceContext
	EntityManager em;

	/**
	 * Cleans up the database.
	 */
	public void clean() {
		em.createQuery("DELETE FROM Account").executeUpdate();
		em.createQuery("DELETE FROM SalesReceipt").executeUpdate();
		em.createQuery("DELETE FROM ServiceCharge").executeUpdate();
		em.createQuery("DELETE FROM Timecard").executeUpdate();
		em.createQuery("DELETE FROM Payment").executeUpdate();
		em.createQuery("DELETE FROM Employee").executeUpdate();
	}
}
