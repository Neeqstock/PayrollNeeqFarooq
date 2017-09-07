package test;


import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import constants.ContractTypes;
import constants.PaymentMethods;
import control.AdminControl;
import control.TimeCardControl;
import dao.DatabaseCleaner;
import junit.framework.Assert;
import model.HourlyEmployee;
import model.TimeCard;

/**
 * Tests various functions on the TimeCard entity.
 * 
 * @author farooq
 *
 */
@RunWith(Arquillian.class)
public class TimeCardTest {

	@Inject	
	DatabaseCleaner databaseCleaner;
	@Inject
	AdminControl adminControl;
	@Inject
	TimeCardControl timeCardControl;
	
	@Before
	public void cleanDatabase(){
		databaseCleaner.clean();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addTimeCardTest(){
		
		boolean testOk = false;
		
		
		// Create employee
		HourlyEmployee employee = new HourlyEmployee("tom", "logan", "pavia", ContractTypes.hourly, PaymentMethods.pickup, 10);
		adminControl.addHourlyEmployee(employee);
		
		// Values
		Date timeCardDate = new Date(2017, 07, 01);
		int hoursWorked =  8;
		
		// Create employee
	
		TimeCard tcard = new TimeCard(employee, timeCardDate, hoursWorked);
		
		timeCardControl.addTimeCard(tcard);
		
		// See if salesReceipt has been added
		List<TimeCard> timeCardList = timeCardControl.getTimeCardsOfEmployee(employee);
		for(TimeCard timeCard : timeCardList){
			if(timeCard.getTimeCardDate().equals(timeCardDate) && timeCard.getHoursWorked() == hoursWorked)
			{
				testOk = true;
				break;
			}
			
		}
		Assert.assertTrue("addTimeCardTest - OK", testOk);
		
	}
}
