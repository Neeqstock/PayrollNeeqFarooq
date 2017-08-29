package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.HourlyEmployee;
import model.TimeCard;

@Singleton
@Stateless
public class TimeCardDAO {

	@PersistenceContext
	EntityManager entityManager;

	public List<TimeCard> getTimeCardsOfEmployee(HourlyEmployee employee) {
		List<TimeCard> timeCards = entityManager.createQuery("SELECT * FROM TimeCard WHERE timeCardID ='" + employee.getEmployeeID() + "';", TimeCard.class).getResultList();
		return timeCards;
	}

	public void updateTimeCard(TimeCard timeCard) {
		entityManager.merge(timeCard);
		entityManager.flush();
	}
}
