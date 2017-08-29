package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import constants.ContractTypes;
import model.Employee;
import model.FlatEmployee;
import model.HourlyEmployee;

@Singleton
@Stateless
public class EmployeeDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	public void addFlatEmployee(FlatEmployee employee) {
		entityManager.persist(employee);
		entityManager.flush();
	}

	public void addHourlyEmployee(HourlyEmployee employee) {
		entityManager.persist(employee);
		entityManager.flush();
		
	}

	public List<FlatEmployee> getFlatEmployees() {
		List<FlatEmployee> employees = entityManager.createQuery("SELECT * FROM Employee WHERE ContractType='" + ContractTypes.flat + "';", FlatEmployee.class).getResultList();
		return employees;
	}

	public List<HourlyEmployee> getHourlyEmployees() {
		List<HourlyEmployee> employees = entityManager.createQuery("SELECT * FROM Employee WHERE ContractType='" + ContractTypes.flat + "';", HourlyEmployee.class).getResultList();
		return employees;
	}

	public void purgeFlatEmployee(FlatEmployee flatEmployee) {
		int ID = retrieveEmployeeID(flatEmployee);
		entityManager.createQuery("DELETE FROM Employee WHERE employeeID ='" + ID + "';").executeUpdate();
		entityManager.createQuery("DELETE FROM Payment WHERE employeeID ='" + ID + "';").executeUpdate();
		entityManager.createQuery("DELETE FROM SalesReceipt WHERE employeeID ='" + ID + "';").executeUpdate();
		entityManager.createQuery("DELETE FROM ServiceCharge WHERE employeeID ='" + ID + "';").executeUpdate();
	}
	
	public void purgeHourlyEmployee(HourlyEmployee hourlyEmployee) {
		int ID = retrieveEmployeeID(hourlyEmployee);
		entityManager.createQuery("DELETE FROM Employee WHERE employeeID ='" + ID + "';").executeUpdate();
		entityManager.createQuery("DELETE FROM Payment WHERE employeeID ='" + ID + "';").executeUpdate();
		entityManager.createQuery("DELETE FROM TimeCard WHERE employeeID ='" + ID + "';").executeUpdate();
	}

	public int editFlatEmployee(FlatEmployee flatEmployee, String newName, String newSurname, String newAddress,
			String newPaymentMethod, String newBankAccount, float newSalary, float newCommissionRate) {
		Employee employee = entityManager.contains(flatEmployee) ? flatEmployee : entityManager.merge(flatEmployee);
		entityManager.createQuery("UPDATE Employee SET "
				+ "name = '" + newName + "',"
				+ "surname = '" + newSurname + "',"
				+ "address = '" + newAddress + "',"
				+ "paymentMethod = '" + newPaymentMethod + "',"
				+ "bankAccount = '" + newBankAccount + "',"
				+ "salary = '" + newSalary + "',"
				+ "commissionRate = '" + newCommissionRate + "',"
				+ "WHERE employeeID = " + employee.getEmployeeID()).executeUpdate();
				return 0;
	}
	
	public int editFlatEmployee(int employeeID, FlatEmployee modifiedFlatEmployee) {
		entityManager.createQuery("UPDATE Employee SET "
				+ "name = '" + modifiedFlatEmployee.getName() + "',"
				+ "surname = '" + modifiedFlatEmployee.getSurname() + "',"
				+ "address = '" + modifiedFlatEmployee.getAddress() + "',"
				+ "paymentMethod = '" + modifiedFlatEmployee.getMethodOfPayment() + "',"
				+ "bankAccount = '" + modifiedFlatEmployee.getBankAccount() + "',"
				+ "salary = '" + modifiedFlatEmployee.getSalary() + "',"
				+ "commissionRate = '" + modifiedFlatEmployee.getCommissionRate() + "',"
				+ "WHERE employeeID = " + employeeID).executeUpdate();
				return 0;
	}
	
	public int editHourlyEmployee(int employeeID, HourlyEmployee modifiedHourlyEmployee) {
		entityManager.createQuery("UPDATE Employee SET "
				+ "name = '" + modifiedHourlyEmployee.getName() + "',"
				+ "surname = '" + modifiedHourlyEmployee.getSurname() + "',"
				+ "address = '" + modifiedHourlyEmployee.getAddress() + "',"
				+ "paymentMethod = '" + modifiedHourlyEmployee.getMethodOfPayment() + "',"
				+ "bankAccount = '" + modifiedHourlyEmployee.getBankAccount() + "',"
				+ "rate = '" + modifiedHourlyEmployee.getRate() + "',"
				+ "WHERE employeeID = " + employeeID).executeUpdate();
				return 0;
	}
	
	public void updateFlatEmployee(FlatEmployee employee){
		entityManager.merge(employee); 		
		entityManager.flush();
	}
	
	public void updateHourlyEmployee(HourlyEmployee employee) {
		entityManager.merge(employee); 		
		entityManager.flush();
	}
	
	public int retrieveEmployeeID(Employee employee){
		Employee dbEmployee = entityManager.contains(employee) ? employee : entityManager.merge(employee);
		return dbEmployee.getEmployeeID();
	}
	
}
