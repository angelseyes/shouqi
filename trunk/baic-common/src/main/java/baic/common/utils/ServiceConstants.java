package baic.common.utils;

public class ServiceConstants {
	// Platform URL.
	private static final String BASE = "http://localhost:8080/baic-base-service/services";
	private static final String DEVICE = "http://localhost:8081/baic-device-service/services";
	private static final String OBD = "http://localhost:8082/baic-obd-service/services";

	public class Base {
		// Service Path.
		private static final String ADMIN = "/admin";
		private static final String DEPARTMENT = "/department";
		private static final String VEHICLE = "/vehicle";

		public class Admin {
			// Method Path.
			public static final String FIND_ALL_ADMINS = "/findAllAdmins";
			public static final String FIND_ADMINS_BY_DEPARTMENT_ID = "/findAdminsByDepartmentId";
			public static final String ACCESS = "/access";
			public static final String LOGIN = "/login";
			public static final String SAVE = "/save";
			public static final String LOGOUT = "/logout";

			public class Url {
				public static final String FIND_ALL_ADMINS_URL = BASE + ADMIN + FIND_ALL_ADMINS;
				public static final String FIND_ADMINS_BY_DEPARTMENT_ID_URL = BASE + ADMIN
						+ FIND_ADMINS_BY_DEPARTMENT_ID;
				public static final String ACCESS_URL = BASE + ADMIN + ACCESS;
				public static final String LOGIN_URL = BASE + ADMIN + LOGIN;
				public static final String SAVE_URL = BASE + ADMIN + SAVE;
				public static final String LOGOUT_URL = BASE + ADMIN + LOGOUT;
			}

		}

		public class Department {
			public static final String FIND_DEPARTMENT_BY_ID = "/findDepartmentById";
			public static final String FIND_DEPARTMENTS_BY_ID = "/findDepartmentsById";

			public class Url {
				public static final String FIND_DEPARTMENT_BY_ID_URL = BASE + DEPARTMENT + FIND_DEPARTMENT_BY_ID;
				public static final String FIND_DEPARTMENTS_BY_ID_URL = BASE + DEPARTMENT + FIND_DEPARTMENTS_BY_ID;
			}
		}

		public class Vehicle {
			public static final String FIND_ALL_VEHICLES = "/findAllVehicles";
			public static final String FIND_VEHICLES_BY_DEPARTMENT_ID = "/findVehiclesByDepartmentId";
			public static final String FIND_VEHICLES_BY_ADMIN_ID = "/findVehiclesByAdminId";
			public static final String FIND_SUB_VEHICLES_BY_DEPARTMENT_ID = "/findSubVehiclesByDepartmentId";

			public class Url {
				public static final String FIND_ALL_VEHICLES_URL = BASE + VEHICLE + FIND_ALL_VEHICLES;
				public static final String FIND_VEHICLES_BY_DEPARTMENT_ID_URL = BASE + VEHICLE
						+ FIND_VEHICLES_BY_DEPARTMENT_ID;
				public static final String FIND_VEHICLES_BY_ADMIN_ID_URL = BASE + VEHICLE + FIND_VEHICLES_BY_ADMIN_ID;
				public static final String FIND_SUB_VEHICLES_BY_DEPARTMENT_ID_URL = BASE + VEHICLE + FIND_SUB_VEHICLES_BY_DEPARTMENT_ID;
			}
		}
	}

	public class Device {
		private static final String DEVICE_VEHICLE = "/deviceVehicle";

		public class DeviceVehicle {
			public static final String FIND_VEHICLE_ID_BY_IMEI = "/findVehicleIdByImei";
			public static final String FIND_IMEI_BY_VEHICLE_ID = "/findImeiByVehicleId";

			public class Url {
				public static final String FIND_VEHICLE_ID_BY_IMEI_URL = DEVICE + DEVICE_VEHICLE
						+ FIND_VEHICLE_ID_BY_IMEI;
				public static final String FIND_IMEI_BY_VEHICLE_ID_URL = DEVICE + DEVICE_VEHICLE
						+ FIND_IMEI_BY_VEHICLE_ID;
			}
		}
	}

	public class Obd {
		private static final String LATEST_RECORD = "/latestRecord";
		private static final String TRACE = "/trace";

		public class LatestRecord {
			public static final String FIND_LATEST_RECORD_BY_IMEI = "/findLatestRecordByImei";

			public class Url {
				public static final String FIND_LATEST_RECORD_BY_IMEI_URL = OBD + LATEST_RECORD
						+ FIND_LATEST_RECORD_BY_IMEI;
			}
		}

		public class Trace {
			public static final String FIND_TRACES = "/findTraces";
			public static final String FIND_TRACES_BY_IMEI = "/findTracesByImei";

			public class Url {
				public static final String FIND_TRACES_URL = OBD + TRACE + FIND_TRACES;
				public static final String FIND_TRACES_BY_IMEI_URL = OBD + TRACE + FIND_TRACES_BY_IMEI;
			}
		}
	}
}
