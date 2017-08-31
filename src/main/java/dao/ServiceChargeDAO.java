package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;
import model.ServiceCharge;

@Stateless
public class ServiceChargeDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	public List<ServiceCharge> getServiceChargesOfEmployee(Employee employee) {
		List<ServiceCharge> serviceCharges = entityManager.createQuery("SELECT * FROM ServiceCharge WHERE employeeID ='" + employee.getEmployeeID() + "';", ServiceCharge.class).getResultList();
		return serviceCharges;
	}

	public void updateCharge(ServiceCharge serviceCharge) {
		entityManager.merge(serviceCharge); 		
		entityManager.flush();
	}

	public void addServiceCharge(ServiceCharge serviceCharge) {
		entityManager.persist(serviceCharge);
		entityManager.flush();
		
	}

}
