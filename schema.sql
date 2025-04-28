create database axis;
use axis;
create table customer(
	customerId varchar(10) NOT NULL UNIQUE,
	customername varchar(30) NOT NULL,
    dateOfBirth date NOT NULL,
    phoneNumber varchar(15) NOT NULL,
    emailAddress varchar(100),
    address varchar(100),
    createdAt datetime,
    PRIMARY KEY (customerId)
);
create table branches(
	branchId varchar(10) NOT NULL UNIQUE,
    branchName varchar(100) NOT NULL,
	branchCode VARCHAR(10) NOT NULL UNIQUE,
    ifscCode VARCHAR(11) NOT NULL UNIQUE,
    micrCode varchar(9),
    addressLine1 varchar(100),
    addressLine2 varchar(100),
    city varchar(50),
    state varchar(50),
    postalCode varchar(10),
    phoneNumber varchar(20),
    emailAddress varchar(100),
    branchManagerName varchar(100),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (branchId)
);
create table accounts(
	accountId varchar(15) NOT NULL UNIQUE,
    branchId varchar(10) NOT NULL,
    accountType varchar(30) NOT NULL,
    currencyCode varchar(3) NOT NULL,
    openDate datetime NOT NULL,
    closeDate datetime,
    accountStatus ENUM('ACTIVE', 'DORMANT', 'CLOSED', 'FROZEN', 'SUSPENDED', 'BLOCKED') NOT NULL,
    accountBalance DECIMAL(15,2) NOT NULL,
    PRIMARY KEY (accountId),
    FOREIGN KEY (branchId) references branches(branchId)
);
CREATE TABLE customer_accounts (
    customerId VARCHAR(10) NOT NULL,
    accountId VARCHAR(15) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customerId, accountId),
    FOREIGN KEY (customerId) REFERENCES customer(customerId),
    FOREIGN KEY (accountId) REFERENCES accounts(accountId)
);
create table transactions(
	transactionId varchar(8) NOT NULL UNIQUE,
    accountId varchar(15) NOT NULL,
    transactionDate timestamp DEFAULT current_timestamp,
    transactionCurrencyCode varchar(3) NOT NULL,
    transactionType ENUM('D', 'C') NOT NULL,
    transactionParticulars varchar(30) NOT NULL,
    transactionAmount DECIMAL(15,2) NOT NULL,
    channel varchar(10) NOT NULL,
    transactionStatus ENUM('PENDING', 'SUCCESS', 'FAILED', 'CANCELLED', 'REVERSED') NOT NULL,
    PRIMARY KEY (transactionId),
    FOREIGN KEY (accountId) references accounts(accountId)
);
create table loans(
	loanId varchar(20) NOT NULL UNIQUE,
    accountId VARCHAR(15) NOT NULL,
    loanType ENUM('HOME', 'PERSONAL', 'AUTO', 'EDUCATION', 'BUSINESS') NOT NULL,
    rateType varchar(10),
    disbursedAmount DECIMAL(15,2),
    currentRateOfInterest decimal(3,2),
    emiEndDate datetime,
    tenure int,
    emiAmount decimal,
    sanctionedDate datetime,
    closureDate datetime,
    emiStartDate datetime,
    micrCode varchar(9),
    loanStatus ENUM('ACTIVE', 'PAID_OFF', 'DEFAULTED', 'FORECLOSURE', 'SETTLED', 'WRITTEN_OFF', 'CANCELLED'),
    loanStatusUpdatedAt datetime,
    loanLimit DECIMAL(15,2),
	PRIMARY KEY (loanId),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (accountId) REFERENCES accounts(accountId)
);
CREATE TABLE cards (
    cardId VARCHAR(15) NOT NULL UNIQUE,
    accountId VARCHAR(15) NOT NULL,
    cardType ENUM('CREDIT', 'DEBIT', 'PREPAID') NOT NULL,
    cardNumber VARCHAR(20) NOT NULL,
    cardHolderName VARCHAR(50) NOT NULL,
    issueDate DATE NOT NULL,
    expiryDate DATE NOT NULL,
    cvv VARCHAR(4) NOT NULL,
    cardStatus ENUM('ACTIVE', 'BLOCKED', 'EXPIRED') NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (cardId),
    FOREIGN KEY (accountId) REFERENCES accounts(accountId)
);
ALTER TABLE accounts ADD INDEX idx_branchId (branchId);
ALTER TABLE customer_accounts ADD INDEX idx_customerId (customerId);
ALTER TABLE customer_accounts ADD INDEX idx_accountId (accountId);
ALTER TABLE transactions ADD INDEX idx_accountId (accountId);
ALTER TABLE loans ADD INDEX idx_accountId (accountId);
ALTER TABLE cards ADD INDEX idx_accountId (accountId);
ALTER TABLE transactions ADD INDEX idx_transactionDate (transactionDate);
ALTER TABLE accounts ADD INDEX idx_accountStatus (accountStatus);
ALTER TABLE customer_accounts ADD INDEX idx_customer_account (customerId, accountId);

create database aggregator_db;
use aggregator_db;
CREATE TABLE customer (
    customerId VARCHAR(10) NOT NULL UNIQUE,
    userId VARCHAR(10) NOT NULL UNIQUE,
    firstName varchar(25) NOT NULL,
    lastName varchar(25) NOT NULL,
    password varchar(100) NOT NULL,
    dateOfBirth DATE NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,
    emailAddress VARCHAR(100),
    address VARCHAR(200),
    city VARCHAR(10),
    postalCode VARCHAR(6),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (customerId)
);
CREATE TABLE banks (
    bankId VARCHAR(10) NOT NULL UNIQUE,
    bankName VARCHAR(100) NOT NULL DEFAULT "AXIS",
    bankCode VARCHAR(20) NOT NULL UNIQUE, -- like AXIS, ICICI
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (bankId)
);
CREATE TABLE user_bank_mapping (
    mappingId INT AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(10) NOT NULL UNIQUE,
    bankId VARCHAR(10) NOT NULL,
    bankAccountId VARCHAR(15) NOT NULL,
    accountLinked BOOLEAN DEFAULT FALSE, -- TRUE when user finished authorization
    linkedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customerId) REFERENCES customer(customerId),
    FOREIGN KEY (bankId) REFERENCES banks(bankId)
);