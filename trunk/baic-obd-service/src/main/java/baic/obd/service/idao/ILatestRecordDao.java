package baic.obd.service.idao;

import baic.common.exception.ServiceException;
import baic.obd.model.LatestRecordModel;

public interface ILatestRecordDao {

	public LatestRecordModel findLatestRecordByImei(String imei) throws ServiceException;

}
