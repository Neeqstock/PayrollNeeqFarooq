package dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;
import model.Payment;

/**
 * Database management for the Payment entity.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class PaymentDAO {

	@PersistenceContext
	EntityManager entityManager;

	public void addPayment(Payment payment) {
		entityManager.persist(payment);
		entityManager.flush();
	}

	public List<Payment> getPaymentsOfEmployee(Employee employee) {
		List<Payment> payments = entityManager
				.createQuery("SELECT p FROM Payment p WHERE employeeID ='" + employee.getEmployeeID() + "'",
						Payment.class)
				.getResultList();
		return payments;
	}

	public List<Payment> getPaymentsInDate(Date date) {
		List<Payment> payments = entityManager.createQuery("SELECT p FROM Payment p WHERE paymentDate = '" + date + "'",	Payment.class).getResultList();
		return payments;
	}

}
