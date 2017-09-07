package constants;

/**
 * Constants to manage HourlyEmployees payments, including "what do we mean by
 * extra hours".
 * 
 * @author neeqstock
 *
 */
public class HourlyEmployeeConstants {
	/**
	 * If the hours worked exceed "extra", the difference will be counted as
	 * extra hours.
	 */
	public static final int extra = 8;
	/**
	 * The value given to extra hours.
	 */
	public static final float extraAmount = 1.5f;
}
