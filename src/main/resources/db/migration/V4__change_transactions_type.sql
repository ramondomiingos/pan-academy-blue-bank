ALTER TABLE `transactions` DROP FOREIGN KEY `transactions_ibfk_3`;
ALTER TABLE `transactions` DROP INDEX `type_id`;
ALTER TABLE `transactions` DROP `type_id`;

DROP TABLE `transaction_type`;

ALTER TABLE `transactions`
    ADD `claim` ENUM('C','D') NOT NULL AFTER `destination_account`,
    ADD `type` ENUM('DEPOSIT','DOC','PAYMENT','PIX','TED','WITHDRAW') NOT NULL AFTER `claim`;