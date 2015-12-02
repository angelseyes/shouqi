package cn.concar.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.db.dao.CommonDao;
import cn.concar.service.exception.ServiceException;
import cn.concar.service.model.DtcMessageModel;

@Service
public class DtcMessageService {
	private static final Logger LOG = Logger.getLogger(DtcMessageService.class);
	@Autowired
	private CommonDao commonDao;
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String findDtcMessageByDtc(String dtc) throws ServiceException {
		LOG.info("Inside DtcMessageService.findDtcMessageByDtc.");
		try {
			String dtcMessage = null;
			List<DtcMessageModel> dtcMessages = (List<DtcMessageModel>) commonDao.findByNativeSql(findDtcMessageByDtc, null,
					DtcMessageModel.class);
			if (dtcMessages != null && dtcMessages.size() > 0) {
				dtcMessage = dtcMessages.get(0).getDtcMessage();
			}
			return dtcMessage;
		} catch (Exception e) {
			LOG.error("Failed in DtcMessageService.findDtcMessageByDtc.", e);
			throw new ServiceException("DtcMessageService.findDtcMessageByDtc failure due to unexpeted error!", e);
		}
	}

	private static final String findDtcMessageByDtc = "select " 
			+ "dtc_message dtcMessage " 
			+ "from dtc_message d "
			+ "where d.dtc = :dtc";
}
