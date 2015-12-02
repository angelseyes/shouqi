package cn.concar.gw.socket.baic.protocol;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import cn.concar.device.baic.protocol.ADC;
import cn.concar.device.baic.protocol.COT;
import cn.concar.device.baic.protocol.DTT;
import cn.concar.device.baic.protocol.ETD;
import cn.concar.device.baic.protocol.FUL;
import cn.concar.device.baic.protocol.GPS;
import cn.concar.device.baic.protocol.GSM;
import cn.concar.device.baic.protocol.OBD;
import cn.concar.device.baic.protocol.SYS;
import cn.concar.device.baic.protocol.model.Body;
import cn.concar.device.baic.protocol.model.Head;
import cn.concar.device.baic.protocol.model.Model;
import cn.concar.device.baic.protocol.model.ModelPackage;
import cn.concar.gw.socket.baic.util.Constants;
import cn.concar.gw.socket.baic.util.ParseUtils;

public class Parser {

	private static final Logger LOG = Logger.getLogger(Parser.class);

	public synchronized static ModelPackage parse(String rawData) {
		LOG.debug("Inside parse, rawData: " + rawData);
		ModelPackage modelPackage = new ModelPackage();
		try {
			List<Model> modelList = new ArrayList<Model>();
			String[] packageDataArray = rawData.split("#");

			for (String packageData : packageDataArray) {
				String[] dataArray = packageData.split("\\$");

				for (String data : dataArray) {
					Model model = new Model();

					String[] partArray = data.split(",");
					// Empty Package
					if (partArray.length == Constants.EMPTY_LENGTH) {
						continue;
					}
					int index = 0;
					if (data.contains(Head.VER)) {
						modelPackage.setImei(partArray[1]);
						index = 2;
					}

					// Head with Body.
					LOG.debug("Parsing Head.");
					Head head = new Head();
					head.setImei(modelPackage.getImei());
					Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
					String[] timeArray = new String[6];
					for (int i = 0; i < partArray[index].length(); i += 2) {
						timeArray[i / 2] = partArray[index].substring(i, i + 2);
					}
					index++;
					calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
					calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
					calendar.set(Calendar.SECOND, Integer.parseInt(timeArray[2]));
					calendar.set(Calendar.DATE, Integer.parseInt(timeArray[3]));
					calendar.set(Calendar.MONTH, Integer.parseInt(timeArray[4]) - 1);
					calendar.set(Calendar.YEAR, 2000 + Integer.parseInt(timeArray[5]));
					head.setTime(calendar.getTime());
//					if (partArray[index] != null && !"".equals(partArray[index])) {
//						String binStr = ParseUtils.hexString2BinaryString(partArray[index]);
//						head.setEventId(Integer.valueOf(binStr.substring(2), 2));
//						if (binStr.length() >= 8) {
//							head.setEventFlag(binStr.substring(binStr.length() - 8, binStr.length() - 7));
//						}
//					}
					model.setHead(head);
					index++;
					// Body.
					LOG.debug("Parsing Body.");
					Body body = new Body();
					for (; index < partArray.length; index++) {
						String part = partArray[index];
						String[] contentArray = part.split(":");
						if (contentArray.length != 2) {
							continue;
						}
						String[] paramArray = contentArray[1].split(";");
						if (SYS.FLAG.equals(contentArray[0])) {
							body.setSys(parseSYS(paramArray));
						} else if (GPS.FLAG.equals(contentArray[0])) {
							body.setGps(parseGPS(paramArray));
						} else if (GSM.FLAG.equals(contentArray[0])) {
							body.setGsmList(parseGSMList(contentArray[1]));
						} else if (COT.FLAG.equals(contentArray[0])) {
							body.setCot(parseCOT(paramArray));
						} else if (ADC.FLAG.equals(contentArray[0])) {
							body.setAdc(parseADC(paramArray));
						} else if (DTT.FLAG.equals(contentArray[0])) {
							body.setDtt(parseDTT(paramArray));
						} else if (ETD.FLAG.equals(contentArray[0])) {
							body.setEtd(parseETD(contentArray[1]));
						} else if (OBD.FLAG.equals(contentArray[0])) {
							body.setObd(OBDParser.parseOBD(contentArray[1]));
						} else if (FUL.FLAG.equals(contentArray[0])) {
							body.setFul(parseFUL(paramArray));
						}
					}
					model.setBody(body);
					modelList.add(model);
				}
			}
			LOG.info("End parse, model list size: " + modelList.size());
			modelPackage.setModelList(modelList);
		} catch (Exception e) {
			LOG.error("Parse model package error!", e);
		}
		return modelPackage;
	}

	private static SYS parseSYS(String[] paramArray) {
		LOG.debug("Inside parseSYS");
		SYS sys = new SYS();
		try {
			sys.setDeviceName(paramArray[0]);
			sys.setFwVer(paramArray[1]);
			sys.setHwVer(paramArray[2]);
		} catch (Exception e) {
			LOG.error("Parse SYS error.", e);
			return null;
		}
		return sys;
	}

	private static GPS parseGPS(String[] paramArray) {
		LOG.debug("Inside parseGPS");
		GPS gps = new GPS();
		try {
			if (GPS.SET.equals(paramArray[0])) {
				gps.setIsSet(true);
			} else if (GPS.NOT_SET.equals(paramArray[0])) {
				gps.setIsSet(false);
			}
			gps.setSatelliteNum(Integer.parseInt(paramArray[1]));
			Double lat = Double.parseDouble(paramArray[2].substring(1));
			if (GPS.SOUTH.equals(paramArray[2].substring(0, 1))) {
				lat = -lat;
			}
			gps.setLat(lat);
			Double lon = Double.parseDouble(paramArray[3].substring(1));
			if (GPS.WEST.equals(paramArray[3].substring(0, 1))) {
				lon = -lon;
			}
			gps.setLon(lon);
			gps.setSpeed(Integer.parseInt(paramArray[4]));
			gps.setBearing(Integer.parseInt(paramArray[5]));
			gps.setAlt(Integer.parseInt(paramArray[6]));
			if (paramArray.length >= 8) {
				gps.setHdop(Float.parseFloat(paramArray[7]));
			}
			if (paramArray.length >= 9) {
				gps.setVdop(Float.parseFloat(paramArray[8]));
			}
		} catch (Exception e) {
			LOG.error("Parse GPS error.", e);
			return null;
		}
		return gps;
	}

	private static List<GSM> parseGSMList(String content) {
		LOG.debug("Inside parseGSMList");
		List<GSM> gsmList = new ArrayList<GSM>();
		try {
			String[] sequenceArray = content.split(";;");
			for (String sequence : sequenceArray) {
				GSM gsm = new GSM();
				String[] partArray = sequence.split(";");
				gsm.setReg(Integer.parseInt(partArray[0]));
				gsm.setCsq(Integer.parseInt(partArray[1]));
				gsm.setMcc(partArray[2]);
				gsm.setMnc(partArray[3]);
				gsm.setLac(partArray[4]);
				gsm.setCid(partArray[5]);
				gsm.setRssi(Integer.parseInt(partArray[6]));
				gsmList.add(gsm);
			}
		} catch (Exception e) {
			LOG.error("Parse GSM error.", e);
			return null;
		}
		return gsmList;
	}

	private static COT parseCOT(String[] paramArray) {
		LOG.debug("Inside parseCOT");
		COT cot = new COT();
		try {
			cot.setMileage(Long.parseLong(paramArray[0]));
			if (paramArray.length >= 2) {
				String[] segmentArray = paramArray[1].split("-");
				cot.setEngineRunTime(Long.parseLong(segmentArray[0]) * 60 * 60 + Long.parseLong(segmentArray[1]) * 60
				        + Long.parseLong(segmentArray[2]));
			}
		} catch (Exception e) {
			LOG.error("Parse COT error.", e);
			return null;
		}
		return cot;
	}

	private static ADC parseADC(String[] paramArray) {
		LOG.debug("Inside parseADC");
		ADC adc = new ADC();
		try {
			adc.setExtVol(Double.parseDouble(paramArray[0]));
			adc.setIntVol(Double.parseDouble(paramArray[1]));
		} catch (Exception e) {
			LOG.error("Parse ADC error.", e);
			return null;
		}
		return adc;
	}

	private static DTT parseDTT(String[] paramArray) {
		DTT dtt = new DTT();
		String statusStr = ParseUtils.hexString2BinaryString(paramArray[0]);
		if (statusStr.length() >= 14) {
			dtt.setEngineStatus(statusStr.substring(statusStr.length() - 14, statusStr.length() - 13));
		}
		return dtt;
	}

	private static ETD parseETD(String content) {
		ETD etd = new ETD();
		etd.setEventData(content);
		return etd;
	}

	private static FUL parseFUL(String[] paramArray) {
		LOG.debug("Inside parseFUL");
		FUL ful = new FUL();
		try {
			ful.setFuelConsume(Long.parseLong(paramArray[0]));
		} catch (Exception e) {
			LOG.error("Parse FUL error.", e);
			return null;
		}
		return ful;
	}
}
