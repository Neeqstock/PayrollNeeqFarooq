package tools;

/**
 * A conversion tool that was made to fix conversion problems between
 * java.util.Date and java.sql.Date. Then I've discovered that this was pretty
 * useless since "sql.Date" inherits from "util.Date", but it was too late :). A
 * refactor will fix everything. WARNING: it takes in account only the fields *
 * "Year", "Month" and "Date". Does not convert minutes, seconds, etc.
 * 
 * @author neeqstock
 *
 */
public class DateTypesWrapper {

	public java.util.Date sqlToUtil(java.sql.Date sqlDate) {
		if (sqlDate != null) {
			@SuppressWarnings("deprecation")
			java.util.Date utilDate = new java.util.Date(sqlDate.getYear(), sqlDate.getMonth(), sqlDate.getDate());
			return utilDate;
		} else {
			return null;
		}
	}

	public java.sql.Date utilToSql(java.util.Date utilDate) {
		if (utilDate != null) {
			@SuppressWarnings("deprecation")
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getYear(), utilDate.getMonth(), utilDate.getDate());
			return sqlDate;
		} else {
			return null;
		}
	}
}
