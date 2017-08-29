package test;


import java.sql.Date;
import java.util.ArrayList;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import constants.ContractTypes;
import constants.PaymentMethods;
import dao.DatabaseCleaner;
import junit.framework.Assert;
import model.Account;
import model.FlatEmployee;
import model.SalesReceipt;
import model.TimeCard;

/**
 * 
 * @author farooq
 *
 */
@RunWith(Arquillian.class)
public class TimeCardTest {

	@Inject	
	DatabaseCleaner databaseCleaner;
	@Inject
	AdminController adminController;
	@Inject
	TimeCardController timeCardController;
	
	@Before
	public void cleanDatabase(){
		databaseCleaner.clean();
	}
	
	@Test
	public void addTimeCardTest(){
		
		boolean testOk = false;
		
		
		// Create employee
		FlatEmployee employee = new FlatEmployee("tom", "logan", "pavia", ContractTypes.flat, PaymentMethods.mailed, 1200, 0);
		adminController.addEmployee(employee);
		
		// Values
		boolean isAdmin = false;
		
		Date timeCardDate = new Date(2017, 07, 01);
		int hoursWorked =  8;
		
		// Create employee
	
		TimeCard tcard = new TimeCard(employee, timeCardDate, hoursWorked);
		
		accountController.addTimeCard(tcard);
		
		// See if salesReceipt has been added
		ArrayList<TimeCard> timeCardList = timeCardController.getTimeCardsOfEmployee(employee);
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
