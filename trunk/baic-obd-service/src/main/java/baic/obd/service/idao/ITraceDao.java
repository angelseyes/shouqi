package baic.obd.service.idao;

import java.util.List;

import baic.common.exception.ServiceException;
import baic.obd.model.TraceModel;

public interface ITraceDao {

	public List<TraceModel> findAllTraces()  throws ServiceException;
	
	public List<TraceModel> findTracesByImei(String imei) throws ServiceException;
	
}
