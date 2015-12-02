package baic.obd.gateway.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import baic.obd.gateway.dao.AllRecordDao;
import baic.obd.gateway.dao.BeanFactory;
import baic.obd.gateway.model.AllRecordModel;
import baic.obd.protocol.model.Model;
import baic.obd.protocol.model.ModelPackage;

public class ServiceController extends BaseController {

	private static final Logger LOG = Logger.getLogger(ServiceController.class);

	private static AllRecordDao allRecordDao = BeanFactory.getBean(AllRecordDao.class);

	public ServiceController(ModelPackage mp) {
		this.mp = mp;
	}

	@Override
	public boolean persistData() {
		LOG.info("Inside Pesisting data: " + mp.toString());
		return persistData(mp.getModelList());
	}

	private boolean persistData(List<Model> modelList) {
		LOG.info("Inside Persistion data, model size: " + modelList.size());
		try {
			List<AllRecordModel> allRecords = new ArrayList<AllRecordModel>();
			for (Model model : modelList) {
				allRecords.add(transTypeAllRecord(model));
			}
			LOG.info("Persised successfully! Starting add batch");
			if (allRecords.size() > 0) {
				allRecordDao.addAllRecordBatch(allRecords);
				LOG.info("Add batch successfully!");
			} else {
				LOG.info("No record!");
			}
			return true;
		} catch (Exception e) {
			LOG.error("Persist data error. ", e);
			return false;
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
