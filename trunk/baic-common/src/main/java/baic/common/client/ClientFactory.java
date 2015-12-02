package baic.common.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public abstract class ClientFactory {
	private static HashMap<String, WebClient> clients = new HashMap<String, WebClient>();
	private static HashMap<String, WebTarget> targets = new HashMap<String, WebTarget>();

	public static WebClient getClient(String url) {
		WebClient webClient = clients.get(url);
		if (webClient == null) {
			List<Object> providerList = new ArrayList<Object>();
			providerList.add(new JacksonJsonProvider());
			webClient = WebClient.create(url, providerList);
			webClient.accept(MediaType.APPLICATION_JSON);
			webClient.type(MediaType.APPLICATION_JSON);
			clients.put(url, webClient);
		}
		return webClient;
	}

	public static WebTarget getTarget(String url) {
		WebTarget webTarget = targets.get(url);
		if (webTarget == null) {
			ClientBuilder.newBuilder();
			Client client = ClientBuilder.newClient();
			webTarget = client.target(url);
			targets.put(url, webTarget);
		}
		return webTarget;
	}

}
