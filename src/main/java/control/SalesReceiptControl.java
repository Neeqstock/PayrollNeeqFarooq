package control;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.SalesReceiptDAO;
import model.FlatEmployee;
import model.SalesReceipt;

/**
 * Controls the various actions available for the SalesReceipt entity.
 * 
 * @author neeqstock
 *
 */
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

	public List<SalesReceipt> getSalesReceiptsBetweenDates(FlatEmployee flatEmployee, Date date1, Date date2) {
		return salesReceiptDAO.getSalesReceiptsBetweenDates(flatEmployee, date1, date2);
	}

	public void deleteSalesReceipt(SalesReceipt salesReceipt) {
		salesReceiptDAO.deleteSalesReceipt(salesReceipt);

	}

}
