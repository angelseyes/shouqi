use baic_vs;

DELIMITER $$

DROP PROCEDURE IF EXISTS baic_vs.p_process_daily_trace $$
CREATE PROCEDURE baic_vs.`p_process_daily_trace`(IN V_START_TIME DATETIME, IN V_END_TIME DATETIME)
begin
DECLARE record_not_found INT DEFAULT 0;
DECLARE p_Traceline LONGTEXT character set utf8;
DECLARE p_Traceline_sub LONGTEXT character set utf8;
DECLARE p_Traceline_tmp LONGTEXT character set utf8;
DECLARE p_imei VARCHAR(45);
DECLARE p_longitude DOUBLE;
DECLARE p_latitude DOUBLE;

DECLARE p_Traceline_imei VARCHAR(45);
DECLARE p_Traceline_line TEXT character set utf8;

DECLARE cur_p CURSOR FOR SELECT imei, longitude, latitude
FROM baic_vs.`all_record` vd
WHERE trace_time >= V_START_TIME and trace_time < V_END_TIME
order by trace_time;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;

SET p_Traceline := '';

OPEN cur_p;
mLoop:LOOP
	FETCH cur_p INTO p_imei,p_longitude,p_latitude;
	IF record_not_found THEN
		LEAVE mLoop;
	END IF;
	
	IF LOCATE(CONCAT(p_imei,'|'), p_Traceline) = 0 THEN
		IF LENGTH(p_Traceline) > 0 THEN
			SET p_Traceline := CONCAT(p_Traceline,'&',p_imei,'|',p_longitude,',',p_latitude);
		ELSE  
			SET p_Traceline := CONCAT(p_imei,'|',p_longitude,',',p_latitude);
		END IF;
	ELSE
		SET p_Traceline_sub := SUBSTRING(p_Traceline,LOCATE(CONCAT(p_imei,'|'), p_Traceline));
		IF LOCATE('&', p_Traceline_sub) = 0 THEN
			SET p_Traceline_tmp := p_Traceline_sub;
		ELSE
			SET p_Traceline_tmp := SUBSTRING(p_Traceline_sub,1,LOCATE('&', p_Traceline_sub)-1);
		END IF;
		
		SET p_Traceline_sub := p_Traceline_tmp;
		SET p_Traceline_tmp := CONCAT(p_Traceline_tmp,';',p_longitude,',',p_latitude);
		SET p_Traceline := REPLACE(p_Traceline,p_Traceline_sub,p_Traceline_tmp);
	END IF;
END LOOP mLoop;
CLOSE cur_p;

WHILE LENGTH(p_Traceline) > 0 DO
	IF LOCATE('&', p_Traceline) = 0 THEN
		SET p_Traceline_imei := SUBSTRING(p_Traceline,1,LOCATE('|', p_Traceline)-1);
		SET p_Traceline_line := SUBSTRING(p_Traceline,LOCATE('|', p_Traceline)+1);
		SET p_Traceline := '';
	ELSE
		SET p_Traceline_tmp := SUBSTRING(p_Traceline,1,LOCATE('&', p_Traceline)-1);
		SET p_Traceline_imei := SUBSTRING(p_Traceline_tmp,1,LOCATE('|', p_Traceline_tmp)-1);
		SET p_Traceline_line := SUBSTRING(p_Traceline_tmp,LOCATE('|', p_Traceline_tmp)+1);
		SET p_Traceline := SUBSTRING(p_Traceline,LOCATE('&', p_Traceline)+1);
	END IF;
  
	INSERT INTO `trace`(
			`imei`,
			`trace_date`,
			`trace_line`)
		VALUES (
			p_Traceline_imei,
			V_START_TIME,
			p_Traceline_line
		);
end WHILE;

end$$
DELIMITER ;