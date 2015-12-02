SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema baic_vs
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baic_vs` DEFAULT CHARACTER SET utf8 ;
USE `baic_vs` ;

DROP TABLE IF EXISTS `baic_vs`.`vehicle`;
DROP TABLE IF EXISTS `baic_vs`.`dtc_message`;
DROP TABLE IF EXISTS `baic_vs`.`latest_record`;
DROP TABLE IF EXISTS `baic_vs`.`all_record`;
DROP TABLE IF EXISTS `baic_vs`.`trace`;
DROP TABLE IF EXISTS `baic_vs`.`device`;
DROP TABLE IF EXISTS `baic_vs`.`config`;
DROP TABLE IF EXISTS `baic_vs`.`admin`;
DROP TABLE IF EXISTS `baic_vs`.`department`;
DROP TABLE IF EXISTS `baic_vs`.`historystat`;
DROP VIEW IF EXISTS `baic_vs`.`is_norm_active`;

-- -----------------------------------------------------
-- Table `baic_vs`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`vehicle` (
  `vehicle_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `plate_number` VARCHAR(45) NULL DEFAULT NULL,
  `vin` VARCHAR(45) NULL DEFAULT NULL,
  `model` VARCHAR(45) NULL DEFAULT NULL,
  `displacement` VARCHAR(45) NULL DEFAULT NULL,
  `color` VARCHAR(45) NULL DEFAULT NULL,
  `mileage` DOUBLE NULL DEFAULT NULL,
  `registered_date` DATE NULL DEFAULT NULL,
  `valid` TINYINT(1) NULL DEFAULT NULL,
  `same_name` TINYINT(1) NULL DEFAULT NULL,
  `key_number` INT(11) NULL DEFAULT NULL,
  `license` TINYINT(1) NULL DEFAULT NULL,
  `certificate` TINYINT(1) NULL DEFAULT NULL,
  `purchase` TINYINT(1) NULL DEFAULT NULL,
  `tax` TINYINT(1) NULL DEFAULT NULL,
  `clivta` TINYINT(1) NULL DEFAULT NULL,
  `clivta_date` DATE NULL DEFAULT NULL,
  `insurance` TINYINT(1) NULL DEFAULT NULL,
  `insurance_date` DATE NULL DEFAULT NULL,
  `inspection_date` DATE NULL DEFAULT NULL,
  `violation` TINYINT(1) NULL DEFAULT NULL,
  `imei` VARCHAR(45) NULL DEFAULT NULL,
  `belong_department` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`vehicle_id`) ,
  INDEX `belong_department_idx` (`belong_department` ASC) ,
  INDEX `imei_idx` (`imei` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baic_vs`.`dtc_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`dtc_message` (
  `dtc` VARCHAR(10) NOT NULL,
  `dtc_message` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`dtc`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baic_vs`.`latest_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`latest_record` (
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baic_vs`.`all_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`all_record` (
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baic_vs`.`trace`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`trace` (
  `trace_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `imei` VARCHAR(45) NULL DEFAULT NULL,
  `trace_date` DATE NULL DEFAULT NULL,
  `trace_line` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`trace_id`),
  INDEX `imei_idx` (`imei` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baic_vs`.`device`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`device` (
  `imei` VARCHAR(45) NOT NULL,
  `sim_number` VARCHAR(45) NULL,
  PRIMARY KEY (`imei`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baic_vs`.`config`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`config` (
  `config_name` VARCHAR(45) NULL,
  `config_value` VARCHAR(45) NULL,
  PRIMARY KEY (`config_name`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baic_vs`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`admin` (
  `admin_id`BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `realname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `belong_department` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) ,
  INDEX `belong_department_idx` (`belong_department` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baic_vs`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`department` (
  `department_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `department_code` VARCHAR(45) NULL,
  `department_name` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `longitude` DOUBLE NULL DEFAULT NULL,
  `latitude` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`department_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baic_vs`.`historystat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_vs`.`historystat` (
  `stat_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `stat_date` DATE NULL DEFAULT NULL,
  `stat_type` VARCHAR(45) DEFAULT NULL,
  `stat_count` BIGINT(20) NULL,
  PRIMARY KEY (`stat_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- View `baic_vs`.`is_norm_active`
-- -----------------------------------------------------
CREATE VIEW is_norm_active AS 
SELECT d.department_code, 
	isnull(r.dtc) is_normal, 
	ifnull((DATE_FORMAT(trace_time, '%Y-%m-%d') = curdate()), 0) is_active, 
	count(1) c
FROM vehicle v 
LEFT OUTER JOIN latest_record r 
ON r.imei = v.imei 
LEFT OUTER JOIN department d 
ON d.department_id = v.belong_department
GROUP BY 
	d.department_code, 
	isnull(r.dtc), 
	(DATE_FORMAT(trace_time, '%Y-%m-%d') = curdate());


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
