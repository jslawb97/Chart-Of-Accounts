package awesomeexpress.dataaccessobjects;

import awesomeexpress.MainMenu;
import awesomeexpress.dataobjects.Transaction;
import awesomeexpress.interfaces.TransactionDAO;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jacob Slaubaugh
 */
public class TransactionDAODB implements TransactionDAO {

    /*
        Establish connection parameters
     */
    String databaseUrl = "localhost";
    String databasePort = "3306";
    String databaseName = "java2accountproject";
    String databaseUserName = "java2user";
    String databaseUserPassword = "password";

    /*
        Create the Connection String
     */
    String connectionString = "jdbc:mysql://"
            + databaseUrl
            + ":"
            + databasePort
            + "/"
            + databaseName
            + "?useSSL=false"
            + "&noAccessToProcedureBodies=true"
            + "&user=" + databaseUserName
            + "&password=" + databaseUserPassword;

    /**
     * Creates a new transaction to store in the database.
     *
     * @param transactions
     * @param transaction
     */
    @Override
    public void createTransaction(ArrayList<Transaction> transactions, Transaction transaction) {
        transactions.add(transaction);
        save(transactions);
    }

    /**
     * Reads in the transactions to display to the user.
     *
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public ArrayList<Transaction> readTransactions() throws FileNotFoundException {
        ArrayList<Transaction> transactions = null;

        transactions = load();
        MainMenu.printTransactions(transactions);

        return transactions;
    }

    /**
     * Allows the user to make an update to an existing transaction in the
     * database.
     *
     * @param transaction
     */
    @Override
    public void updateTransaction(Transaction transaction) {

        Connection conn;
        int TransactionID = 0;
        String ToAccount = null;
        String FromAccount = null;
        String Date = null;
        double Amount = 0;
        String Comment = null;
        try {
            conn = DriverManager.getConnection(connectionString);

            // Since you can only update one transaction at a time there is
            // no need for a loop here.
            TransactionID = transaction.getTransactionID();
            ToAccount = transaction.getTo();
            FromAccount = transaction.getFrom();
            Date = transaction.getDate();
            Amount = (double) transaction.getCurrencyAmount();
            Comment = transaction.getComment();

            //Create a CallableStatement
            CallableStatement callableStatement
                    = conn.prepareCall("call sp_UpdateTransaction(?,?,?,?,?,?)");

            // Assign a value for the parameter
            callableStatement.setInt(1, TransactionID);
            callableStatement.setString(2, ToAccount);
            callableStatement.setString(3, FromAccount);
            callableStatement.setString(4, Date);
            callableStatement.setDouble(5, Amount);
            callableStatement.setString(6, Comment);

            // Execute the call
            ResultSet resultSet = callableStatement.executeQuery();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    // Allows the user to delete an existing transaction to delete.
    @Override
    public void deleteTransaction(int id) {
        Connection conn;
        int TransactionID = 0;
        String Reason = null;
        try {
            conn = DriverManager.getConnection(connectionString);

            TransactionID = id;
            Reason = MainMenu.getUserReason();

            //Create a CallableStatement
            CallableStatement callableStatement
                    = conn.prepareCall("call sp_DeleteTransactionByID(?,?)");

            // Assign a value for the parameter
            callableStatement.setInt(1, TransactionID);
            callableStatement.setString(2, Reason);

            // Execute the call
            ResultSet resultSet = callableStatement.executeQuery();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    // Saves the new transaction to the database
    private void save(ArrayList<Transaction> transactions) {
        Connection conn;
        String ToAccount = null;
        String FromAccount = null;
        String Date = null;
        double Amount = 0;
        String Comment = null;
        try {
            conn = DriverManager.getConnection(connectionString);

            for (Transaction transaction : transactions) {
                ToAccount = transaction.getTo();
                FromAccount = transaction.getFrom();
                Date = transaction.getDate();
                Amount = (double) transaction.getCurrencyAmount();
                Comment = transaction.getComment();
            }

            //Create a CallableStatement
            CallableStatement callableStatement
                    = conn.prepareCall("call sp_AddTransaction(?,?,?,?,?)");
            // Assign a value for the parameter
            callableStatement.setString(1, ToAccount);
            callableStatement.setString(2, FromAccount);
            callableStatement.setString(3, Date);
            callableStatement.setDouble(4, Amount);
            callableStatement.setString(5, Comment);

            // Execute the call
            ResultSet resultSet = callableStatement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    // Reads in all VALID transactions to display to the user.
    private ArrayList<Transaction> load() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        Connection conn;
        int TransactionID = 0;
        String ToAccount = null;
        String FromAccount = null;
        String Date = null;
        double Amount = 0;
        String Comment = null;
        try {
            conn = DriverManager.getConnection(connectionString);

            //Create a CallableStatement
            CallableStatement callableStatement
                    = conn.prepareCall("call sp_GetTransactionByValid()");

            // Execute the call
            ResultSet resultSet = callableStatement.executeQuery();

            Transaction transaction;

            // A while loop is used here because there is most likely more
            // than one transaction.
            while (resultSet.next()) {
                TransactionID = resultSet.getInt("TransactionID");
                ToAccount = resultSet.getString("ToAccount");
                FromAccount = resultSet.getString("FromAccount");
                Date = resultSet.getString("TDate");
                Amount = resultSet.getDouble("Amount");
                Comment = resultSet.getString("TComment");

                transaction = new Transaction(FromAccount, ToAccount, Amount, Date, TransactionID, Comment);
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
        }

        return transactions;
    }

    // Allows the user to select a transaction to invalidate.
    public void invalidateTransaction(int id) {
        Connection conn;
        int TransactionID = 0;
        String Reason = null;
        try {
            conn = DriverManager.getConnection(connectionString);

            TransactionID = id;
            Reason = MainMenu.getUserReason();

            //Create a CallableStatement
            CallableStatement callableStatement
                    = conn.prepareCall("call sp_InvalidateTransactionByID(?,?)");
            // Assign a value for the parameter
            callableStatement.setInt(1, TransactionID);
            callableStatement.setString(2, Reason);

            // Execute the call
            ResultSet resultSet = callableStatement.executeQuery();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
