package cn.concar.ws.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import cn.concar.ws.model.in.Coordinate;

public class BaiduClientTest {
	private static final Logger LOG = Logger.getLogger(BaiduClientTest.class);

	@Test
	public void transGPStoBaiduTest() {
		List<Coordinate> coordList = new ArrayList<Coordinate>();
		coordList.add(new Coordinate(116.298848, 39.877184));
		for (int i = 0; i < 10; i++) {
			List<Coordinate> coords = BaiduClient.transGPStoBaidu(coordList);
			for (Coordinate coordinate : coords) {
				LOG.info(coordinate.toString());
			}
		}
	}
}
