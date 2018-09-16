/* *****************************************************************************
	FILE:		sp_DeleteTransactionByID.sql 
	PROJECT:	Version 3- Final Project
	AUTHOR:		Jacob Slaubaugh
	DATE:		2017-12-05
	DESCRIPTION:
		Deletes a specified transaction from the database 
		
***************************************************************************** */
USE java2accountproject;

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_DeleteTransactionByID$$
CREATE PROCEDURE sp_DeleteTransactionByID(
	IN pTransactionID MEDIUMINT
	,IN pReason varchar(300)
)
COMMENT 'Deletes a transaction from the database.'
BEGIN
-- *****************************************************************************
	/*
		Copyright 2017
        FILE: sp_DeleteTransactionByID.sql
        AUTHOR: Jacob Slaubaugh
        DESCRIPTION:
			Deletes a transaction by the specified ID
            
            MODIFICATION HISTORY
            2017-12-05 - Jacob Slaubaugh - Original creation date. 
    */
-- *****************************************************************************

--  DEFINE VARIABLES AND CONSTANTS
	DECLARE sql_error TINYINT DEFAULT FALSE;
    
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET sql_error = TRUE;

-- *****************************************************************************
    --  CHECK for valid values
    IF pReason = '' THEN
		SIGNAL SQLSTATE '45000' -- Unhandled user defined exception
        SET MESSAGE_TEXT = 'The reason cannot be empty';
    END IF;
    
-- *****************************************************************************
-- Primary Logic
	START TRANSACTION;

	INSERT INTO deletedtransactions(
		TransactionID
        , ToAccount
        , FromAccount
        , TDate
        , Amount
        , TComment
        , DeleteUser
        , DeleteReason
        , DeleteDate
    )
    SELECT 
        transactions.TransactionID
        , transactions.ToAccount
        , transactions.FromAccount
        , transactions.TDate
        , transactions.Amount
        , transactions.TComment
        , user()
        , pReason
        , now()
    
    FROM transactions
    WHERE transactions.TransactionID = pTransactionID
    ;
    
    DELETE FROM transactions
    WHERE transactions.TransactionID = pTransactionID
    ;
    
    IF sql_error = FALSE THEN
		COMMIT;
        -- SELECT 'The transaction was deleted.';
	ELSE
		ROLLBACK;
        -- SELECT 'The transaction was kept.';
	END IF;
  
END$$
DELIMITER ;

-- Allow the javaUser to execute this stored procedure
GRANT EXECUTE ON PROCEDURE sp_DeleteTransactionByID TO 'java2user'@'%';