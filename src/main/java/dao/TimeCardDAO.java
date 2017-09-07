package dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.HourlyEmployee;
import model.TimeCard;

/**
 * Database management for the TimeCard entity.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class TimeCardDAO {

	@PersistenceContext
	EntityManager entityManager;

	public List<TimeCard> getTimeCardsOfEmployee(HourlyEmployee employee) {
		List<TimeCard> timeCards = entityManager
				.createQuery("SELECT t FROM TimeCard t WHERE employeeID ='" + employee.getEmployeeID() + "'",
						TimeCard.class)
				.getResultList();
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

	public List<TimeCard> getTimeCardsBetweenDates(HourlyEmployee hourlyEmployee, Date date1, Date date2) {
		List<TimeCard> timeCards = entityManager
				.createQuery("SELECT t FROM TimeCard t WHERE employeeID =" + hourlyEmployee.getEmployeeID()
						+ " AND timeCardDate BETWEEN '" + date1 + "' AND '" + date2 + "'", TimeCard.class)
				.getResultList();
		return timeCards;
	}

	public void deleteTimeCard(TimeCard timeCard) {
		int ID = timeCard.getTimecardID();
		entityManager.createQuery("DELETE FROM TimeCard WHERE timecardID = " + ID).executeUpdate();
	}
}
