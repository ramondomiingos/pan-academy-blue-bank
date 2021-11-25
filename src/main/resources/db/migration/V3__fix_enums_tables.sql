ALTER TABLE `clients` CHANGE `type` `type` ENUM('PF','PJ','NP','LP') NOT NULL;
UPDATE `clients` SET `type` = 'NP' WHERE `clients`.`type` = 'PF';
UPDATE `clients` SET `type` = 'LP' WHERE `clients`.`type` = 'PJ';
ALTER TABLE `clients` CHANGE `type` `type` ENUM('NP','LP') NOT NULL;

ALTER TABLE `accounts` CHANGE `type` `type` ENUM('CC','CP','CS','CA','SA') NOT NULL;
UPDATE `accounts` SET `type` = 'CA' WHERE `accounts`.`type` = 'CC';
UPDATE `accounts` SET `type` = 'CA' WHERE `accounts`.`type` = 'CS';
UPDATE `accounts` SET `type` = 'SA' WHERE `accounts`.`type` = 'CP';
ALTER TABLE `accounts` CHANGE `type` `type` ENUM('CA','SA') NOT NULL;
