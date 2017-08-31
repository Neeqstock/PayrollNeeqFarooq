package control;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.TimeCardDAO;
import model.HourlyEmployee;
import model.TimeCard;

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

}
