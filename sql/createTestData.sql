
/**
 * Buildings...
 */
INSERT INTO `building` (`buildingName`, `address1`, `address2`, `address3`, `town`, `postcode`, `countryCode`, `activeFrom`, `activeUntil`) VALUES ('Tete-A-Tete House', '17 Garland Street',   '',             '', 'Uxbridge',   'UB8 2WW', 'GBR', '2000-01-01', '2100-01-01');
INSERT INTO `building` (`buildingName`, `address1`, `address2`, `address3`, `town`, `postcode`, `countryCode`, `activeFrom`, `activeUntil`) VALUES ('Meeting Place',     '21 Bloom Street',     'Camden',       '', 'London',     'WC1 7NP', 'GBR', '2000-01-01', '2100-01-01');
INSERT INTO `building` (`buildingName`, `address1`, `address2`, `address3`, `town`, `postcode`, `countryCode`, `activeFrom`, `activeUntil`) VALUES ('Rendezvous Plaza',  '23 Olive Tree Close', 'Broad Street', '', 'Birmingham', 'BP7 8MQ', 'GBR', '2000-01-01', '2100-01-01');
\g

/**
 * Floors for building...
 */
INSERT INTO `floor` (`floorName`, `buildingId`, `displayOrder`) VALUES ('Ground Floor', 1, 1);
INSERT INTO `floor` (`floorName`, `buildingId`, `displayOrder`) VALUES ('1st Floor',    1, 2);
INSERT INTO `floor` (`floorName`, `buildingId`, `displayOrder`) VALUES ('2nd Floor',    1, 3);
INSERT INTO `floor` (`floorName`, `buildingId`, `displayOrder`) VALUES ('3rd Floor',    1, 4);
\g

/**
 * Rooms for floors...
 */
INSERT INTO `room` (`roomCode`, `roomName`, `capacity`, `location`, `selfService`, `floorId`) VALUES ('G1',  'Meeting Room 1', 0, '', 'Y', 1);
INSERT INTO `room` (`roomCode`, `roomName`, `capacity`, `location`, `selfService`, `floorId`) VALUES ('G2',  'Meeting Room 2', 0, '', 'Y', 1);
INSERT INTO `room` (`roomCode`, `roomName`, `capacity`, `location`, `selfService`, `floorId`) VALUES ('1.1', 'Meeting Room 1', 0, '', 'Y', 2);
INSERT INTO `room` (`roomCode`, `roomName`, `capacity`, `location`, `selfService`, `floorId`) VALUES ('1.2', 'Meeting Room 2', 0, '', 'Y', 2);
INSERT INTO `room` (`roomCode`, `roomName`, `capacity`, `location`, `selfService`, `floorId`) VALUES ('1.3', 'Meeting Room 3', 0, '', 'Y', 2);
\g

/**
 * Users
 */
INSERT INTO `user` (`userPreviledgeCode`, `description`, `firstName`, `surname`, `email`, `telephone`, `mobile`) VALUES ('admin', 'Super User', 'Steve', 'Harding', 'steve@email.com', '020 7654 2234', '07776 654522');
\g

