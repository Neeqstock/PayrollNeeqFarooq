package control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import dao.EmployeeDAO;
import model.FlatEmployee;
import model.HourlyEmployee;

@Stateless
public class AdminControl {
	
	@Inject
	EmployeeDAO employeeDAO;
	
	Logger logger = Logger.getLogger(AdminControl.class);

	@PostConstruct
	public void init() {
		logger.info("AdminControl initialized.");
	}

	public void addFlatEmployee(FlatEmployee employee) {
		employeeDAO.addFlatEmployee(employee);
	}
	
	public void addHourlyEmployee(HourlyEmployee employee) {
		employeeDAO.addHourlyEmployee(employee);
	}

	public List<FlatEmployee> getFlatEmployees() {
		return employeeDAO.getFlatEmployees();
	}

	public List<HourlyEmployee> getHourlyEmployees() {
		return employeeDAO.getHourlyEmployees();
	}

	public void removeFlatEmployee(FlatEmployee flatEmployee) {
		employeeDAO.purgeFlatEmployee(flatEmployee);
	}

	public void editFlatEmployee(FlatEmployee flatEmployee, String newName, String newSurname, String newAddress,
			String newPaymentMethod, String newBankAccount, float newSalary, float newCommissionRate) {
		employeeDAO.editFlatEmployee(flatEmployee, newName, newSurname, newAddress, newPaymentMethod, newBankAccount, newSalary, newCommissionRate);
	}

}
