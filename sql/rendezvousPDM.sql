SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `Rendezvous` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `Rendezvous`;

-- -----------------------------------------------------
-- Table `Rendezvous`.`lkCountryCode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`lkCountryCode` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`lkCountryCode` (
  `countryCode` CHAR(3) NOT NULL ,
  `countryName` VARCHAR(45) NULL ,
  PRIMARY KEY (`countryCode`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rendezvous`.`building`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`building` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`building` (
  `buildingId` INT NOT NULL AUTO_INCREMENT ,
  `buildingName` VARCHAR(45) NULL ,
  `address1` VARCHAR(45) NULL ,
  `address2` VARCHAR(45) NULL ,
  `address3` VARCHAR(45) NULL ,
  `town` VARCHAR(45) NULL ,
  `postcode` VARCHAR(45) NULL ,
  `countryCode` CHAR(3) NOT NULL ,
  `activeFrom` DATE NULL ,
  `activeUntil` DATE NULL ,
  PRIMARY KEY (`buildingId`) ,
  CONSTRAINT `fk_building_countryCode`
    FOREIGN KEY (`countryCode` )
    REFERENCES `Rendezvous`.`lkCountryCode` (`countryCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_building_countryCode ON `Rendezvous`.`building` (`countryCode` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`floor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`floor` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`floor` (
  `floorId` INT NOT NULL AUTO_INCREMENT ,
  `buildingId` INT NULL ,
  `floorName` VARCHAR(45) NULL ,
  `displayOrder` INT NULL ,
  `deleted` CHAR(1) NULL DEFAULT 'N' ,
  PRIMARY KEY (`floorId`) ,
  CONSTRAINT `fk_floor_building`
    FOREIGN KEY (`buildingId` )
    REFERENCES `Rendezvous`.`building` (`buildingId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_floor_building ON `Rendezvous`.`floor` (`buildingId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`room` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`room` (
  `roomId` INT NOT NULL AUTO_INCREMENT ,
  `roomCode` VARCHAR(12) NOT NULL COMMENT 'Unique code for a room.' ,
  `roomName` VARCHAR(45) NOT NULL COMMENT 'A name for the room.' ,
  `capacity` INT NULL ,
  `location` VARCHAR(64) NULL ,
  `selfService` CHAR(1) NULL ,
  `floorId` INT NULL ,
  PRIMARY KEY (`roomId`) ,
  CONSTRAINT `fk_room_floor`
    FOREIGN KEY (`floorId` )
    REFERENCES `Rendezvous`.`floor` (`floorId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_room_floor ON `Rendezvous`.`room` (`floorId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`facilityType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`facilityType` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`facilityType` (
  `facilityTypeCode` VARCHAR(12) NOT NULL ,
  `typeDescription` VARCHAR(48) NOT NULL ,
  `numberAvailable` INT NULL ,
  `noticeRequired` TIME NULL ,
  PRIMARY KEY (`facilityTypeCode`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rendezvous`.`resourceType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`resourceType` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`resourceType` (
  `resourceTypeCode` VARCHAR(12) NOT NULL ,
  `typeDescription` VARCHAR(48) NULL ,
  `noticeRequired` TIME NULL ,
  `consumable` BOOLEAN NULL ,
  PRIMARY KEY (`resourceTypeCode`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rendezvous`.`lkBookingStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`lkBookingStatus` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`lkBookingStatus` (
  `bookingStatusCode` CHAR(12) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  PRIMARY KEY (`bookingStatusCode`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rendezvous`.`lkUserPreviledge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`lkUserPreviledge` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`lkUserPreviledge` (
  `userPriviledgeCode` CHAR(12) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  PRIMARY KEY (`userPriviledgeCode`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rendezvous`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`user` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`user` (
  `userId` INT NOT NULL AUTO_INCREMENT ,
  `userPreviledgeCode` CHAR(12) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  `firstName` VARCHAR(45) NOT NULL ,
  `surname` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NULL ,
  `telephone` VARCHAR(45) NULL ,
  `mobile` VARCHAR(45) NULL ,
  PRIMARY KEY (`userId`) ,
  CONSTRAINT `fk_user_userType`
    FOREIGN KEY (`userPreviledgeCode` )
    REFERENCES `Rendezvous`.`lkUserPreviledge` (`userPriviledgeCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_user_userType ON `Rendezvous`.`user` (`userPreviledgeCode` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`meetingBooking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`meetingBooking` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`meetingBooking` (
  `bookingId` INT NOT NULL AUTO_INCREMENT ,
  `bookingDate` DATE NOT NULL ,
  `bookingStatusCode` CHAR(12) NOT NULL ,
  `organiserUserId` INT NOT NULL ,
  PRIMARY KEY (`bookingId`) ,
  CONSTRAINT `fk_meetingBooking_bookingStatus`
    FOREIGN KEY (`bookingStatusCode` )
    REFERENCES `Rendezvous`.`lkBookingStatus` (`bookingStatusCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetingBooking_user`
    FOREIGN KEY (`organiserUserId` )
    REFERENCES `Rendezvous`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_meetingBooking_bookingStatus ON `Rendezvous`.`meetingBooking` (`bookingStatusCode` ASC) ;

CREATE INDEX fk_meetingBooking_user ON `Rendezvous`.`meetingBooking` (`organiserUserId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`requestedTimes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`requestedTimes` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`requestedTimes` (
  `requestedTimesId` INT NOT NULL AUTO_INCREMENT ,
  `bookingId` INT NULL ,
  `startDate` DATE NULL ,
  `endDate` DATE NULL ,
  `maximumOccurances` INT NULL ,
  `everyXDays` INT NULL ,
  `everyWeekday` BOOLEAN NULL ,
  `everyXWeeks` INT NULL ,
  `weekDaySelection` CHAR(7) NULL ,
  `dayOfMonth` INT NULL ,
  `everyXMonths` INT NULL ,
  `the1stXofTheMonth` CHAR(3) NULL ,
  `the2ndXofTheMonth` CHAR(3) NULL ,
  `the3rdXofTheMonth` CHAR(3) NULL ,
  `the4thXofTheMonth` CHAR(3) NULL ,
  `theLastXofTheMonth` CHAR(3) NULL ,
  `everyXMonth` CHAR(3) NULL ,
  `startTime` TIME NULL ,
  `endTime` TIME NULL ,
  `meetingTitle` VARCHAR(45) NULL ,
  `meetingDescription` VARCHAR(45) NULL ,
  PRIMARY KEY (`requestedTimesId`) ,
  CONSTRAINT `fk_blockBookingTemplate_meetingBooking`
    FOREIGN KEY (`bookingId` )
    REFERENCES `Rendezvous`.`meetingBooking` (`bookingId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_blockBookingTemplate_meetingBooking ON `Rendezvous`.`requestedTimes` (`bookingId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`requestedRooms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`requestedRooms` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`requestedRooms` (
  `requestedRoomsId` INT NOT NULL AUTO_INCREMENT ,
  `requestedTimesId` INT NULL ,
  `preferredRoomCode` VARCHAR(12) NULL ,
  `numberOfAttendees` INT NULL ,
  PRIMARY KEY (`requestedRoomsId`) ,
  CONSTRAINT `fk_requestedRooms_requestedTimes`
    FOREIGN KEY (`requestedTimesId` )
    REFERENCES `Rendezvous`.`requestedTimes` (`requestedTimesId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_requestedRooms_requestedTimes ON `Rendezvous`.`requestedRooms` (`requestedTimesId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`meeting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`meeting` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`meeting` (
  `meetingId` INT NOT NULL AUTO_INCREMENT ,
  `requestedRoomsId` INT NULL ,
  `roomId` INT NULL ,
  `meetingDate` DATE NOT NULL ,
  `startTime` TIME NOT NULL ,
  `endTime` TIME NOT NULL ,
  PRIMARY KEY (`meetingId`) ,
  CONSTRAINT `fk_meeting_requestedRooms`
    FOREIGN KEY (`requestedRoomsId` )
    REFERENCES `Rendezvous`.`requestedRooms` (`requestedRoomsId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meeting_room`
    FOREIGN KEY (`roomId` )
    REFERENCES `Rendezvous`.`room` (`roomId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_meeting_requestedRooms ON `Rendezvous`.`meeting` (`requestedRoomsId` ASC) ;

CREATE INDEX fk_meeting_room ON `Rendezvous`.`meeting` (`roomId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`meetingAttendee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`meetingAttendee` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`meetingAttendee` (
  `meetingAttendeeId` INT NOT NULL AUTO_INCREMENT ,
  `userId` INT NULL ,
  `requestedTimesId` INT NULL ,
  PRIMARY KEY (`meetingAttendeeId`) ,
  CONSTRAINT `fk_meetingAttendee_user`
    FOREIGN KEY (`userId` )
    REFERENCES `Rendezvous`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetingAttendee_requestedTimes`
    FOREIGN KEY (`requestedTimesId` )
    REFERENCES `Rendezvous`.`requestedTimes` (`requestedTimesId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_meetingAttendee_user ON `Rendezvous`.`meetingAttendee` (`userId` ASC) ;

CREATE INDEX fk_meetingAttendee_requestedTimes ON `Rendezvous`.`meetingAttendee` (`requestedTimesId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`meetingAgenda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`meetingAgenda` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`meetingAgenda` (
  `agendaItemNumber` INT NOT NULL AUTO_INCREMENT ,
  `agendaItemDescription` VARCHAR(64) NOT NULL ,
  `meetingAttendeeId` INT NULL ,
  `meetingId` INT NULL ,
  PRIMARY KEY (`agendaItemNumber`) ,
  CONSTRAINT `fk_meetingAgenda_meetingAttendee`
    FOREIGN KEY (`meetingAttendeeId` )
    REFERENCES `Rendezvous`.`meetingAttendee` (`meetingAttendeeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetingAgenda_meeting`
    FOREIGN KEY (`meetingId` )
    REFERENCES `Rendezvous`.`meeting` (`meetingId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_meetingAgenda_meetingAttendee ON `Rendezvous`.`meetingAgenda` (`meetingAttendeeId` ASC) ;

CREATE INDEX fk_meetingAgenda_meeting ON `Rendezvous`.`meetingAgenda` (`meetingId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`meetingFacility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`meetingFacility` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`meetingFacility` (
  `meetingFacilityId` INT NOT NULL AUTO_INCREMENT ,
  `facilityTypeCode` VARCHAR(12) NOT NULL ,
  `requestedRoomsId` INT NULL ,
  `facilityComment` VARCHAR(256) NULL ,
  PRIMARY KEY (`meetingFacilityId`) ,
  CONSTRAINT `fk_meetingFacility_facilityType`
    FOREIGN KEY (`facilityTypeCode` )
    REFERENCES `Rendezvous`.`facilityType` (`facilityTypeCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetingFacility_requestedRooms`
    FOREIGN KEY (`requestedRoomsId` )
    REFERENCES `Rendezvous`.`requestedRooms` (`requestedRoomsId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_meetingFacility_facilityType ON `Rendezvous`.`meetingFacility` (`facilityTypeCode` ASC) ;

CREATE INDEX fk_meetingFacility_requestedRooms ON `Rendezvous`.`meetingFacility` (`requestedRoomsId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`meetingResource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`meetingResource` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`meetingResource` (
  `meetingResourceId` INT NOT NULL AUTO_INCREMENT ,
  `requestedRoomsId` INT NULL ,
  `numberOfConsumables` INT NULL ,
  `resourceComment` VARCHAR(256) NULL ,
  `resourceTypeCode` VARCHAR(12) NULL ,
  PRIMARY KEY (`meetingResourceId`) ,
  CONSTRAINT `fk_meetingResource_resourceType1`
    FOREIGN KEY (`resourceTypeCode` )
    REFERENCES `Rendezvous`.`resourceType` (`resourceTypeCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetingResource_requestedRooms`
    FOREIGN KEY (`requestedRoomsId` )
    REFERENCES `Rendezvous`.`requestedRooms` (`requestedRoomsId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_meetingResource_resourceType1 ON `Rendezvous`.`meetingResource` (`resourceTypeCode` ASC) ;

CREATE INDEX fk_meetingResource_requestedRooms ON `Rendezvous`.`meetingResource` (`requestedRoomsId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`roomFacilities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`roomFacilities` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`roomFacilities` (
  `facilityTypeCode` VARCHAR(12) NOT NULL ,
  `roomId` INT NULL ,
  `facilityNote` VARCHAR(45) NOT NULL COMMENT 'For example, room extension/direct-dial number.' ,
  PRIMARY KEY (`facilityTypeCode`) ,
  CONSTRAINT `fk_facilityType_has_room_facilityType`
    FOREIGN KEY (`facilityTypeCode` )
    REFERENCES `Rendezvous`.`facilityType` (`facilityTypeCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_roomFacilities_room`
    FOREIGN KEY (`roomId` )
    REFERENCES `Rendezvous`.`room` (`roomId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX fk_facilityType_has_room_facilityType ON `Rendezvous`.`roomFacilities` (`facilityTypeCode` ASC) ;

CREATE INDEX fk_roomFacilities_room ON `Rendezvous`.`roomFacilities` (`roomId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`countryHolidays`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`countryHolidays` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`countryHolidays` (
  `countryHolidaysId` INT NOT NULL AUTO_INCREMENT ,
  `countryCode` CHAR(3) NOT NULL ,
  `holidayDate` DATE NULL ,
  `holidayDescription` VARCHAR(64) NULL ,
  PRIMARY KEY (`countryHolidaysId`) ,
  CONSTRAINT `fk_countryHolidays_countryCode`
    FOREIGN KEY (`countryCode` )
    REFERENCES `Rendezvous`.`lkCountryCode` (`countryCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_countryHolidays_countryCode ON `Rendezvous`.`countryHolidays` (`countryCode` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`buildingContact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`buildingContact` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`buildingContact` (
  `buildingContactId` INT NOT NULL AUTO_INCREMENT ,
  `buildingId` INT NOT NULL ,
  `userId` INT NOT NULL ,
  `fromDate` DATE NOT NULL ,
  `untilDate` DATE NULL ,
  PRIMARY KEY (`buildingContactId`) ,
  CONSTRAINT `fk_buildingContact_building`
    FOREIGN KEY (`buildingId` )
    REFERENCES `Rendezvous`.`building` (`buildingId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_buildingContact_user`
    FOREIGN KEY (`userId` )
    REFERENCES `Rendezvous`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_buildingContact_building ON `Rendezvous`.`buildingContact` (`buildingId` ASC) ;

CREATE INDEX fk_buildingContact_user ON `Rendezvous`.`buildingContact` (`userId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`lkNoticeType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`lkNoticeType` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`lkNoticeType` (
  `noticeTypeCode` CHAR(12) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  PRIMARY KEY (`noticeTypeCode`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Rendezvous`.`generalNotices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`generalNotices` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`generalNotices` (
  `noticeId` INT NOT NULL AUTO_INCREMENT ,
  `buildingId` INT NULL ,
  `noticeTypeCode` CHAR(12) NOT NULL ,
  `noticeText` VARCHAR(256) NULL ,
  `validFromDate` DATE NULL ,
  `validUntilDate` DATE NULL ,
  PRIMARY KEY (`noticeId`) ,
  CONSTRAINT `fk_generalNotices_building`
    FOREIGN KEY (`buildingId` )
    REFERENCES `Rendezvous`.`building` (`buildingId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_generalNotices_noticeType`
    FOREIGN KEY (`noticeTypeCode` )
    REFERENCES `Rendezvous`.`lkNoticeType` (`noticeTypeCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_generalNotices_building ON `Rendezvous`.`generalNotices` (`buildingId` ASC) ;

CREATE INDEX fk_generalNotices_noticeType ON `Rendezvous`.`generalNotices` (`noticeTypeCode` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`userNotice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`userNotice` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`userNotice` (
  `userNoticeId` INT NOT NULL AUTO_INCREMENT ,
  `userId` INT NOT NULL ,
  `noticeTypeCode` CHAR(12) NULL ,
  `noticeText` VARCHAR(1024) NULL ,
  PRIMARY KEY (`userNoticeId`) ,
  CONSTRAINT `fk_userNotice_user`
    FOREIGN KEY (`userId` )
    REFERENCES `Rendezvous`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userNotice_noticeType`
    FOREIGN KEY (`noticeTypeCode` )
    REFERENCES `Rendezvous`.`lkNoticeType` (`noticeTypeCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_userNotice_user ON `Rendezvous`.`userNotice` (`userId` ASC) ;

CREATE INDEX fk_userNotice_noticeType ON `Rendezvous`.`userNotice` (`noticeTypeCode` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`openingTimes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`openingTimes` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`openingTimes` (
  `openingTimesId` INT NOT NULL AUTO_INCREMENT ,
  `activeFrom` DATE NOT NULL ,
  `activeUntil` DATE NOT NULL ,
  `dayOfWeek` CHAR(3) NULL ,
  `weekOfMonth` INT NULL ,
  `weekdays` BOOLEAN NULL ,
  `everyday` BOOLEAN NULL ,
  `everydayExceptSun` BOOLEAN NULL ,
  `weekends` BOOLEAN NULL ,
  `buildingId` INT NULL ,
  `floorId` INT NULL ,
  `roomId` INT NULL ,
  PRIMARY KEY (`openingTimesId`) ,
  CONSTRAINT `fk_buildingOpeningHours_building`
    FOREIGN KEY (`buildingId` )
    REFERENCES `Rendezvous`.`building` (`buildingId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_openDefinition_floor`
    FOREIGN KEY (`floorId` )
    REFERENCES `Rendezvous`.`floor` (`floorId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_openingTimes_room`
    FOREIGN KEY (`roomId` )
    REFERENCES `Rendezvous`.`room` (`roomId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_buildingOpeningHours_building ON `Rendezvous`.`openingTimes` (`buildingId` ASC) ;

CREATE INDEX fk_openDefinition_floor ON `Rendezvous`.`openingTimes` (`floorId` ASC) ;

CREATE INDEX fk_openingTimes_room ON `Rendezvous`.`openingTimes` (`roomId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`closedTimes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`closedTimes` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`closedTimes` (
  `closedTimesId` INT NOT NULL AUTO_INCREMENT ,
  `buildingId` INT NULL ,
  `floorId` INT NULL ,
  `roomId` INT NULL ,
  `activeFrom` DATE NULL ,
  `activeUntil` DATE NULL ,
  `dayOfWeek` CHAR(3) NULL ,
  `weekOfMonth` INT NULL ,
  `weekdays` BOOLEAN NULL ,
  `everyday` BOOLEAN NULL ,
  `everydayExceptSun` BOOLEAN NULL ,
  `weekends` BOOLEAN NULL ,
  `closedDate` DATE NULL ,
  `bankHolidays` BOOLEAN NULL ,
  PRIMARY KEY (`closedTimesId`) ,
  CONSTRAINT `fk_buildingClosed_building`
    FOREIGN KEY (`buildingId` )
    REFERENCES `Rendezvous`.`building` (`buildingId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_closedTimes_floor`
    FOREIGN KEY (`floorId` )
    REFERENCES `Rendezvous`.`floor` (`floorId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_closedTimes_room`
    FOREIGN KEY (`roomId` )
    REFERENCES `Rendezvous`.`room` (`roomId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_buildingClosed_building ON `Rendezvous`.`closedTimes` (`buildingId` ASC) ;

CREATE INDEX fk_closedTimes_floor ON `Rendezvous`.`closedTimes` (`floorId` ASC) ;

CREATE INDEX fk_closedTimes_room ON `Rendezvous`.`closedTimes` (`roomId` ASC) ;


-- -----------------------------------------------------
-- Table `Rendezvous`.`resourceInstance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Rendezvous`.`resourceInstance` ;

CREATE  TABLE IF NOT EXISTS `Rendezvous`.`resourceInstance` (
  `resourceId` INT NOT NULL AUTO_INCREMENT ,
  `resourceLabel` VARCHAR(45) NOT NULL ,
  `resourceTypeCode` VARCHAR(12) NULL ,
  PRIMARY KEY (`resourceId`) ,
  CONSTRAINT `fk_resource_resourceType`
    FOREIGN KEY (`resourceTypeCode` )
    REFERENCES `Rendezvous`.`resourceType` (`resourceTypeCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX fk_resource_resourceType ON `Rendezvous`.`resourceInstance` (`resourceTypeCode` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
