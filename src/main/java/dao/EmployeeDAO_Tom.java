package dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;
import model.FlatEmployee;
import model.HourlyEmployee;

@Stateless
public class EmployeeDAO_Tom {
	@PersistenceContext
	EntityManager em;

	/** * add new employee 
	 * @param employee */
	public void add(Employee employee) {
		em.persist(employee);
		em.flush();
	}

	/** * * @return all employees */
	public List<Employee> findAll() {
		List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();
		return employees;
	}

	/** * 
	 *  @param date 
	 * @return employees to be paid in given date */
	public List<Employee> findAlltoPay(Date date) {
		List<Employee> employees = em
				.createQuery("select e from Employee e where nextpayment = '" + date + "'", Employee.class)
				.getResultList();
		return employees;
	}

	/**
	 * @param date 	 
	 * @return all employees paid in a given date 	
	 */ 	
	public List<Employee> findAllPaid(Date date) {
		List<Employee> employees =
				em.createQuery("select e from Employee e where lastpayment = '" + date + "'",
						Employee.class).getResultList();
		return employees;
		
	}

	public List<HourlyEmployee> findAllHourlyEmployee(){
		List<HourlyEmployee> employees = em.createQuery("select p from HourlyEmployee p",
				HourlyEmployee.class).getResultList();
		return employees;
	}
	
	public List<FlatEmployee> findAllFlatEmployee(){
		List<FlatEmployee> employees = em.createQuery("select p from FlatEmployee p", 
				FlatEmployee.class).getResultList();
		return employees;
	}
	
	/** 	 
	 * remove a given employee 	 
	 * @param employee 	 
	 */ 	
	public void remove(Employee employee){			
		Employee emp = em.contains(employee) ? employee : em.merge(employee);
		em.createQuery("delete from TimeCard where emp_id ='" + emp.getId() + "'").executeUpdate();
		em.createQuery("delete from SalesCard where emp_id = '" + emp.getId() + "'").executeUpdate();
		em.createQuery("delete from ServiceCharge where emp_id = '" + emp.getId() + "'").executeUpdate();
		em.createQuery("delete from Payment where emp_id ='" + emp.getId() + "'").executeUpdate();
		em.createQuery("delete from Login where emp_id = '" + emp.getId() + "'").executeUpdate();
		em.createQuery("delete from Employee where id = '" + emp.getId() + "'").executeUpdate();
		}
	
	public Employee getEmployeeById(int id) {
		Employee emp = em.createQuery("select e from Employee e where id = " + id
				, Employee.class).getSingleResult();
		return emp;
		}
	
	public FlatEmployee findFlatEmployeeById(int emp_id){
		FlatEmployee employee = em.createQuery("select p from FlatEmployee p where id = "+emp_id,
				FlatEmployee.class).getSingleResult();
		return employee;
	}
	
	public HourlyEmployee findHourlyEmployeeById(int emp_id){
		HourlyEmployee employee = em.createQuery("select p from HourlyEmployee p where id = "+emp_id,
				HourlyEmployee.class).getSingleResult();
		return employee;
	}
	
	/** 	 
	 * @param emp_id 	 
	 * @return "Flat" or "Hourly" 	 
	 */ 	
	public String getEmployeeType(int emp_id) {
		Employee emp = getEmployeeById(emp_id);
		return emp.getContract();
		} 
	
	/** modifications allowed to employees 	 
	 * @param id
	 * @param method 	 
	 * @param address 	 
	 * @param account 	 
	 * @return 	
	 */ 	
	public Employee modify(int id, String method, String address, String account) {
		em.createQuery("update Employee set methodofpayment = '" + method +
				"', postaladdress = '" + address + "', bankaccount = '" + account +
				"' where id = " + id).executeUpdate();
		return getEmployeeById(id);
		}
	
	/**
	 * modification performed by a service charge 	 
	 * @param id 	 
	 * @param dues 	 
	 * @return 	 
	 */ 	
	public Employee modify(int id, float dues) {
		em.createQuery("update Employee set totaldues = " + dues +
				" where id = " + id).executeUpdate();
		return getEmployeeById(id);
		}
	
	/** 	 
	 * modifications performed by Admin 
	 * @param emp 	 
	 */ 	
	public void modify(Employee emp) { 		
		em.merge(emp); 		
		em.flush(); 	
	}
	
	/**
	 * @return all employees of the union
	*/
	public List<Employee> findAllUnion() { 		
		List<Employee> employees = 				
				em.createQuery("select e from Employee e where belongstounion = 'true'",
						Employee.class).getResultList();
		return employees;
	}
	
	public Employee findByName(String name, String surname, String contract){
		Employee emp = em.createQuery("select e from Employee e where"
				+ " name ='" +name + "' and surname = '"+ surname +"' and "
				+ "contract = '"+ contract + "'",Employee.class).getSingleResult();
		
		return emp;
	}
	
}
