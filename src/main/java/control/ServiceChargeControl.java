package control;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ServiceChargeDAO;
import model.FlatEmployee;
import model.ServiceCharge;

/**
 * Controls the various actions available for the ServiceCharge entity.
 * 
 * @author neeqstock
 *
 */
@Stateless
public class ServiceChargeControl {

	@Inject
	ServiceChargeDAO serviceChargeDAO;
	
	public void addServiceCharge(ServiceCharge serviceCharge) {
		serviceChargeDAO.addServiceCharge(serviceCharge);
		
	}
	
	public void deleteServiceCharge(ServiceCharge serviceCharge){
		serviceChargeDAO.deleteCharge(serviceCharge);
	}

	public List<ServiceCharge> getServiceChargesOfEmployee(FlatEmployee flatEmployee) {
		return serviceChargeDAO.getServiceChargesOfEmployee(flatEmployee);
	}

	public List<ServiceCharge> getServiceCharges() {
		return serviceChargeDAO.getServiceCharges();
	}

}
