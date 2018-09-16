/*
	FILE:	JavaII_AccountProject_v03_CreateDB_and_User.sql
	AUTHOR:	Jacob Slaubaugh
	DATE:	2017-12-05
	DESCRIPTION:
		This is the SQL to create the MySQL database for version 3 of the
		Account project for Java II.  It also creates the User with the 
		specified password.  
*/

-- *****************************************************************************
DROP DATABASE IF EXISTS java2accountproject;
CREATE DATABASE java2accountproject;
USE java2accountproject;

--
-- Table structure for table `account`
-- Not needed for my use cases.
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `AccountID` char(3) NOT NULL COMMENT 'The ID number for the account',
  `Type` varchar(100) NOT NULL COMMENT 'The account type',
  `Name` varchar(100) NOT NULL COMMENT 'The name of the account',
  
  PRIMARY KEY (`AccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Data about accounts';

--
-- Data for table `account`
-- Not needed for my use cases.
--
INSERT INTO `account` 
VALUES 
	('01','Asset','Bank')
	,('102','Asset','Cash')
	,('108','Asset','Deferred Expense')
	,('110','Asset','Other Assets')
	,('112','Asset','Accounts Receivable')
	,('116','Asset','Supplies')
	,('130','Asset','Pre-paid Insurance')
	,('157','Asset','Equipment')
	,('158','Asset','Accumulated Depreciation Equipment')
	,('200','Liability','Notes Payable')
	,('201','Liability','Accounts Payable')
	,('209','Liability','Unearned Service Revenue')
	,('210','Liability','Tax Payable')
	,('211','Liability','Bonds Payable')
	,('212','Liability','Salaries and Wages Payable')
	,('230','Liability','Interest Payable')
	,('300','Equity','Common Stock')
	,('310','Equity','Capital')
	,('330','Equity','Retained Earnings')
	,('400','Revenue','Rental Income')
	,('410','Revenue','Sales Income')
	,('420','Revenue','Interest Income')
	,('570','Expense','Office Expense')
	,('585','Expense','Computer Expenses')
	,('595','Expense','Communication Expense')
	,('597','Expense','Labor and Welfare Expenses')
	,('610','Expense','Advertising Expenses')
	,('599','Expense','Printing and Stationary Expenses')
	,('631','Expense','Supplies Expense')
	,('711','Expense','Depreciation Expense')
	,('722','Expense','Insurance Expense')
	,('726','Expense','Salaries and Wages Expense')
	,('729','Expense','Rent Expense')
	,('732','Expense','Utilities Expense')
	,('905','Expense','Interest Expense')
;

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `TransactionID` MEDIUMINT NOT NULL AUTO_INCREMENT COMMENT 'The ID number for the transaction',
  `ToAccount` varchar(100) NOT NULL COMMENT 'The account that will recieve the payment',
  `FromAccount` varchar(100) NOT NULL COMMENT 'The account that will send the payment',
  `TDate` varchar(100) NOT NULL COMMENT 'The date of the transaction',
  `Amount` double(100,2) NOT NULL COMMENT 'The amount sent',
  `TValid` TINYINT(1) NOT NULL COMMENT 'If the transaction is valid or invalid',
  `TComment` varchar(300) NOT NULL COMMENT 'A comment about the transaction',
  `UpdateUser` varchar(100) NULL COMMENT 'The user who last updated the table',
  `InvalidReason` varchar(300) NULL COMMENT 'The reason the transaction is invalid',
  
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Data about transactions';

--
--  data for table `transactions`
--

INSERT INTO `transactions` 
(ToAccount, FromAccount, TDate, Amount, TValid, TComment) VALUES 
	('Checking', 'Savings',	'10/22/2017', 238.12, 1, 'No comment')
	,('Checking', 'Savings', '8/19/2017',	15.99, 1, 'No comment')
	,('Rent Expense', 'Utilities Expense', '11/22/2016', 100, 1, 'No comment')
	,('Insurance Expense',	'Utilities Expense', '11/22/2013', 5234, 1, 'No comment')
	,('Bank', 'Cash', '11/5/2017', 5840, 1, 'No comment')
	,('Rent Expense', 'Rent Expense', '12/11/2017', 606, 1, 'No comment')
	,('Utilities Expense', 'Interest Expense', '1/11/2017', 1680, 1, 'No comment')
	,('Advertising Expenses', 'Supplies Expense', '1/11/2017', 5087, 1, 'No comment')
	,('Advertising Expenses', 'Interest Expense', '3/11/2017', 1471, 1, 'No comment')
    ,('Insurance Expense',	'Utilities Expense', '11/22/2013', 5234, 1, 'No comment')
;

DROP TABLE IF EXISTS `deletedTransactions`;
CREATE TABLE `deletedTransactions` (
  `DeleteID` MEDIUMINT NOT NULL AUTO_INCREMENT COMMENT 'The ID number for the deleted transaction',
  `TransactionID` MEDIUMINT NOT NULL COMMENT 'The ID number for the transaction',
  `ToAccount` varchar(100) NOT NULL COMMENT 'The account that will recieve the payment',
  `FromAccount` varchar(100) NOT NULL COMMENT 'The account that will send the payment',
  `TDate` varchar(100) NOT NULL COMMENT 'The date of the transaction',
  `Amount` double(100,2) NOT NULL COMMENT 'The amount sent',
  `TComment` varchar(300) NOT NULL COMMENT 'A comment about the transaction',
  `DeleteUser` varchar(100) NULL COMMENT 'The user who deleted the transaction',
  `DeleteReason` varchar(300) NULL COMMENT 'The reason the transaction was deleted',
  `DeleteDate` varchar(100) NULL COMMENT 'The date the transaction was deleted',
  
  PRIMARY KEY (`DeleteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Data about deleted transactions';


-- *****************************************************************************
-- The User Definition
DROP USER IF EXISTS 'java2user'@'%';
CREATE USER 'java2user'@'%'
IDENTIFIED BY 'password'
;



-- *****************************************************************************
--                              END OF SCRIPT
-- *****************************************************************************

