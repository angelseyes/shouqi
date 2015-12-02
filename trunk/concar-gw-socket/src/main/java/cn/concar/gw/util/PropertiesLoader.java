package cn.concar.gw.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * ClassName: ServiceUrlLoader
 * Function: Util class for load properties files.
 * Date: 2013-6-24
 *
 * @author haoli
 * @version 1.0
 */
public class PropertiesLoader {    /**
     * LOG:Create Log instance to record log.
     */
    private static final Logger LOG = Logger.getLogger(PropertiesLoader.class);
    /**
     * configuration: Static PeropertiesLoader variable.
     */
    private static PropertiesLoader configuration = null;
    /**
     * CONFIG_PROP_LOC:static string variable.
     */
    private static String configPropLoc = null;

    /**
     * isInitialized:flag to mark whether or not Properties are initialized.
     */
    private static boolean isInitialized = false;
    
    /**
     * cache
     */
    private static HashMap<String, Properties> cache = new HashMap<String, Properties>();
    
    /**
     * Construct method.
     */
    private PropertiesLoader() {
    }

    /**
     * init:(initiate the class for the first time).
     * 
     * @param configLoc configLog
     * @return ServiceUrlLoader
     */
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

    /**
     * This method will be called at run time. This will load all properties
     * 
     * @throws Exception exception
     */
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

            /* Reading from properties file */
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
     * @param name name of attribute.
     * @param app  Application name of the properties.
     * @return value of attribute.
     */
    public static synchronized String getAppProperty(final String name) {
        Properties p = cache.get("gw-socket.properties");
        if(p == null) {
            String ploc = "gw-socket.properties";
            p = new Properties();
            try {
                p.load(PropertiesLoader.class.getClassLoader().getResourceAsStream(ploc));
                cache.put("gw-socket.properties", p);
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
        if(p == null) {
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
        	if (p.getProperty((String)key).equals(value)) {
        		return (String) key;
        	}
        }
        return null;
    }}

