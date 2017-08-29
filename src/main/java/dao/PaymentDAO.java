package dao;

import javax.ejb.Stateless;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Payment;

@Singleton
@Stateless
public class PaymentDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	public void addPayment(Payment payment) {
		entityManager.persist(payment);
		entityManager.flush();
	}

}
