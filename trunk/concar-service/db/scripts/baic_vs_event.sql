use baic_vs;

DELIMITER $$


drop event if exists e_trace_daily; $$ 
create event e_trace_daily
on schedule every 1 day starts date_add(date_add(current_date(),interval 2 hour), interval 1 day)
ON COMPLETION PRESERVE
DO BEGIN
	call p_process_daily_trace(
		date_sub(current_date(), interval 1 day),
		current_date());
END $$

--drop event if exists e_process_his_act; $$ 
--create event e_process_his_act
--on schedule every 1 day starts date_add(date_add(current_date(),interval 3 hour), interval 1 day)
--ON COMPLETION PRESERVE
--DO BEGIN
--	call p_process_his_act(
--		date_sub(current_date(), interval 1 day),
--		current_date());
--END $$
--
--
--drop event if exists e_process_his_dtc; $$ 
--create event e_process_his_dtc
--on schedule every 1 day starts date_add(date_add(current_date(),interval 4 hour), interval 1 day)
--ON COMPLETION PRESERVE
--DO BEGIN
--	call p_process_his_dtc(
--		date_sub(current_date(), interval 1 day),
--		current_date());
--END $$

DELIMITER ;