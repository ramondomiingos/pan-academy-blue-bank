ALTER TABLE `blue_bank`.`transactions` DROP FOREIGN KEY `transactions_ibfk_3`;
ALTER TABLE `blue_bank`.`transactions` DROP INDEX `type_id`;
ALTER TABLE `blue_bank`.`transactions` DROP `type_id`;

DROP TABLE `blue_bank`.`transaction_type`;

ALTER TABLE `blue_bank`.`transactions`
    ADD `claim` ENUM('C','D') NOT NULL AFTER `destination_account`,
    ADD `type` ENUM('DEPOSIT','DOC','PAYMENT','PIX','TED','WITHDRAW') NOT NULL AFTER `claim`;