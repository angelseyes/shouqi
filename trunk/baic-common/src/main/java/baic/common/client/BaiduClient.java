package baic.common.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import baic.common.client.model.BaiduCoordinate;
import baic.common.client.model.BaiduResponse;
import baic.common.client.model.Coordinate;

public class BaiduClient {

	private static final Logger LOG = Logger.getLogger(BaiduClient.class);

	private static final String URL = "http://api.map.baidu.com";
	private static final int COORD_LIMIT = 80;
	private static final String AK = "78789afacf642b86b1495d85bf6b7ed6";

	public static List<Coordinate> transGPStoBaidu(List<Coordinate> coordList) {
		LOG.info("Trans from GPS to Baidu: " + coordList);
		List<Coordinate> baiduCoordList = new ArrayList<Coordinate>();
		try {
			for (int i = 0; i < coordList.size(); i += COORD_LIMIT) {
				baiduCoordList.addAll(transGPStoBaiduSub(
						coordList.subList(i, i + COORD_LIMIT > coordList.size() ? coordList.size() : i + COORD_LIMIT)));
			}
		} catch (Exception e) {
			LOG.error("Trans from GPS to Baidu failure!", e);
		}
		return baiduCoordList;
	}

	private static List<Coordinate> transGPStoBaiduSub(List<Coordinate> coordList) throws Exception {
		String result = getCoordsStr(coordList);
		WebTarget target = ClientFactory.getTarget(URL);
		target = target.path("geoconv/v1/").queryParam("coords", result).queryParam("ak", AK);
		Invocation.Builder builder = target.request();
		String respStr = builder.get(String.class);

		ObjectMapper om = new ObjectMapper();
		BaiduResponse resp = om.readValue(respStr, BaiduResponse.class);
		LOG.info("Trans GPS to Baidu: " + respStr);
		return getCoords(resp);
	}

	private static String getCoordsStr(List<Coordinate> coordList) throws Exception {
		if (coordList == null || coordList.size() == 0) {
			LOG.error("Failed to trans.");
			throw new Exception("Trans GPS to Baidu failure!");
		}
		String result = "";
		for (Coordinate coord : coordList) {
			if (!"".equals(result)) {
				result = result + ";";
			}
			result = result + coord.getLon() + "," + coord.getLat();
		}
		return result;
	}

	private static List<Coordinate> getCoords(BaiduResponse response) {
		ArrayList<Coordinate> coordinateList = new ArrayList<Coordinate>();
		for (BaiduCoordinate baiduCoord : response.getResult()) {
			Coordinate coordinate = new Coordinate();
			coordinate.setLon(baiduCoord.getX());
			coordinate.setLat(baiduCoord.getY());
			coordinateList.add(coordinate);
		}
		return coordinateList;
	}
}
