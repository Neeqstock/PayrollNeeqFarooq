package payment;

public class TestBankPayer implements IBankPayment {

	@Override
	public void payFlatEmployee(String name, String surname, String bankAccount, float totalPayment, float flatPayments, float receiptPayments,
			float unionDeductions) {
		// Do nothing.
	}

	@Override
	public void payHourlyEmployee(String name, String surname, String bankAccount, float totalPayment,
			float hourlyPayments, float unionDeductions) {
		// Do nothing.
		
	}

}
