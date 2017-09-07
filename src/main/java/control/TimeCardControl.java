package control;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.TimeCardDAO;
import model.HourlyEmployee;
import model.TimeCard;

/**
 * Controls the various actions available for the TimeCard entity.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class TimeCardControl {

	@Inject
	TimeCardDAO timeCardDAO;
	
	public void addTimeCard(TimeCard timeCard) {
		timeCardDAO.addTimeCard(timeCard);
		
	}

	public List<TimeCard> getTimeCardsOfEmployee(HourlyEmployee hourlyEmployee) {
		return timeCardDAO.getTimeCardsOfEmployee(hourlyEmployee);
	}

	public List<TimeCard> getTimeCardsBetweenDates(HourlyEmployee hourlyEmployee, Date date1, Date date2) {
		return timeCardDAO.getTimeCardsBetweenDates(hourlyEmployee, date1, date2);
	}

	public void deleteTimeCard(TimeCard timeCard) {
		timeCardDAO.deleteTimeCard(timeCard);
	}

}
