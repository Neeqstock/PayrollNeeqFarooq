package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
/**
 * A class used only to clean up the database.
 * @author neeqstock
 *
 */
public class DatabaseCleaner {

	@PersistenceContext
	EntityManager em;
	
	/**
	 * Cleans up the database for testing purposes.
	 */
	public void clean() {
		em.createQuery("DELETE FROM Account").executeUpdate();
		em.createQuery("DELETE FROM Employee").executeUpdate();
		em.createQuery("DELETE FROM SalesReceipt").executeUpdate();
		em.createQuery("DELETE FROM ServiceCharge").executeUpdate();
		em.createQuery("DELETE FROM Timecard").executeUpdate();
	}
}
