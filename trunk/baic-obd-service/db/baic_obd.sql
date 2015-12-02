SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema baic_obd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baic_obd` DEFAULT CHARACTER SET utf8 ;
USE `baic_obd` ;

DROP TABLE IF EXISTS `baic_obd`.`latest_record`;
DROP TABLE IF EXISTS `baic_obd`.`trace`;


-- -----------------------------------------------------
-- Table `baic_obd`.`latest_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_obd`.`latest_record` (
  `record_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `imei` VARCHAR(45) NULL DEFAULT NULL,
  `trace_time` DATETIME NULL DEFAULT NULL,
  `longitude` DOUBLE NULL DEFAULT NULL,
  `latitude` DOUBLE NULL DEFAULT NULL,
  `altitude` INT(11) NULL DEFAULT NULL,
  `hdop` DOUBLE NULL DEFAULT NULL,
  `vdop` DOUBLE NULL DEFAULT NULL,
  `mileage` BIGINT(20) NULL DEFAULT NULL,
  `speed` INT(11) NULL DEFAULT NULL,
  `rpm` INT(11) NULL DEFAULT NULL,
  `temperature` INT(11) NULL DEFAULT NULL,
  `voltage` DOUBLE NULL DEFAULT NULL,
  `engine` TINYINT(1) NULL DEFAULT NULL,
  `dtc` VARCHAR(100) NULL DEFAULT NULL,
  `location` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`record_id`) ,
  INDEX `imei_idx` (`imei` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `baic_obd`.`trace`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_obd`.`trace` (
  `trace_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `imei` VARCHAR(45) NULL DEFAULT NULL,
  `trace_date` DATE NULL DEFAULT NULL,
  `trace_line` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`trace_id`),
  INDEX `imei_idx` (`imei` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
