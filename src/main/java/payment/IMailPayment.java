package payment;

/**
 * An interface for abstracting on how a mail payment is effectively made.
 * 
 * @author neeqstock
 *
 */
public interface IMailPayment {

	public void payFlatEmployee(String name, String surname, String address, float totalPayment, float flatPayments,
			float receiptPayments, float unionDeductions);

	public void payHourlyEmployee(String name, String surname, String address, float totalPayment, float hourlyPayments,
			float unionDeductions);

}
