package control;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.PaymentDAO;
import model.Employee;
import model.Payment;

@Stateless
public class PaymentControl {

	@Inject
	PaymentDAO paymentDAO;

	public List<Payment> getPaymentsOfEmployee(Employee employee) {
		return paymentDAO.getPaymentsOfEmployee(employee);
	}
}
