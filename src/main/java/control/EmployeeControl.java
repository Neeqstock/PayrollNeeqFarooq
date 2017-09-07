package control;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.EmployeeDAO;
import model.FlatEmployee;
import model.HourlyEmployee;

/**
 * Controls the various actions available for the Employee entity, and the employees' interface.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class EmployeeControl {
	
	@Inject
	EmployeeDAO employeeDAO;
	
	public FlatEmployee getFlatEmployee(int employeeID){
		return employeeDAO.getFlatEmployee(employeeID);
	}
	
	public HourlyEmployee getHourlyEmployee(int employeeID){
		return employeeDAO.getHourlyEmployee(employeeID);
	}
	
	public void updateFlatEmployee(FlatEmployee flatEmployee){
		employeeDAO.updateFlatEmployee(flatEmployee);
	}
	
	public void updateHourlyEmployee(HourlyEmployee hourlyEmployee){
		employeeDAO.updateHourlyEmployee(hourlyEmployee);
	}
}
