/*
 *
 * Program made for Java II
 *
 */
package awesomeexpress.dataaccessobjects;

import awesomeexpress.MainMenu;
import awesomeexpress.dataobjects.Account;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import awesomeexpress.dataobjects.Transaction;
import awesomeexpress.interfaces.TransactionDAO;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Reuben
 */
public class TransactionDAOCSV implements TransactionDAO {

    static final String FILE_NAME = "transactions.csv";

    @Override
    public void createTransaction(ArrayList<Transaction> transactions, Transaction transaction) {

        add(transactions, transaction);

    }

    @Override
    public ArrayList<Transaction> readTransactions() throws FileNotFoundException {
        ArrayList<Transaction> transactions = null;

        try {
            transactions = load();
            MainMenu.printTransactions(transactions);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return transactions;
    }

    @Override
    public void updateTransaction(Transaction transaction) {

        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            transactions = load();
            for (Transaction transaction1 : transactions) {
                if (transaction.equals(transaction1)) {
                    transaction1.setTransactionID(transaction.getTransactionID());
                    transaction1.setFrom(transaction.getFrom());
                    transaction1.setTo(transaction.getTo());
                    transaction1.setCurrencyAmount(transaction.getCurrencyAmount());
                    transaction1.setDate(transaction.getDate());
                    transaction1.setComment(transaction.getComment());
                    save(transactions);
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    @Override
    public void deleteTransaction(int id) {

        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            transactions = load();
            for (Transaction transaction : transactions) {
                if (transaction.getTransactionID() == id) {
                    transactions.remove(transaction);
                    save(transactions);
                    break;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Gets a list of transactions from a csv file
     *
     * @param inputFileName the name of the csv file
     * @return
     * @throws FileNotFoundException
     */
    private static ArrayList<Transaction> load()
            throws FileNotFoundException {

        ArrayList<Transaction> transactions = new ArrayList<>();

        File inputFile = new File(FILE_NAME);

        Scanner in = new Scanner(inputFile);
        String line;
        String[] fields;
        Transaction transaction;
        int id;
        double amt;
        while (in.hasNextLine()) {
            line = in.nextLine();
            fields = line.split(",");
            amt = Double.parseDouble(fields[2]);
            id = Integer.parseInt(fields[4]);
            transaction = new Transaction(fields[0], fields[1], amt,
                    fields[3], id, fields[5]);
            transactions.add(transaction);
        }
        in.close();

        return transactions;
    }

    private static ArrayList<Transaction> add(ArrayList<Transaction> transactions, Transaction transaction) {

        transactions.add(transaction);
        try {
            save(transactions);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        MainMenu.mainMenu();
        return transactions;

    }

    private static void save(ArrayList<Transaction> transactions) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(FILE_NAME);

        for (Transaction t : transactions) {
            writer.println(t.getFrom() + ","
                    + t.getTo() + ","
                    + t.getCurrencyAmount() + ","
                    + t.getDate() + "," + t.getTransactionID()
                    + "," + t.getComment());
        }
        writer.close();

    }

    /**
     * gets list of accounts for the user to select
     *
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<Account> loadAccounts() throws FileNotFoundException {

        ArrayList<Account> accounts = new ArrayList<>();
        File inputFile = new File("accounts.csv");
        Scanner in = new Scanner(inputFile);

        String line;
        String[] fields;
        Account account;
        int id;
        while (in.hasNextLine()) {
            line = in.nextLine();
            fields = line.split(",");
            id = Integer.parseInt(fields[0]);
            account = new Account(id, fields[1], fields[2]);
            accounts.add(account);
        }
        in.close();

        if (accounts.isEmpty() || accounts == null) {
            System.out.println("ERROR: No transactions found.");
        }

        return accounts;
    }

}
