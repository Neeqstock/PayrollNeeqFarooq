package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.FlatEmployee;
import model.SalesReceipt;

@Stateless
public class SalesReceiptDAO {

	@PersistenceContext
	EntityManager entityManager;

	public List<SalesReceipt> getSalesReceiptsOfEmployee(FlatEmployee employee) {
		List<SalesReceipt> salesReceipts = entityManager.createQuery("SELECT * FROM SalesReceipt WHERE employeeID ='" + employee.getEmployeeID() + "';", SalesReceipt.class).getResultList();
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

	
}
