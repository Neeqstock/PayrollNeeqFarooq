package control;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.ServiceChargeDAO;
import model.FlatEmployee;
import model.ServiceCharge;

@Stateless
public class ServiceChargeControl {

	@Inject
	ServiceChargeDAO serviceChargeDAO;
	
	public void addServiceCharge(ServiceCharge serviceCharge) {
		serviceChargeDAO.addServiceCharge(serviceCharge);
		
	}

	public List<ServiceCharge> getServiceChargesOfEmployee(FlatEmployee flatEmployee) {
		return serviceChargeDAO.getServiceChargesOfEmployee(flatEmployee);
	}

}
