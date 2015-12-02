package cn.concar.service.util;

public final class ServiceConstants {
	private ServiceConstants() {
	}

	private static final String APP = "concar-service";
	private static final String CONTEXT_KEY = "concar.service.spring.context";

	public static final long TIME_OUT = 10 * 60 * 1000;

	public static final String ADD_LVL_PROP = "address-level";

	public static final int UPLOAD_DISABLED = 0;
	public static final int UPLOAD_ENABLED = 1;

	public static final int ALERT_INTERVAL = 10 * 60 * 1000;
	/**
	 * Parameters for device.
	 */
	public static final String PARAM_HEART_BEAT_INTERVAL = "PARAM_HEART_BEAT_INTERVAL";
	public static final String PARAM_NON_CAN_DAT_INTERVAL = "PARAM_NON_CAN_DAT_INTERVAL";
	public static final String PARAM_MAX_SPD_LIMIT = "PARAM_MAX_SPD_LIMIT";
	public static final String PARAM_EXD_SPD_DUR = "PARAM_EXD_SPD_DUR";
	public static final String PARAM_NO_BRK_DRV_DUR_LIMIT = "PARAM_NO_BRK_DRV_DUR_LIMIT";
	public static final String PARAM_DAILY_DRV_DUR_LIMIT = "PARAM_DAILY_DRV_DUR_LIMIT";
	public static final String PARAM_MIN_BRK_TIME = "PARAM_MIN_BRK_TIME";
	public static final String PARAM_MAX_PARK_DUR = "PARAM_MAX_PARK_DUR";
	public static final String PARAM_VEH_DAT_UPLOAD_FLAG = "PARAM_VEH_DAT_UPLOAD_FLAG";
	public static final String PARAM_5_SEC_CAN_DAT_INTERVAL = "PARAM_5_SEC_CAN_DAT_INTERVAL";
	public static final String PARAM_30_SEC_CAN_DAT_INTERVAL = "PARAM_30_SEC_CAN_DAT_INTERVAL";
	public static final String PARAM_3600_SEC_CAN_DAT_INTERVAL = "PARAM_3600_SEC_CAN_DAT_INTERVAL";
	public static final String PARAM_MAN_CODE = "PARAM_MAN_CODE";

	/**
	 * SPRING_CONEXT_XML
	 */
	public static final String SPRING_CONEXT_XML = PropertiesLoader.getAppProperty(CONTEXT_KEY, APP);

	public static final int DISABLED = 0;
	public static final int ENABLED = 1;

	public static final int USER_ENABLED = 1;

	public static final int USER_DISABLED = 0;

	public static final int VALID = 1;

	public static final int INVALID = 0;

	public static final int MAP_LEVEL_ALL = 0;

	public static final int MAP_LEVEL_INSTITUTION = 1;

	public static final int MAP_LEVEL_ALL_INSTITUTION = 2;

	// Address level for marker, 0 is stand for station
	public static final int ADDRESS_LEVEL_STATION = 0;

	// Address level for marker, 1 is stand for center
	public static final int ADDRESS_LEVEL_CENTER = 1;

	public static final String DEFAULT_PASSWORD = "start123";

	/** DATE_FORMAT. */
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	/** DATE_FORMAT. */
	public static final String DATE_FORMAT_Y = "yyyy-MM-dd";
	/** TIME_FORMAT. */
	public static final String TIME_FORMAT = "HH:mm:ss";
	/** DISPATCH_TIME_FORMAT. */
	public static final String DISPATCH_TIME_FORMAT = "HH:mm";

	public static final String NULL = "NULL";
	/** DATE_TIME_FORMAT. */
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** DATE_T_TIME_FORMAT. */
	public static final String DATE_T_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public static final String DATE_TZ_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	/** TIMESTAMP_FORMAT. */
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

	public static final String DEFAULT_TIMEZONE = "Asia/Shanghai";

	public static final String MIN_DATE = "01-01-1970";
	public static final String MAX_DATE = "31-12-9999";
}
