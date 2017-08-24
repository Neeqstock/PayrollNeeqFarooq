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
import model.FlatEmployee;
import model.HourlyEmployee;
import model.SalesReceipt;

@RunWith(Arquillian.class)
public class EmployeeTest {

	@Inject
	DatabaseCleaner databaseCleaner;
	@Inject
	AdminController adminController;

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
		adminController.addEmployee(flatEmployee);
		
		// See if Employee has been added
		ArrayList<FlatEmployee> flatEmployeeList = adminController.getFlatEmployees();
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
		adminController.addEmployee(hourlyEmployee);
		
		// See if Employee has been added
		ArrayList<HourlyEmployee> hourlyEmployeeList = adminController.getHourlyEmployees();
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
		adminController.addEmployee(flatEmployee);
		
		// Remove employee
		adminController.removeEmployee(flatEmployee);
		
		// See if Employee has been removed
		ArrayList<FlatEmployee> flatEmployeeList = adminController.getFlatEmployees();
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
		adminController.addEmployee(flatEmployee);
		
		// New values
		String newName = "NewName";
		String newSurname = "NewTestSurname";
		String newAddress = "NewTestAddress";
		String newPaymentMethod = PaymentMethods.mailed;
		float newSalary = 2000;
		float newCommissionRate = 30;
		
		adminController.editFlatEmployee(flatEmployee, newName, newSurname, newAddress, newPaymentMethod, newSalary, newCommissionRate);
		
		// See if Employee has been changed
		ArrayList<FlatEmployee> flatEmployeeList = adminController.getFlatEmployees();
		// Check
		for(FlatEmployee employee : flatEmployeeList){
			if(employee.getName().equalsIgnoreCase(newName) && 
					employee.getSurname().equalsIgnoreCase(newSurname) && 
					employee.getAddress().equalsIgnoreCase(newAddress) && 
					employee.getContractType().equalsIgnoreCase(newContractType) && 
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
