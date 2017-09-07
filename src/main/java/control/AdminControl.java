package control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import dao.EmployeeDAO;
import model.FlatEmployee;
import model.HourlyEmployee;

/**
 * Controls the various actions available for the administrator's interface.
 * 
 * @author neeqstock
 *
 */
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
	
	public void updateFlatEmployee(FlatEmployee flatEmployee){
		employeeDAO.updateFlatEmployee(flatEmployee);
	}
	
	public void updateHourlyEmployee(HourlyEmployee hourlyEmployee){
		employeeDAO.updateHourlyEmployee(hourlyEmployee);
	}

	public void removeHourlyEmployee(HourlyEmployee hourlyEmployee) {
		employeeDAO.purgeHourlyEmployee(hourlyEmployee);
	}

}
