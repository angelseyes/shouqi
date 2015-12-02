package baic.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class PropertiesLoader {

	private static final Logger LOG = Logger.getLogger(PropertiesLoader.class);
	private static PropertiesLoader configuration = null;
	private static String configPropLoc = null;
	private static boolean isInitialized = false;
	private static HashMap<String, Properties> cache = new HashMap<String, Properties>();

	private PropertiesLoader() {
	}

	public static PropertiesLoader init(final String configLoc) {
		configPropLoc = configLoc;
		if (!isInitialized) {
			try {
				LOG.info("Init properties: " + configLoc);
				createInstance();
			} catch (Exception ex) {
				LOG.error("Exception raised in getInstance method", ex);
			}
		}
		return PropertiesLoader.configuration;
	}

	private static synchronized void createInstance() throws Exception {
		InputStream inputStream = null;
		if (isInitialized) {
			return;
		}

		try {
			LOG.debug("Inside createInstance method...");
			configuration = new PropertiesLoader();
			LOG.debug("Location of CONFIG_PROP_LOC=" + configPropLoc);
			Properties appConfigProperties = new Properties();
			Properties getAppConfigPropertiesFile = new Properties();
			inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(configPropLoc);
			getAppConfigPropertiesFile.load(inputStream);
			LOG.debug("Loading from the prop file....");
			Enumeration<?> keys = getAppConfigPropertiesFile.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = getAppConfigPropertiesFile.getProperty(key);
				LOG.debug("key=" + key + " value=" + value);
				if (key != null && value != null) {
					appConfigProperties.put(key, value);
				}
			}
			isInitialized = true;
		} catch (FileNotFoundException exception) {
			LOG.error("Properties not found exception");
			throw new Exception(exception.getMessage(), exception);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	/**
	 * Get property value.
	 * 
	 * @param name
	 *            name of attribute.
	 * @param app
	 *            Application name of the properties.
	 * @return value of attribute.
	 */
	public static synchronized String getAppProperty(final String name, String app) {
		Properties p = cache.get(app);
		if (p == null) {
			String ploc = app + ".properties";
			p = new Properties();
			try {
				p.load(PropertiesLoader.class.getClassLoader().getResourceAsStream(ploc));
				cache.put(app, p);
			} catch (FileNotFoundException e) {
				LOG.error("File could not be found: " + ploc, e);
			} catch (IOException e) {
				LOG.error("Exception occured when reading file: " + ploc, e);
			} catch (Exception e) {
				LOG.error("Exception occured when reading file: " + ploc, e);
			}
		}
		return p.getProperty(name).trim();
	}

	public static synchronized String getAppPropKey(String value, String app) {
		Properties p = cache.get(app);
		if (p == null) {
			String ploc = app + ".properties";
			p = new Properties();
			try {
				p.load(PropertiesLoader.class.getClassLoader().getResourceAsStream(ploc));
				cache.put(app, p);
			} catch (FileNotFoundException e) {
				LOG.error("File could not be found: " + ploc, e);
			} catch (IOException e) {
				LOG.error("Exception occured when reading file: " + ploc, e);
			} catch (Exception e) {
				LOG.error("Exception occured when reading file: " + ploc, e);
			}
		}
		Set<Object> keys = p.keySet();
		for (Object key : keys) {
			if (p.getProperty((String) key).equals(value)) {
				return (String) key;
			}
		}
		return null;
	}
}
