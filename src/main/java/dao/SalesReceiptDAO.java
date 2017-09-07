package dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.FlatEmployee;
import model.SalesReceipt;

/**
 * Database management for the SalesReceipt entity.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class SalesReceiptDAO {

	@PersistenceContext
	EntityManager entityManager;

	public List<SalesReceipt> getSalesReceiptsOfEmployee(FlatEmployee employee) {
		List<SalesReceipt> salesReceipts = entityManager
				.createQuery("SELECT s FROM SalesReceipt s WHERE employeeID ='" + employee.getEmployeeID() + "'",
						SalesReceipt.class)
				.getResultList();
		return salesReceipts;
	}

	public void updateReceipt(SalesReceipt salesReceipt) {
		entityManager.merge(salesReceipt);
		entityManager.flush();
	}

	public void addSalesReceipt(SalesReceipt salesReceipt) {
		entityManager.persist(salesReceipt);
		entityManager.flush();
	}

	public List<SalesReceipt> getSalesReceiptsBetweenDates(FlatEmployee flatEmployee, Date date1, Date date2) {
		List<SalesReceipt> salesReceipts = entityManager
				.createQuery("SELECT s FROM SalesReceipt s WHERE employeeID =" + flatEmployee.getEmployeeID() + " AND receiptDate BETWEEN '" + date1 + "' AND '" + date2 + "'",
						SalesReceipt.class)
				.getResultList();
		return salesReceipts;
	}

	public void deleteSalesReceipt(SalesReceipt salesReceipt) {
		int ID = salesReceipt.getReceiptID();
		entityManager.createQuery("DELETE FROM SalesReceipt WHERE receiptID = " + ID).executeUpdate();
	}

}
