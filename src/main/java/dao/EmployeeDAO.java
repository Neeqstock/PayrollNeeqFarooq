package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import constants.ContractTypes;
import model.Employee;
import model.FlatEmployee;
import model.HourlyEmployee;

/**
 * Database management for the Employee, FlatEmployee ad HourlyEmployee
 * entities.
 * 
 * @author neeqstock
 *
 */
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
		List<FlatEmployee> employees = entityManager
				.createQuery("SELECT e FROM FlatEmployee e WHERE ContractType='" + ContractTypes.flat + "'",
						FlatEmployee.class)
				.getResultList();
		return employees;
	}

	public List<HourlyEmployee> getHourlyEmployees() {
		List<HourlyEmployee> employees = entityManager
				.createQuery("SELECT e FROM HourlyEmployee e WHERE ContractType='" + ContractTypes.hourly + "'",
						HourlyEmployee.class)
				.getResultList();
		return employees;
	}

	public void purgeFlatEmployee(FlatEmployee flatEmployee) {
		FlatEmployee emp = entityManager.contains(flatEmployee) ? flatEmployee : entityManager.merge(flatEmployee);
		int ID = emp.getEmployeeID();

		entityManager.createQuery("DELETE FROM Account WHERE employeeID ='" + ID + "'").executeUpdate();
		entityManager.createQuery("DELETE FROM Payment WHERE employeeID ='" + ID + "'").executeUpdate();
		entityManager.createQuery("DELETE FROM SalesReceipt WHERE employeeID ='" + ID + "'").executeUpdate();
		entityManager.createQuery("DELETE FROM ServiceCharge WHERE employeeID ='" + ID + "'").executeUpdate();
		entityManager.createQuery("DELETE FROM FlatEmployee WHERE employeeID ='" + ID + "'").executeUpdate();
	}

	public void purgeHourlyEmployee(HourlyEmployee hourlyEmployee) {
		int ID = hourlyEmployee.getEmployeeID();

		entityManager.createQuery("DELETE FROM Account WHERE employeeID ='" + ID + "'").executeUpdate();
		entityManager.createQuery("DELETE FROM Payment WHERE employeeID ='" + ID + "'").executeUpdate();
		entityManager.createQuery("DELETE FROM TimeCard WHERE employeeID ='" + ID + "'").executeUpdate();
		entityManager.createQuery("DELETE FROM ServiceCharge WHERE employeeID ='" + ID + "'").executeUpdate();
		entityManager.createQuery("DELETE FROM Employee WHERE employeeID ='" + ID + "'").executeUpdate();
	}

	public void updateFlatEmployee(FlatEmployee employee) {
		entityManager.merge(employee);
		entityManager.flush();
	}

	public void updateHourlyEmployee(HourlyEmployee employee) {
		entityManager.merge(employee);
		entityManager.flush();
	}

	public int retrieveEmployeeID(Employee employee) {
		Employee dbEmployee = entityManager.contains(employee) ? employee : entityManager.merge(employee);
		return dbEmployee.getEmployeeID();
	}

	public Employee getEmployee(int employeeID) {
		return entityManager.createQuery("SELECT e FROM Employee e WHERE employeeID =" + employeeID, Employee.class)
				.getSingleResult();
	}

	public FlatEmployee getFlatEmployee(int employeeID) {
		return entityManager
				.createQuery("SELECT e FROM FlatEmployee e WHERE employeeID =" + employeeID, FlatEmployee.class)
				.getSingleResult();
	}

	public HourlyEmployee getHourlyEmployee(int employeeID) {
		return entityManager
				.createQuery("SELECT e FROM HourlyEmployee e WHERE employeeID =" + employeeID, HourlyEmployee.class)
				.getSingleResult();
	}
}
