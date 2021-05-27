/*
* Author: Nikhil Shah
* Project: Social Media Project
* Create and Populate Tables
*/

-- -----------------------------------------------------
-- Schema HobbyHome
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `HobbyHome` DEFAULT CHARACTER SET utf8 ;
USE `HobbyHome` ;



	
-- -----------------------------------------------------
-- Table `HobbyHome`.`login`
-- -----------------------------------------------------
	
CREATE TABLE IF NOT EXISTS `HobbyHome`.`login` (
  `userID` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `passhash` varchar(64) NOT NULL, --use sha256
  `isadmin` bool NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `idUser_UNIQUE` (`userID` ASC) VISIBLE
);