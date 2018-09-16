/* *****************************************************************************
	FILE:		sp_GetTransactionByValid.sql 
	PROJECT:	Version 3- Final Project
	AUTHOR:		Jacob Slaubaugh
	DATE:		2017-12-05
	DESCRIPTION:
		Retrieves all valid transactions. 
		
***************************************************************************** */
USE java2accountproject;

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_GetTransactionByValid$$
CREATE PROCEDURE sp_GetTransactionByValid(

)
COMMENT 'Retrieves all valid transactions'
BEGIN
-- *****************************************************************************
	/*
		Copyright 2017
        FILE: sp_GetTransactionByValid.sql
        AUTHOR: Jacob Slaubaugh
        DESCRIPTION:
			Retrieves all valid transactions.
            
            MODIFICATION HISTORY
            2017-12-05 - Jacob Slaubaugh - Original creation date. 
    */ 
-- *****************************************************************************
-- Primary Logic
	SELECT 
		transactions.TransactionID
        , transactions.ToAccount
        , transactions.FromAccount
        , transactions.TDate
        , transactions.Amount
        , transactions.TValid
        , transactions.TComment
	FROM transactions
    WHERE transactions.TValid = 1
	;	
    
END$$
DELIMITER ;

-- Allow the java2user to execute this stored procedure
GRANT EXECUTE ON PROCEDURE sp_GetTransactionByValid TO 'java2user'@'%';

-- *****************************************************************************
--                               END OF SCRIPT
-- *****************************************************************************
