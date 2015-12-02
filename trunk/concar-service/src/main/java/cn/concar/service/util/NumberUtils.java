package cn.concar.service.util;

import java.text.DecimalFormat;

public class NumberUtils {

	private static final DecimalFormat df2z = new DecimalFormat(".00");
	private static final DecimalFormat df3z = new DecimalFormat(".000");

	public static String parseFormatFor2Z(Double d) {
		return df2z.format(d);
	}

	public static String parseFormatFor3Z(Double d) {
		return df3z.format(d);
	}
}
