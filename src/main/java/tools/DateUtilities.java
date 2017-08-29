package tools;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import javax.ejb.Stateless;

@Stateless
public class DateUtilities {

	/**
	 * @return a sql.Date with today's date
	 */
	public Date getToday() {
		LocalDate d = LocalDate.now();
		Date sql = Date.valueOf(d);

		return sql;
	}

	/**
	 * @return a sql.Date with yesterday's date
	 */
	public Date getYesterday() {
		LocalDate d = LocalDate.now();
		d = d.minusDays(1);
		Date sql = Date.valueOf(d);

		return sql;
	}

	
	
	/**
	 * 
	 * @return a sql.Date with the end of this month (last Friday)
	 */
	public Date getThisEndOfMonth() {
		LocalDate d = LocalDate.now();
		d = getEndOfMonth(d);
		Date sql = Date.valueOf(d);

		return sql;
	}

	/**
	 * 
	 * @return a sql.Date with the end of next month
	 */
	public Date getNextMonthEndOfMonth() {
		LocalDate d = LocalDate.now();
		int nextMonth = d.getMonthValue() + 1;

		if (nextMonth > 12) {
			nextMonth = 1;
			d = d.withYear(d.getYear() + 1);
		}

		d = d.withMonth(nextMonth);
		d = getEndOfMonth(d);
		Date sql = Date.valueOf(d);

		return sql;
	}

	/**
	 * 
	 * @return a sql.Date with next Friday
	 */
	public Date getNextFriday() {
		LocalDate d = LocalDate.now();
		d = d.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		Date sql = Date.valueOf(d);

		return sql;
	}

	/**
	 * 
	 * @return a sql.Date with other Friday
	 */
	public Date getOtherFriday() {
		LocalDate d = LocalDate.now();
		d = d.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		d = d.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		Date sql = Date.valueOf(d);

		return sql;
	}

	private LocalDate getEndOfMonth(LocalDate d) {
		d = d.with(TemporalAdjusters.lastDayOfMonth());
		if (d.getDayOfWeek().equals(DayOfWeek.SATURDAY) || d.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {

			d = d.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
		}
		return d;
	}
	
}
