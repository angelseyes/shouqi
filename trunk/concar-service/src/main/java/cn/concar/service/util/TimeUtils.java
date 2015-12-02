package cn.concar.service.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimeUtils {

	private static final Logger LOG = Logger.getLogger(TimeUtils.class);

	public static Date dateToDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			Calendar c = Calendar.getInstance();
			// c.setTimeZone(TimeZone.getTimeZone(ServiceConstants.DEFAULT_TIMEZONE));
			c.setTime(date);
			return c.getTime();
		} catch (Exception e) {
			LOG.error("Parse date failure! date: " + date);
			return null;
		}
	}

	public static Date stringToDate(String dateStr, String format) {
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			// dateFormat.setTimeZone(TimeZone.getTimeZone(ServiceConstants.DEFAULT_TIMEZONE));
			return dateFormat.parse(dateStr);
		} catch (Exception e) {
			LOG.error("Parse date failure! dateStr: " + dateStr);
			return null;
		}
	}

	public static String dateToString(Date date, String format) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			// dateFormat.setTimeZone(TimeZone.getTimeZone(ServiceConstants.DEFAULT_TIMEZONE));
			return dateFormat.format(date);
		} catch (Exception e) {
			LOG.error("Parse date failure! date: " + date);
			return null;
		}
	}
}
