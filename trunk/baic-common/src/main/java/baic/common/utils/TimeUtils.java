package baic.common.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimeUtils {

	private static final Logger LOG = Logger.getLogger(TimeUtils.class);

	public static long timeBetween(Date startDate, Date endDate, int type) {
		LOG.info("Inside TimeUtils.timeBetween.");
		switch (type) {
		case Calendar.MINUTE:
			return endDate.getTime() / 60000 - startDate.getTime() / 60000;
		case Calendar.HOUR:
			return endDate.getTime() / 3600000 - startDate.getTime() / 3600000;
		case Calendar.DATE:
			return endDate.getTime() / 86400000 - startDate.getTime() / 86400000;
		case Calendar.MONTH: {
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startDate);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(startDate);
			return (endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)) * 12
					+ endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		}
		case Calendar.YEAR: {
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startDate);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(startDate);
			return endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		}
		}
		LOG.error("Type does not match, type: " + type);
		return 0;
	}
}
