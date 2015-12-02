package cn.concar.service.proxy;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class BaseTestUnit {
	private static final Logger LOG = Logger.getLogger(BaseTestUnit.class);

	public String toJson(Object obj){		
		ObjectMapper om = new ObjectMapper();
		try {
			final byte[] data = om.writeValueAsBytes(obj);
			String respStr = new String(data, "UTF-8");;
			return respStr;
		} catch (Exception e) {
			LOG.error("Failed to convert the marker objects into JSON format!", e);
			return null;
		}
	}
}
