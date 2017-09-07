package payment;

/**
 * A class that does actions suitable only for testing purposes. It does nothing
 * but printing into the console that the payment has been made.
 * 
 * @author neeqstock
 *
 */
public class TestBankPayer implements IBankPayment {

	@Override
	public void payFlatEmployee(String name, String surname, String bankAccount, float totalPayment, float flatPayments,
			float receiptPayments, float unionDeductions) {
		System.out.println(name + " " + surname + " has been paid via bank payment, amount: " + totalPayment);
	}

	@Override
	public void payHourlyEmployee(String name, String surname, String bankAccount, float totalPayment,
			float hourlyPayments, float unionDeductions) {
		System.out.println(name + " " + surname + " has been paid via bank payment, amount: " + totalPayment);

	}

}
