package test;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import constants.ContractTypes;
import constants.PaymentMethods;
import control.AdminControl;
import dao.DatabaseCleaner;
import junit.framework.Assert;
import model.FlatEmployee;
import model.HourlyEmployee;

/**
 * Tests various functions on the Employee entity.
 * 
 * @author neeqstock
 *
 */
@RunWith(Arquillian.class)
public class EmployeeTest extends ArquillianTest {

	@Inject
	DatabaseCleaner databaseCleaner;
	@Inject
	AdminControl adminControl;

	@Before
	public void cleanDatabase() {
		databaseCleaner.clean();
	}

	@Test
	public void addFlatEmployee(){
		
		boolean testOk = false;
		
		// Values
		String name = "AddTestName";
		String surname = "AddTestSurname";
		String address = "AddTestAddress";
		String contractType = ContractTypes.flat;
		String paymentMethod = PaymentMethods.pickup;
		float salary = 1000;
		float commissionRate = 50;
		
		// Create employee
		FlatEmployee flatEmployee = new FlatEmployee(
				name,
				surname,
				address,
				contractType,
				paymentMethod,
				salary,
				commissionRate);
		adminControl.addFlatEmployee(flatEmployee);
		
		// See if Employee has been added
		List<FlatEmployee> flatEmployeeList = adminControl.getFlatEmployees();
		for(FlatEmployee employee : flatEmployeeList){
			if(employee.getName().equalsIgnoreCase(name) && 
					employee.getSurname().equalsIgnoreCase(surname) && 
					employee.getAddress().equalsIgnoreCase(address) && 
					employee.getContractType().equalsIgnoreCase(contractType) && 
					employee.getMethodOfPayment().equalsIgnoreCase(paymentMethod) && 
					employee.getSalary() == salary && 
					employee.getCommissionRate() == commissionRate){
				testOk = true;
				break;
			}
		}
		
		Assert.assertTrue("addFlatEmployeeTest - OK", testOk);
	}
	
	@Test
	public void addHourlyEmployee(){
		
		boolean testOk = false;
		
		// Values
		String name = "AddTestName";
		String surname = "AddTestSurname";
		String address = "AddTestAddress";
		String contractType = ContractTypes.hourly;
		String paymentMethod = PaymentMethods.pickup;
		float rate = 10;
		
		// Create employee
		HourlyEmployee hourlyEmployee = new HourlyEmployee(name, surname, address, contractType, paymentMethod, rate);
		adminControl.addHourlyEmployee(hourlyEmployee);
		
		// See if Employee has been added
		List<HourlyEmployee> hourlyEmployeeList = adminControl.getHourlyEmployees();
		for(HourlyEmployee employee : hourlyEmployeeList){
			if(employee.getName().equalsIgnoreCase(name) && 
					employee.getSurname().equalsIgnoreCase(surname) && 
					employee.getAddress().equalsIgnoreCase(address) && 
					employee.getContractType().equalsIgnoreCase(contractType) && 
					employee.getMethodOfPayment().equalsIgnoreCase(paymentMethod) && 
					employee.getRate() == rate){
				testOk = true;
				break;
			}
		}
		
		Assert.assertTrue("addHourlyEmployeeTest - OK", testOk);
	}
	
	@Test
	public void removeEmployeeTest(){
		
		boolean testOk = true;
		
		// Values
		String name = "RemoveTestName";
		String surname = "RemoveTestSurname";
		String address = "RemoveTestAddress";
		String contractType = ContractTypes.flat;
		String paymentMethod = PaymentMethods.pickup;
		float salary = 1000;
		float commissionRate = 50;
		
		// Create employee
		FlatEmployee flatEmployee = new FlatEmployee(
				name,
				surname,
				address,
				contractType,
				paymentMethod,
				salary,
				commissionRate);
		adminControl.addFlatEmployee(flatEmployee);
		
		// Remove employee
		adminControl.removeFlatEmployee(flatEmployee);
		
		// See if Employee has been removed
		List<FlatEmployee> flatEmployeeList = adminControl.getFlatEmployees();
		for(FlatEmployee employee : flatEmployeeList){
			if(employee.getName().equalsIgnoreCase(name) && 
					employee.getSurname().equalsIgnoreCase(surname) && 
					employee.getAddress().equalsIgnoreCase(address) && 
					employee.getContractType().equalsIgnoreCase(contractType) && 
					employee.getMethodOfPayment().equalsIgnoreCase(paymentMethod) && 
					employee.getSalary() == salary && 
					employee.getCommissionRate() == commissionRate){
				testOk = false;
				break;
			}
		}
		
		Assert.assertTrue("removeEmployeeTest - OK", testOk);
	}
	
	@Test
	public void changeFlatEmployeeDetailsTest(){
		
		boolean testOk = false;
		
		// Initial Values
		String name = "ChangeName";
		String surname = "ChangeTestSurname";
		String address = "ChangeTestAddress";
		String contractType = ContractTypes.flat;
		String paymentMethod = PaymentMethods.pickup;
		float salary = 1000;
		float commissionRate = 50;
		
		// Create employee
		FlatEmployee flatEmployee = new FlatEmployee(
				name,
				surname,
				address,
				contractType,
				paymentMethod,
				salary,
				commissionRate);
		adminControl.addFlatEmployee(flatEmployee);
		
		// New values
		String newName = "NewName";
		String newSurname = "NewTestSurname";
		String newAddress = "NewTestAddress";
		String newPaymentMethod = PaymentMethods.mailed;
		String newBankAccount = "";
		float newSalary = 2000;
		float newCommissionRate = 30;
		
		flatEmployee.setName(newName);
		flatEmployee.setSurname(newSurname);
		flatEmployee.setAddress(newAddress);
		flatEmployee.setMethodOfPayment(newPaymentMethod);
		flatEmployee.setBankAccount(newBankAccount);
		flatEmployee.setSalary(newSalary);
		flatEmployee.setCommissionRate(newCommissionRate);
		
		adminControl.updateFlatEmployee(flatEmployee);
		
		// See if Employee has been changed
		List<FlatEmployee> flatEmployeeList = adminControl.getFlatEmployees();
		// Check
		for(FlatEmployee employee : flatEmployeeList){
			if(employee.getName().equalsIgnoreCase(newName) && 
					employee.getSurname().equalsIgnoreCase(newSurname) && 
					employee.getAddress().equalsIgnoreCase(newAddress) && 
					employee.getMethodOfPayment().equalsIgnoreCase(newPaymentMethod) && 
					employee.getSalary() == newSalary && 
					employee.getCommissionRate() == newCommissionRate){
				testOk = true;
				break;
			}
		}
		// CounterCheck
		for(FlatEmployee employee : flatEmployeeList){
			if(employee.getName().equalsIgnoreCase(name) && 
					employee.getSurname().equalsIgnoreCase(surname) && 
					employee.getAddress().equalsIgnoreCase(address) && 
					employee.getContractType().equalsIgnoreCase(contractType) && 
					employee.getMethodOfPayment().equalsIgnoreCase(paymentMethod) && 
					employee.getSalary() == salary && 
					employee.getCommissionRate() == commissionRate){
				testOk = false;
				break;
			}
		}
		
		Assert.assertTrue("changeFlatEmployeeDetails - OK", testOk);
	}
}
