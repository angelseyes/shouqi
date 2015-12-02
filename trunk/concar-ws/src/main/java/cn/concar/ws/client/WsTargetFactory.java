package cn.concar.ws.client;

import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class WsTargetFactory {

	private static HashMap<String, WebTarget> webTargetMap = new HashMap<String, WebTarget>();

	public static WebTarget getTarget(String url) {
		WebTarget target = webTargetMap.get(url);
		if (target == null) {
			ClientBuilder.newBuilder();
			Client client = ClientBuilder.newClient();
			target = client.target(url);
			webTargetMap.put(url, target);
		}
		return target;
	}
}
