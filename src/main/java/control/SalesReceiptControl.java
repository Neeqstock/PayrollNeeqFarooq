package control;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.SalesReceiptDAO;
import model.FlatEmployee;
import model.SalesReceipt;

@Stateless
public class SalesReceiptControl {
	
	@Inject
	SalesReceiptDAO salesReceiptDAO;
	
	public void addSalesReceipt(SalesReceipt salesReceipt) {
		salesReceiptDAO.addSalesReceipt(salesReceipt);
	}

	public List<SalesReceipt> getSalesReceiptsOfEmployee(FlatEmployee flatEmployee) {
		return salesReceiptDAO.getSalesReceiptsOfEmployee(flatEmployee);
	}

}
