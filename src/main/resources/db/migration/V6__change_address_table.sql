ALTER TABLE `addresses` CHANGE `address` `street` VARCHAR(255) NOT NULL;

ALTER TABLE `addresses`
        ADD `details` VARCHAR(255) NULL AFTER `address_number`,
        ADD `neighborhood` VARCHAR(255) NOT NULL AFTER `details`,
        ADD `zip` VARCHAR(8) NOT NULL AFTER `neighborhood`;