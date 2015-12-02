SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema baic_device
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baic_device` DEFAULT CHARACTER SET utf8 ;
USE `baic_device` ;

DROP TABLE IF EXISTS `baic_device`.`device_vehicle`;


-- -----------------------------------------------------
-- Table `baic_device`.`device_vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `baic_device`.`device_vehicle` (
  `imei` VARCHAR(45) NOT NULL,
  `vehicle_id` BIGINT(20) NULL,
  PRIMARY KEY (`imei`) ,
  INDEX `vehicle_id_idx` (`vehicle_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
