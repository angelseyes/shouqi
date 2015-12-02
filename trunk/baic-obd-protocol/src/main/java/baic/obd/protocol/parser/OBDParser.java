package baic.obd.protocol.parser;

import java.util.HashMap;

import org.apache.log4j.Logger;

import baic.obd.protocol.model.body.OBD;
import baic.obd.protocol.util.ParseUtils;

public class OBDParser {

	private static final Logger LOG = Logger.getLogger(OBDParser.class);

	private static final String DTC = "DTC";

	public static OBD parseOBD(String content) {
		LOG.debug("Inside parseOBD.");
		OBD obd = new OBD();
		try {
			HashMap<String, String> obdMap = contentSplit(content);
			// Speed parse.
			String speedStr = obdMap.get("0D");
			if (speedStr != null) {
				int speed = Integer.parseInt(speedStr, 16);
				obd.setSpeed(speed);
			}
			// RPM parse.
			String rpmStr = obdMap.get("0C");
			if (rpmStr != null) {
				int A = Integer.parseInt(rpmStr.substring(0, 2), 16);
				int B = Integer.parseInt(rpmStr.substring(2), 16);
				int rpm = (A * 256 + B) / 4;
				obd.setRpm(rpm);
			}
			// Temperature parse.
			String temperatureStr = obdMap.get("05");
			if (temperatureStr != null) {
				int temperature = Integer.parseInt(temperatureStr, 16) - 40;
				obd.setTemperature(temperature);
			}
			// DTC parse.
			String dtcStr = obdMap.get(DTC);
			if (dtcStr != null) {
				obd.setDtcArray(parseDTC(dtcStr));
			}
		} catch (Exception e) {
			LOG.error("Parse OBD error.", e);
			return null;
		}
		return obd;
	}

	private static HashMap<String, String> contentSplit(String content) throws Exception {
		HashMap<String, String> obdMap = new HashMap<String, String>();

		for (int i = 0; i < content.length();) {
			int length = Integer.parseInt(content.substring(i, i += 2));
			String obdStr = content.substring(i, i + length * 2 < content.length() ? i + length * 2 : content.length());
			i += length * 2;
			if (obdStr.startsWith("41")) {
				obdMap.put(obdStr.substring(2, 4), obdStr.substring(4));
			} else if (obdStr.startsWith("43")) {
				obdMap.put(DTC, obdStr.substring(4));
			}
		}
		return obdMap;
	}

	private static String[] parseDTC(String dtcStr) throws Exception {
		LOG.debug("Inside parseDTC.");
		String[] dtcArray = new String[dtcStr.length() / 4];
		for (int i = 0; i < dtcArray.length; i++) {
			String dtcSubStr = dtcStr.substring(i * 4, i * 4 + 4);
			String binaryStr = ParseUtils.hexString2BinaryString(dtcSubStr.substring(0, 1));
			String startStr = binaryStr.substring(0, 2);
			if ("00".equals(startStr)) {
				startStr = "P";
			} else if ("01".equals(startStr)) {
				startStr = "C";
			} else if ("10".equals(startStr)) {
				startStr = "B";
			} else if ("11".equals(startStr)) {
				startStr = "U";
			}
			String endStr = binaryStr.substring(2, 4);
			if ("00".equals(endStr)) {
				endStr = "0";
			} else if ("01".equals(endStr)) {
				endStr = "1";
			} else if ("10".equals(endStr)) {
				endStr = "2";
			} else if ("11".equals(endStr)) {
				endStr = "3";
			}
			dtcSubStr = startStr + endStr + dtcSubStr.substring(1, 4);
			dtcArray[i] = dtcSubStr;
		}
		return dtcArray;
	}
}
