Whenever I would switch computers NetBeans wouldn't be able to find the connector j for the database. 
To find it I put it right inside the project folder. When running the sql scripts make sure you execute 
the file JavaII_AccountProject_v03_CreateDB_and_User.sql first. After that it doesn't matter what 
order you run everything else. 


The use cases I had were AddTransaction, DeleteTransaction, InvalidateTransaction, and UpdateTransaction
Here is where you can find them in the java program:

***************** MAIN MENU *****************
Please choose one of the following:
	1) Post Transactions Menu     <-- This is where you can add a new transaction to the database.
	2) List Transactions Menu       
	3) Delete Transactions Menu   <-- This is where you can delete or invalidate transactions in the database.
	4) Update Transactions Menu   <-- This is where you can update an existing transaction in the database.
	5) Exit

When creating or updating a transaction, the TO and FROM accounts are case sensitive and need to be spelled 
correctly in order to work. This is something that our group did on version 1 and I didnt have time to mess
around with.

****** NOTE *******
We didn't have time in the class to add test packages. A better example of creating test packages will be in
the Java III project, which I was in charge of.