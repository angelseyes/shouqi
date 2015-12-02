SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema baic_base
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baic_base` DEFAULT CHARACTER SET utf8 ;
USE `baic_base` ;

DROP TABLE IF EXISTS `baic_base`.`vehicle`;
DROP TABLE IF EXISTS `baic_base`.`admin`;
DROP TABLE IF EXISTS `baic_base`.`department`;

-- -----------------------------------------------------
-- Table `baic_vs`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_base`.`vehicle` (
  `vehicle_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `plate_number` VARCHAR(45) NULL DEFAULT NULL,
  `vin` VARCHAR(45) NULL DEFAULT NULL,
  `model` VARCHAR(45) NULL DEFAULT NULL,
  `displacement` DOUBLE NULL DEFAULT NULL,
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
  `belong_department` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`vehicle_id`) ,
  INDEX `belong_department_idx` (`belong_department` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `baic_vs`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_base`.`admin` (
  `admin_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `realname` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `belong_department` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) ,
  INDEX `belong_department_idx` (`belong_department` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `baic_vs`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_base`.`department` (
  `department_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `belong_department` BIGINT(20) NULL DEFAULT NULL,
  `department_code` VARCHAR(45) NULL DEFAULT NULL,
  `department_name` VARCHAR(45) NULL DEFAULT NULL,
  `short_name` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `longitude` DOUBLE NULL DEFAULT NULL,
  `latitude` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`department_id`) ,
  INDEX `belong_department_idx` (`belong_department` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
