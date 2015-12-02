USE `baic_obd`;


-- -----------------------------------------------------
-- Trigger t_insert_latest
-- -----------------------------------------------------
DELIMITER $$

DROP TRIGGER IF EXISTS t_insert_latest $$
CREATE TRIGGER `t_insert_latest` BEFORE INSERT ON `all_record` FOR EACH ROW 
BEGIN 
	SET @id = f_check_imei_exist(NEW.imei);
	IF @id is null THEN
		IF ((NEW.longitude is not null && NEW.latitude is not null)
			&&  (NEW.longitude <> 0 && NEW.latitude <> 0)) THEN
			SET @location := 1;
		ELSE 
			SET @location := 0;
		END IF;
		INSERT `latest_record`(`imei`,`trace_time`,`longitude`,`latitude`,`altitude`,`hdop`,`vdop`,`mileage`,`speed`,`rpm`,`temperature`,`voltage`,`engine`,`dtc`,`location`) 
				VALUES (NEW.imei,NEW.trace_time,NEW.longitude,NEW.latitude,NEW.altitude,NEW.hdop,NEW.vdop,NEW.mileage,NEW.speed,NEW.rpm,NEW.temperature,NEW.voltage,NEW.engine,NEW.dtc,@location);
	ELSE
		IF NEW.trace_time is not null THEN
			UPDATE `latest_record` SET trace_time = NEW.trace_time WHERE imei = NEW.imei;
		END IF;
		
		IF ((NEW.longitude is not null && NEW.latitude is not null)
			&&  (NEW.longitude <> 0 && NEW.latitude <> 0)) THEN
			UPDATE `latest_record` SET longitude = NEW.longitude, latitude = NEW.latitude WHERE imei = NEW.imei;
			UPDATE `latest_record` SET location = 1 WHERE imei = NEW.imei;
		ELSE 
			UPDATE `latest_record` SET location = 0 WHERE imei = NEW.imei;
		END IF;
		
		IF NEW.altitude is not null THEN
			UPDATE `latest_record` SET altitude = NEW.altitude WHERE imei = NEW.imei;
		END IF;
		
		IF NEW.hdop is not null THEN
			UPDATE `latest_record` SET hdop = NEW.hdop WHERE imei = NEW.imei;
		END IF;
		
		IF NEW.vdop is not null THEN
			UPDATE `latest_record` SET vdop = NEW.vdop WHERE imei = NEW.imei;
		END IF;
		
		IF NEW.mileage is not null THEN
			UPDATE `latest_record` SET mileage = NEW.mileage WHERE imei = NEW.imei;
		END IF;
		
		UPDATE `latest_record` SET speed = NEW.speed WHERE imei = NEW.imei;
		
		UPDATE `latest_record` SET rpm = NEW.rpm WHERE imei = NEW.imei;
		
		UPDATE `latest_record` SET temperature = NEW.temperature WHERE imei = NEW.imei;
		
		UPDATE `latest_record` SET voltage = NEW.voltage WHERE imei = NEW.imei;
		
		UPDATE `latest_record` SET engine = NEW.engine WHERE imei = NEW.imei;
		
		IF NEW.dtc is not null THEN
			UPDATE `latest_record` SET dtc = NEW.dtc WHERE imei = NEW.imei;
		END IF;
	END IF;
	
END$$
DELIMITER ;