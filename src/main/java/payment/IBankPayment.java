package payment;

/**
 * An interface for abstracting on how a bank payment is effectively made.
 * 
 * @author neeqstock
 *
 */
public interface IBankPayment {

	public void payFlatEmployee(String name, String surname, String bankAccount, float totalPayment, float flatPayments,
			float receiptPayments, float unionDeductions);

	public void payHourlyEmployee(String name, String surname, String bankAccount, float totalPayment,
			float hourlyPayments, float unionDeductions);
}