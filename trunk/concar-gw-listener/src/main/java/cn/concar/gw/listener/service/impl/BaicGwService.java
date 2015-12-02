package cn.concar.gw.listener.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import cn.concar.device.baic.protocol.model.Model;
import cn.concar.device.baic.protocol.model.ModelPackage;
import cn.concar.gw.listener.service.GwService;
import cn.concar.service.model.AllRecordModel;
import cn.concar.service.proxy.AllRecordServiceProxy;

public class BaicGwService extends GwService {

	private static final Logger LOG = Logger.getLogger(BaicGwService.class);

	@Override
	public void process(String message) {
		LOG.info("Inside process.");
		LOG.debug("Message: " + message);

		ObjectMapper mapper = new ObjectMapper();
		try {
			ModelPackage mp = mapper.readValue(message, ModelPackage.class);
			List<AllRecordModel> allRecords = new ArrayList<AllRecordModel>();
			for (Model model : mp.getModelList()) {
				allRecords.add(transTypeAllRecord(model));
			}
			AllRecordServiceProxy.addAllRecordBatch(allRecords);
		} catch (Exception e) {
			LOG.error("Failed to persist device message!", e);
			throw new RuntimeException(e);
		}
	}

	private AllRecordModel transTypeAllRecord(Model model) {
		AllRecordModel record = new AllRecordModel();
		record.setImei(model.getHead().getImei());
		record.setTraceTime(model.getHead().getTime());
		record.setLongitude(model.getBody().getGps().getLon());
		record.setLatitude(model.getBody().getGps().getLat());
		record.setAltitude(model.getBody().getGps().getAlt() == null ? null : model.getBody().getGps().getAlt());
		record.setHdop(
				model.getBody().getGps().getHdop() == null ? null : model.getBody().getGps().getHdop().doubleValue());
		record.setVdop(
				model.getBody().getGps().getVdop() == null ? null : model.getBody().getGps().getVdop().doubleValue());
		record.setMileage(model.getBody().getCot().getMileage());
		record.setSpeed(model.getBody().getGps().getSpeed());
		record.setTemperature(
				model.getBody().getObd().getTemperature() == null ? null : model.getBody().getObd().getTemperature());
		record.setRpm(model.getBody().getObd().getRpm() == null ? null : model.getBody().getObd().getRpm());
		record.setEngine(Boolean.valueOf(model.getBody().getDtt().getEngineStatus()));
		record.setVoltage(model.getBody().getAdc().getExtVol());
		record.setDtc(model.getBody().getObd().getDtcArray() == null ? null
				: parseDtcArray(model.getBody().getObd().getDtcArray()));
		LOG.debug("Trans type end: " + record.toString());
		return record;
	}

	private String parseDtcArray(String[] dtcArray) {
		String dtcString = null;
		for (String dtc : dtcArray) {
			if (dtcString == null) {
				dtcString = dtc;
			} else {
				dtcString = ";" + dtc;
			}
		}
		return dtcString;
	}

}
