use baic;

DELIMITER $$

DROP PROCEDURE IF EXISTS baic.p_process_his_act $$
CREATE PROCEDURE baic.`p_process_his_act`(IN V_START_TIME DATETIME, IN V_END_TIME DATETIME)
begin
DECLARE record_not_found INT DEFAULT 0;
DECLARE p_actcount BIGINT(20);

DECLARE cur_p CURSOR FOR SELECT sum(act) from (
SELECT imei,ifnull(DATE_FORMAT(max(trace_time),'%Y-%m-%d')=V_START_TIME,0) act FROM baic.all_record
WHERE trace_time >= V_START_TIME and trace_time < V_END_TIME
group by imei) t;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;

OPEN cur_p;
mLoop:LOOP
	FETCH cur_p INTO p_actcount;
	IF record_not_found THEN
		LEAVE mLoop;
	END IF;
END LOOP mLoop;
CLOSE cur_p;

IF p_actcount is null THEN
	SET p_actcount = 0;
END IF;

INSERT INTO `historystat`(
		`stat_date`,
		`stat_type`,
		`stat_count`)
	VALUES (
		V_START_TIME,
		'ACT',
		p_actcount
	);
end$$
DELIMITER ;