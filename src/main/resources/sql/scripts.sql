create database easymoney;
use easymoney;
CREATE TABLE `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `enabled` INT NOT NULL,
    PRIMARY KEY(`id`)
);
CREATE TABLE `authorities` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `authority` VARCHAR(45) NOT NULL,
    PRIMARY KEY(`id`)
);
INSERT IGNORE INTO `users` VALUES (NULL, 'happy', '12345', '1');
INSERT IGNORE INTO `authorities` VALUES(NULL, 'happy', 'write');
CREATE TABLE `customer` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `email` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `role` varchar(10) NOT NULL,
    PRIMARY KEY(`id`)
);
INSERT INTO `customer` (`email`, `password`, `role`) VALUES ('johndoe@example.com', '54321', 'admin');