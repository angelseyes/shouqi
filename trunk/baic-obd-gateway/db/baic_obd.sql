SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema baic_obd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baic_obd` DEFAULT CHARACTER SET utf8 ;
USE `baic_obd` ;

DROP TABLE IF EXISTS `baic_obd`.`all_record`;


-- -----------------------------------------------------
-- Table `baic_vs`.`all_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_obd`.`all_record` (
  `record_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `imei` VARCHAR(45) NULL,
  `trace_time` DATETIME NULL,
  `longitude` DOUBLE NULL,
  `latitude` DOUBLE NULL,
  `altitude` INT(11) NULL,
  `hdop` DOUBLE NULL,
  `vdop` DOUBLE NULL,
  `mileage` BIGINT(20) NULL,
  `speed` INT(11) NULL,
  `rpm` INT(11) NULL,
  `temperature` INT(11) NULL,
  `voltage` DOUBLE NULL,
  `engine` TINYINT(1) NULL,
  `dtc` VARCHAR(100) NULL,
  PRIMARY KEY (`record_id`) ,
  INDEX `imei_idx` (`imei` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
