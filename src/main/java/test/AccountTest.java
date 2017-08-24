package test;

import java.util.ArrayList;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dao.DatabaseCleaner;
import junit.framework.Assert;
import model.Account;
import model.FlatEmployee;
import model.SalesReceipt;

/**
 * 
 * @author farooq
 *
 */

@RunWith(Arquillian.class)
public class AccountTest {

	@Inject
	DatabaseCleaner databaseCleaner;
	
	@Before
	public void cleanDatabase(){
		databaseCleaner.clean();
	}
	
	@Test
	public void addAccountTest(){
		
		boolean testOk = false;
		
		
		// Create employee
		FlatEmployee employee = new FlatEmployee("john", "sena", "pavia", "Flat", "Mailed", 1300, 0);
		adminController.addEmployee(employee);
		
		// Values
		boolean isAdmin = false;
		String username = "TestAccount";
		String password = "TestPassword";
		
		// Create employee
		Account account = new Account(isAdmin, username, password, employee);
		
		accountController.addAccount(account);
		
		// See if salesReceipt has been added
		ArrayList<Account> accountList = accountController.getAccounts();
		for(Account accountCheck : accountList){
			if(accountCheck.getUserName().equalsIgnoreCase(username) && accountCheck.isAdmin() == isAdmin && accountCheck.getPassword().equalsIgnoreCase(password))
			{
				testOk = true;
				break;
			}
		}
		Assert.assertTrue("addAccountTest - OK", testOk);
		
	}
}

