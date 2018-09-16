/*
 * 
 * Program made for Java II
 * 
 */
package awesomeexpress.interfaces;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import awesomeexpress.dataobjects.Transaction;

/**
 *
 * @author Reuben
 */
public interface TransactionDAO {
    
    void createTransaction(ArrayList<Transaction> transactions, 
            Transaction transaction);
    
    ArrayList<Transaction> readTransactions() throws FileNotFoundException;
    
    void updateTransaction(Transaction transaction);
    
    void deleteTransaction(int id);
    
}
