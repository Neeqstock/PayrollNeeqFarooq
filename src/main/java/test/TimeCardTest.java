package test;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import controller.TimeCardController;
import dao.EmployeeDAO;
import dao.EmptyDB;
import dao.TimeCardDao;
import junit.framework.Assert;
import model.Employee;
import model.HourlyEmployee;
import model.TimeCard;

@RunWith(Arquillian.class)
public class TimeCardTest extends ArquillianTest {

	@Inject
	EmployeeDAO employeeDao;
	@Inject
	TimeCardController timeCardController;
	@Inject
	TimeCardDao timeCardDao;
	@Inject
	EmptyDB emptyDB;

	@Before
	public void cleanup() {
		emptyDB.EmptyDatabase();
	}

	@Test
	public void submitTimeCardTest() {
		// log in

		int hoursWorked = 8;

		// fill out time card
		TimeCard timecard = new TimeCard();
		timecard.setHours(hoursWorked);

		HourlyEmployee emp = new HourlyEmployee("Mario", "Rossi", (float) 0);
		employeeDao.add(emp);
		
		timecard.setHourlyEmployee(emp);

		boolean found = false;
		try {
			timeCardController.submit(timecard);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Employee employee = employeeDao.findByName("Mario", "Rossi", "Hourly");
		
		// check in DB
		List<TimeCard> timecards = timeCardDao.findAll(employee.getId());
		for (TimeCard tc : timecards) {
			if (tc.getHours() == hoursWorked &&
					tc.getHourlyEmployee().getId() == employee.getId()) {
				found = true;
				break;
			}
		}

		Assert.assertTrue("Single time card submitted", found);
	}

	@Test
	public void cascadeRemovalOfTimeCards() {

		HourlyEmployee employee = new HourlyEmployee("Marco", "Rossi", (float) 1);

		employeeDao.add(employee);
		
		// add a timecard
		TimeCard timecard = new TimeCard();
		timecard.setHours(2);
		timecard.setHourlyEmployee(employee);
		
		try {
			timeCardController.submit(timecard);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Employee emp = employeeDao.findByName("Marco", "Rossi", "Hourly");

		employeeDao.remove(employee);
		// WE ASSUME Mario Rossi to be deleted

		boolean found = false;
		// check in DB
		List<TimeCard> timecards = timeCardDao.findAll();

		for (TimeCard tc : timecards) {
			if (tc.getHourlyEmployee().getId() == emp.getId()) {
				found = true;
				break;
			}
		}

		Assert.assertTrue("Time cards deleted", !found);

	}

}
