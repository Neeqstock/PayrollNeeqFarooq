package payment;

public interface IBankPayment {
	
	public void payFlatEmployee(String name, String surname, String bankAccount, float totalPayment, float flatPayments, float receiptPayments,
			float unionDeductions);

	public void payHourlyEmployee(String name, String surname, String bankAccount, float totalPayment,
			float hourlyPayments, float unionDeductions);
}