use baic_vs;

DELIMITER $$

DROP FUNCTION IF EXISTS f_check_imei_exist $$
CREATE FUNCTION `f_check_imei_exist`
(in_imei VARCHAR(45))
RETURNS BIGINT(20)
BEGIN
DECLARE record_not_found INT DEFAULT 0;
DECLARE o_RECORD_ID BIGINT;
DECLARE cur_chk CURSOR FOR SELECT record_id FROM baic_vs.`latest_record` WHERE imei = in_imei;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;

OPEN cur_chk;
mLoop:LOOP
	FETCH cur_chk INTO o_RECORD_ID;
	IF record_not_found THEN
		LEAVE mLoop;
	END IF;
END LOOP mLoop;
CLOSE cur_chk;

RETURN(o_RECORD_ID);

END$$
DELIMITER ;