use baic;

DELIMITER $$

DROP PROCEDURE IF EXISTS baic.p_process_his_dtc $$
CREATE PROCEDURE baic.`p_process_his_dtc`(IN V_START_TIME DATETIME, IN V_END_TIME DATETIME)
begin
DECLARE record_not_found INT DEFAULT 0;
DECLARE p_dtccount BIGINT(20);

DECLARE cur_p CURSOR FOR 
select sum(dtc) from (
SELECT imei, !isnull(GROUP_CONCAT(dtc)) dtc FROM baic.all_record
WHERE trace_time >=V_START_TIME and trace_time < V_END_TIME
group by imei) t;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;

OPEN cur_p;
mLoop:LOOP
	FETCH cur_p INTO p_dtccount;
	IF record_not_found THEN
		LEAVE mLoop;
	END IF;
END LOOP mLoop;
CLOSE cur_p;

IF p_dtccount is null THEN
	SET p_dtccount = 0;
END IF;

INSERT INTO `historystat`(
		`stat_date`,
		`stat_type`,
		`stat_count`)
	VALUES (
		V_START_TIME,
		'DTC',
		p_dtccount
	);
end$$
DELIMITER ;