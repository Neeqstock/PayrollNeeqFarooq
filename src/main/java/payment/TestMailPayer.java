package payment;

public class TestMailPayer implements IMailPayment {

	@Override
	public void payFlatEmployee(String name, String surname, String address, float totalPayment, float flatPayments,
			float receiptPayments, float unionDeductions) {
		// Do nothing.
		
	}

	@Override
	public void payHourlyEmployee(String name, String surname, String address, float totalPayment, float hourlyPayments,
			float unionDeductions) {
		// Do nothing.
		
	}

}
