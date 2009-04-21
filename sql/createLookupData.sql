
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
INSERT INTO `lkUserPriviledge` (`userPriviledgeCode`, `description`) VALUES ('admin',   'Super User');
INSERT INTO `lkUserPriviledge` (`userPriviledgeCode`, `description`) VALUES ('facman',  'Facilities Manager');
INSERT INTO `lkUserPriviledge` (`userPriviledgeCode`, `description`) VALUES ('meetorg', 'Meeting Organiser');
INSERT INTO `lkUserPriviledge` (`userPriviledgeCode`, `description`) VALUES ('meetatt', 'Meeting Attendee');
INSERT INTO `lkUserPriviledge` (`userPriviledgeCode`, `description`) VALUES ('public',  'Public User');
\g

/**
 * User status codes...
 */
INSERT INTO `lkUserStatus` (`status`, `description`) VALUES ('A', 'Active');
INSERT INTO `lkUserStatus` (`status`, `description`) VALUES ('D', 'Disabled');
INSERT INTO `lkUserStatus` (`status`, `description`) VALUES ('N', 'New');
\g
