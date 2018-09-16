/* *****************************************************************************
	FILE:		sp_AddTransaction.sql 
	PROJECT:	Version 3- Final Project
	AUTHOR:		Jacob Slaubaugh
	DATE:		2017-12-05
	DESCRIPTION:
		Adds a new transaction to the database 
		
***************************************************************************** */
USE java2accountproject;

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_AddTransaction$$
CREATE PROCEDURE sp_AddTransaction(
	IN pToAccount varchar(100)
	,IN pFromAccount varchar(100)
	,IN pTDate varchar(100)
	,IN pAmount double(100,2)
	,IN pTComment varchar(300)
)
COMMENT 'Adds a new transaction record to the transaction table.'
BEGIN
-- *****************************************************************************
	/*
		Copyright 2017
        FILE: sp_AddTransaction.sql
        AUTHOR: Jacob Slaubaugh
        DESCRIPTION:
			Adds a new transaction.
            
            MODIFICATION HISTORY
            2017-12-05 - Jacob Slaubaugh - Original creation date. 
    */
-- *****************************************************************************
--  DEFINE VARIABLES AND CONSTANTS
	DECLARE var_min_amount SMALLINT;
    DECLARE var_min_amount_error_message VARCHAR(100);
    
    SET var_min_amount = 1;
    SET var_min_amount_error_message = CONCAT('The transaction amount cannot be less than '
									, CAST(var_min_amount AS CHAR(100)));
   
-- *****************************************************************************
--  CHECK for valid values
	IF pToAccount = '' THEN
		SIGNAL SQLSTATE '45000' -- Unhandled user defined exception
        SET MESSAGE_TEXT = 'The receiving account cannot be empty';
    END IF;
	
    IF pFromAccount ='' THEN
		SIGNAL SQLSTATE '45000' -- Unhandled user defined exception
        SET MESSAGE_TEXT = 'The sending account cannot be empty';
	END IF;
    
    IF pTDate = '' THEN
		SIGNAL SQLSTATE '45000' -- Unhandled user defined exception
        SET MESSAGE_TEXT = 'The date cannot be empty';
	END IF;
    
    IF pAmount < var_min_amount THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = var_min_amount_error_message;
    END IF;
    
    IF pTComment = '' THEN
		SET pTComment = 'No comment';
	END IF;
    
    
-- *****************************************************************************
-- Primary Logic
	INSERT INTO transactions (
        ToAccount
        , FromAccount
        , TDate
        , Amount
        , TValid
        , TComment
    )
    VALUES (
        pToAccount
        , pFromAccount
        , pTDate
        , pAmount
        , 1
        , pTComment
    )
    ;
    
END$$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE sp_AddTransaction TO 'java2user'@'%';