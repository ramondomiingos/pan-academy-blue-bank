CREATE TABLE IF NOT EXISTS `addresses` (
  `address_id` int PRIMARY KEY AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `address_number` varchar(255),
  `city` varchar(255) NOT NULL,
  `state` char(2) NOT NULL
);

CREATE TABLE IF NOT EXISTS `clients` (
  `client_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `birth_date` date NOT NULL,
  `mother_name` varchar(255) NOT NULL,
  `email` varchar(255) UNIQUE,
  `phone` varchar(255),
  `cellphone` varchar(255),
  `type` ENUM ('PF', 'PJ') NOT NULL,
  `registration` varchar(255) UNIQUE NOT NULL,
  `address_id` int NOT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
  `agency_number` int NOT NULL,
  `account_number` int PRIMARY KEY,
  `account_digit` char(1) NOT NULL,
  `balance` decimal NOT NULL DEFAULT 0,
  `type` ENUM ('CC', 'CP', 'CS') NOT NULL,
  `status` ENUM ('A', 'B', 'C') NOT NULL,
  `client_id` int not null
);

CREATE TABLE IF NOT EXISTS `transaction_type` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `type` ENUM ('C', 'D') NOT NULL,
  `name` varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `transactions` (
  `transaction_id` bigint PRIMARY KEY AUTO_INCREMENT,
  `origin_account` int NOT NULL,
  `destination_account` int NOT NULL,
  `amount` decimal NOT NULL,
  `type` int NOT NULL,
  `created_at` datetime NOT NULL
);

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `client_id` int,
  `role` ENUM ('A', 'U') NOT NULL
);

ALTER TABLE `clients` ADD FOREIGN KEY (`address_id`) REFERENCES `addresses` (`address_id`);

ALTER TABLE `accounts` ADD FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`);

ALTER TABLE `transactions` ADD FOREIGN KEY (`origin_account`) REFERENCES `accounts` (`account_number`);

ALTER TABLE `transactions` ADD FOREIGN KEY (`destination_account`) REFERENCES `accounts` (`account_number`);

ALTER TABLE `transactions` ADD FOREIGN KEY (`type`) REFERENCES `transaction_type` (`id`);

ALTER TABLE `users` ADD FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`);

INSERT IGNORE INTO `users` (`user_id`, `username`, `password`, `role`) values (1, 'admin', '$2a$10$hlUaPFEckFJCWKH9rLnuMuhldK3PVisBFJHmZ7H88hKCOpYlm7UIa', 'A');

-- V1 MIGRATION FOR CHANGE CONSTRAINT
ALTER TABLE `clients` CHANGE `address_id` `address_id` int;