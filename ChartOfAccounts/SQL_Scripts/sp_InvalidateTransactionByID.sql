/* *****************************************************************************
	FILE:		sp_InvalidateTransactionByID.sql 
	PROJECT:	Version 3- Final Project
	AUTHOR:		Jacob Slaubaugh
	DATE:		2017-12-05
	DESCRIPTION:
		Invalidates a transaction by the specified ID.
		
***************************************************************************** */
USE java2accountproject;

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_InvalidateTransactionByID$$
CREATE PROCEDURE sp_InvalidateTransactionByID(
	IN pTransactionID MEDIUMINT
	,IN pReason varchar(300)
)
COMMENT 'Invalidates a transaction where the ID matches'
BEGIN
-- *****************************************************************************
	/*
		Copyright 2017
        FILE: sp_InvalidateTransactionByID.sql
        AUTHOR: Jacob Slaubaugh
        DESCRIPTION:
			Invalidates a transaction by the specified ID.
            
            MODIFICATION HISTORY
            2017-12-05 - Jacob Slaubaugh - Original creation date.  
    */
-- *****************************************************************************
    --  CHECK for valid values
	IF pTransactionID = '' THEN
		SIGNAL SQLSTATE '45000' -- Unhandled user defined exception
        SET MESSAGE_TEXT = 'The Transaction ID cannot be empty';
    END IF;
    
    IF pReason = '' THEN
		SIGNAL SQLSTATE '45000' -- Unhandled user defined exception
        SET MESSAGE_TEXT = 'The reason cannot be empty';
    END IF;
    
-- *****************************************************************************
-- Primary Logic
	UPDATE transactions
    SET
		transactions.TValid = 0
        , transactions.UpdateUser = user()
        , transactions.InvalidReason = pReason
    WHERE transactions.TransactionID = pTransactionID
	;	
    
END$$
DELIMITER ;

-- Allow the java2user to execute this stored procedure
GRANT EXECUTE ON PROCEDURE sp_InvalidateTransactionByID TO 'java2user'@'%';