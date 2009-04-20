
/**
 * Country codes...
 */
INSERT INTO `lkCountryCode` (`countryCode`, `countryName`) VALUES ('GBR', 'United Kingdom');
INSERT INTO `lkCountryCode` (`countryCode`, `countryName`) VALUES ('USA', 'United States');
INSERT INTO `lkCountryCode` (`countryCode`, `countryName`) VALUES ('BMU', 'Bermuda');
\g

/**
 * User priviledge codes...
 */
INSERT INTO `lkUserPreviledge` (`userPriviledgeCode`, `description`) VALUES ('admin',   'Super User');
INSERT INTO `lkUserPreviledge` (`userPriviledgeCode`, `description`) VALUES ('facman',  'Facilities Manager');
INSERT INTO `lkUserPreviledge` (`userPriviledgeCode`, `description`) VALUES ('meetorg', 'Meeting Organiser');
INSERT INTO `lkUserPreviledge` (`userPriviledgeCode`, `description`) VALUES ('meetatt', 'Meeting Attendee');
INSERT INTO `lkUserPreviledge` (`userPriviledgeCode`, `description`) VALUES ('public',  'Public User');
\g
