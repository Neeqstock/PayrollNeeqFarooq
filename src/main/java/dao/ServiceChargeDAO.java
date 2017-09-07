package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;
import model.ServiceCharge;

/**
 * Database management for the ServiceCharge entity.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class ServiceChargeDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	public List<ServiceCharge> getServiceChargesOfEmployee(Employee employee) {
		List<ServiceCharge> serviceCharges = entityManager.createQuery("SELECT s FROM ServiceCharge s WHERE employeeID ='" + employee.getEmployeeID() + "'", ServiceCharge.class).getResultList();
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

	public List<ServiceCharge> getServiceCharges() {
		List<ServiceCharge> serviceCharges = entityManager.createQuery("SELECT s FROM ServiceCharge s", ServiceCharge.class).getResultList();
		return serviceCharges;
	}

	public void deleteCharge(ServiceCharge serviceCharge) {
		int ID = serviceCharge.getChargeID();
		entityManager.createQuery("DELETE FROM ServiceCharge WHERE chargeID = " + ID).executeUpdate();
		
	}

}
