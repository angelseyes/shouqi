package baic.obd.gateway.utils;

import baic.common.utils.PropertiesLoader;

public class Constants {
	private static final String APP = "baic-obd-gateway";
	private static final String CONTEXT_KEY = "baic.obd.gateway.context";
	public static final String SPRING_CONEXT_XML = PropertiesLoader.getAppProperty(CONTEXT_KEY, APP);
}
