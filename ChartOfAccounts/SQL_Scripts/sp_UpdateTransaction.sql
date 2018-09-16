/* *****************************************************************************
	FILE:		sp_AddTransaction.sql 
	PROJECT:	Version 3- Final Project
	AUTHOR:		Jacob Slaubaugh
	DATE:		2017-12-05
	DESCRIPTION:
		Updates a transaction from the database
		
***************************************************************************** */
USE java2accountproject;

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_UpdateTransaction$$
CREATE PROCEDURE sp_UpdateTransaction(
	IN pTransactionID MEDIUMINT
	,IN pToAccount varchar(100)
	,IN pFromAccount varchar(100)
	,IN pTDate varchar(100)
	,IN pAmount double(100,2)
	,IN pTComment varchar(300)
)
COMMENT 'Retrieves the car with the speficied VIN.'
BEGIN
-- *****************************************************************************
	/*
		Copyright 2017
        FILE: sp_UpdateTransaction.sql
        AUTHOR: Jacob Slaubaugh
        DESCRIPTION:
			Updates a transaction from the database
            
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
	IF pTransactionID = '' THEN
		SIGNAL SQLSTATE '45000' -- Unhandled user defined exception
        SET MESSAGE_TEXT = 'The Transaction ID cannot be empty';
    END IF;
    
    IF pAmount < var_min_amount THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = var_min_amount_error_message;
    END IF;
    
    IF pTComment = '' THEN
		SET pTComment = 'UPDATED';
	END IF;
    
-- *****************************************************************************
-- Primary Logic
	UPDATE transactions
    SET
		transactions.TValid = 0
        , transactions.InvalidReason = 'Transaction has been updated'
        , transactions.UpdateUser = user()
    WHERE transactions.TransactionID = pTransactionID
	;	
    
    INSERT INTO transactions (
        ToAccount
        , FromAccount
        , TDate
        , Amount
        , TValid
        , TComment
        ,UpdateUser
    )
    VALUES (
        pToAccount
        , pFromAccount
        , pTDate
        , pAmount
        , 1
        , pTComment
        , user()
    )
    ;
END$$
DELIMITER ;

-- Allow the java2user to execute this stored procedure
GRANT EXECUTE ON PROCEDURE sp_UpdateTransaction TO 'java2user'@'%';