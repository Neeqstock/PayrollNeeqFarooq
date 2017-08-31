package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.HourlyEmployee;
import model.TimeCard;

@Stateless
public class TimeCardDAO {

	@PersistenceContext
	EntityManager entityManager;

	public List<TimeCard> getTimeCardsOfEmployee(HourlyEmployee employee) {
		List<TimeCard> timeCards = entityManager.createQuery("SELECT * FROM TimeCard WHERE employeeID ='" + employee.getEmployeeID() + "';", TimeCard.class).getResultList();
		return timeCards;
	}

	public void updateTimeCard(TimeCard timeCard) {
		entityManager.merge(timeCard);
		entityManager.flush();
	}

	public void addTimeCard(TimeCard timeCard) {
		entityManager.persist(timeCard);
		entityManager.flush();
		
	}
}
