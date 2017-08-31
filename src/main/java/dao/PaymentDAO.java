package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;
import model.Payment;

@Stateless
public class PaymentDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	public void addPayment(Payment payment) {
		entityManager.persist(payment);
		entityManager.flush();
	}

	public List<Payment> getPaymentsOfEmployee(Employee employee) {
		List<Payment> payments = entityManager.createQuery("SELECT * FROM Payment WHERE employeeID ='" + employee.getEmployeeID() + "';", Payment.class).getResultList();
		return payments;
	}

}
