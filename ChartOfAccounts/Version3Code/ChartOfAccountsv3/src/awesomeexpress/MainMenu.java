/*
 *
 * Program made for Java II
 *
 */
package awesomeexpress;

import awesomeexpress.dataaccessobjects.TransactionDAOCSV;
import awesomeexpress.dataaccessobjects.TransactionDAODB;
import awesomeexpress.dataaccessobjects.TransactionDAOXML;
import awesomeexpress.dataobjects.Account;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import awesomeexpress.dataobjects.Transaction;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jacob Slaubaugh
 */
public class MainMenu {

    static int TRANSACTION_COUNT = 1;
    private static TransactionDAOCSV csv = new TransactionDAOCSV();
    private static TransactionDAOXML xml = new TransactionDAOXML();
    private static TransactionDAODB db = new TransactionDAODB();

    public static void mainMenu() {

        String choice;
        boolean stillRunning = true;
        while (stillRunning) {
            choice = getUserChoice();
            switch (choice) {
                case "1":
                    postTransactionMenu();
                    break;
                case "2":
                    listTransactionMenu();
                    break;
                case "3":
                    deleteTransactionMenu();
                    break;
                case "4":
                    updateTransactionMenu();
                    break;
                case "5":
                    stillRunning = false;
                    break;
                default:
                    System.out.println("Sorry, that is not a valid choice.");
                    System.out.println("Please try again.");
            }
        }
    }

    private static String getUserChoice() {
        String choice = null;
        // Show the menu
        System.out.println("***************** MAIN MENU *****************");
        System.out.println("Please choose one of the following:");
        System.out.println("\t1) Post Transactions Menu");
        System.out.println("\t2) List Transactions Menu");
        System.out.println("\t3) Delete Transactions Menu");
        System.out.println("\t4) Update Transactions Menu");
        System.out.println("\t5) Exit");
        System.out.print("Your choice: ");
        Scanner in = new Scanner(System.in);
        choice = in.nextLine().trim();
        return choice;
    }

    public static void printTransactions(ArrayList<Transaction> transactions) {

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("-----------------------------TRANSACTIONS---------------------------------------");
        System.out.println("ID-------DATE------AMOUNT------FROM--------TO--------COMMENT--------------------");
        for (Transaction t : transactions) {
            System.out.printf("%-5d|%-10.10s|%-10.2f|%-10.10s|%-10.10s|%-30s\n", t.getTransactionID(),
                    t.getDate(), t.getCurrencyAmount(), t.getFrom(),
                    t.getTo(), t.getComment());
        }
    }

    public static void postTransactionMenu() {
        String choice;
        Scanner in = new Scanner(System.in);
        System.out.println("***********Post Transaction Menu***********");
        System.out.println("\t1) Post a new CSV transaction");
        System.out.println("\t2) Post a new XML transaction");
        System.out.println("\t3) Post a new DB transaction");
        System.out.println("\t4) Return to the Main Menu");
        System.out.print("Your choice: ");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                Transaction t = postTransaction();
                try {
                    ArrayList<Transaction> transactions = csv.readTransactions();
                    csv.createTransaction(transactions, t);
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;
            case "2":
                Transaction x = postTransaction();
                try {
                    ArrayList<Transaction> transactions = xml.readTransactions();
                    xml.createTransaction(transactions, x);
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;
            case "3":
                Transaction y = postTransaction();
                try {
                    ArrayList<Transaction> transactions = db.readTransactions();
                    db.createTransaction(transactions, y);
                    System.out.println("Transaction Added to the database.");
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;
            case "4":
                mainMenu();
                break;
            default:
                System.out.println("Sorry, that is not a valid choice.");
                System.out.println("Please try again.");
        }
    }

    public static Transaction postTransaction() {

        Scanner input = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        Transaction transaction = new Transaction();

        System.out.println("\n************** NEW TRANSACTION **************");
        try {
            accounts = csv.loadAccounts();
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        printAccounts(accounts);
        System.out.print("The account name to transfer FROM (case-sensitive): ");
        String from = getUserInput().trim();
        switch (from) {
            case "Bank":
                transaction.setFrom(from);
                break;
            case "Cash":
                transaction.setFrom(from);
                break;
            case "Deferred Expense":
                transaction.setFrom(from);
                break;
            case "Other Assets":
                transaction.setFrom(from);
                break;
            case "Accounts Receivable":
                transaction.setFrom(from);
                break;
            case "Supplies":
                transaction.setFrom(from);
                break;
            case "Pre-paid Insurance":
                transaction.setFrom(from);
                break;
            case "Equipment":
                transaction.setFrom(from);
                break;
            case "Accumulated Depreciation Equipment":
                transaction.setFrom(from);
                break;
            case "Notes Payable":
                transaction.setFrom(from);
                break;
            case "Accounts Payable":
                transaction.setFrom(from);
                break;
            case "Unearned Service Revenue":
                transaction.setFrom(from);
                break;
            case "Tax Payable":
                transaction.setFrom(from);
                break;
            case "Bonds Payable":
                transaction.setFrom(from);
                break;
            case "Salaries and Wages Payable":
                transaction.setFrom(from);
                break;
            case "Interest Payable":
                transaction.setFrom(from);
                break;
            case "Common Stock":
                transaction.setFrom(from);
                break;
            case "Capital":
                transaction.setFrom(from);
                break;
            case "Retained Earnings":
                transaction.setFrom(from);
                break;
            case "Rental Income":
                transaction.setFrom(from);
                break;
            case "Sales Income":
                transaction.setFrom(from);
                break;
            case "Interest Income":
                transaction.setFrom(from);
                break;
            case "Office Expenses":
                transaction.setFrom(from);
                break;
            case "Computer Expenses":
                transaction.setFrom(from);
                break;
            case "Communication Expense":
                transaction.setFrom(from);
                break;
            case "Labor and Welfare Expenses":
                transaction.setFrom(from);
                break;
            case "Advertising Expenses":
                transaction.setFrom(from);
                break;
            case "Printing and Stationary Expenses":
                transaction.setFrom(from);
                break;
            case "Supplies Expense":
                transaction.setFrom(from);
                break;
            case "Depreciation Expense":
                transaction.setFrom(from);
                break;
            case "Insurance Expense":
                transaction.setFrom(from);
                break;
            case "Salaries and Wages Expense":
                transaction.setFrom(from);
                break;
            case "Rent Expense":
                transaction.setFrom(from);
                break;
            case "Utilities Expense":
                transaction.setFrom(from);
                break;
            case "Interest Expense":
                transaction.setFrom(from);
                break;
            default:
                System.out.println("Error, Incorrect Account Name. Please enter"
                        + " a valid Account Name");
                postTransaction();
                break;
        }
        printAccounts(accounts);
        System.out.print("The account name to tranfer TO (case-sensitive): ");
        String to = getUserInput();
        switch (to) {
            case "Bank":
                transaction.setTo(to);
                break;
            case "Cash":
                transaction.setTo(to);
                break;
            case "Deferred Expense":
                transaction.setTo(to);
                break;
            case "Other Assets":
                transaction.setTo(to);
                break;
            case "Accounts Receivable":
                transaction.setTo(to);
                break;
            case "Supplies":
                transaction.setTo(to);
                break;
            case "Pre-paid Insurance":
                transaction.setTo(to);
                break;
            case "Equipment":
                transaction.setTo(to);
                break;
            case "Accumulated Depreciation Equipment":
                transaction.setTo(to);
                break;
            case "Notes Payable":
                transaction.setTo(to);
                break;
            case "Accounts Payable":
                transaction.setTo(to);
                break;
            case "Unearned Service Revenue":
                transaction.setTo(to);
                break;
            case "Tax Payable":
                transaction.setTo(to);
                break;
            case "Bonds Payable":
                transaction.setTo(to);
                break;
            case "Salaries and Wages Payable":
                transaction.setTo(to);
                break;
            case "Interest Payable":
                transaction.setTo(to);
                break;
            case "Common Stock":
                transaction.setTo(to);
                break;
            case "Capital":
                transaction.setTo(to);
                break;
            case "Retained Earnings":
                transaction.setTo(to);
                break;
            case "Rental Income":
                transaction.setTo(to);
                break;
            case "Sales Income":
                transaction.setTo(to);
                break;
            case "Interest Income":
                transaction.setTo(to);
                break;
            case "Office Expenses":
                transaction.setTo(to);
                break;
            case "Computer Expenses":
                transaction.setTo(to);
                break;
            case "Communication Expense":
                transaction.setTo(to);
                break;
            case "Labor and Welfare Expenses":
                transaction.setTo(to);
                break;
            case "Advertising Expenses":
                transaction.setTo(to);
                break;
            case "Printing and Stationary Expenses":
                transaction.setTo(to);
                break;
            case "Supplies Expense":
                transaction.setTo(to);
                break;
            case "Depreciation Expense":
                transaction.setTo(to);
                break;
            case "Insurance Expense":
                transaction.setTo(to);
                break;
            case "Salaries and Wages Expense":
                transaction.setTo(to);
                break;
            case "Rent Expense":
                transaction.setTo(to);
                break;
            case "Utilities Expense":
                transaction.setTo(to);
                break;
            case "Interest Expense":
                transaction.setTo(to);
                break;
            default:
                System.out.println("Error, Incorrect Account Name. Please enter"
                        + " a valid Account Name");
                postTransaction();
                break;
        }

        // Gets a positive number from the user
        System.out.print("\nEnter the amount you would like to transfer, including cents: ");

        String userChoice = getUserInput();
        if (Double.parseDouble(userChoice) > 0) {
            transaction.setCurrencyAmount(Double.parseDouble(userChoice));
        } else {
            System.out.println("Error: Amount must be greater than 0. Returning to main menu.");
            mainMenu();
        }
        // Gets the local date and uses it for the new transaction
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL/dd/yyyy");
        String stringDate = localDate.format(formatter);
        System.out.println("\nUsing the local date on your computer: " + stringDate);
        transaction.setDate(stringDate);

        int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
        transaction.setTransactionID(randomNum);

        System.out.print("\nPlease enter any comments about this transaction: ");
        userChoice = getUserInput();
        transaction.setComment(userChoice);

        System.out.println("Transaction added.");

        return transaction;
    }

    /**
     * Used to get any string input from the user
     */
    private static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String userChoice = in.nextLine();
        return userChoice;
    }

    /**
     * Prints list of accounts for the user to select
     *
     * @param accounts
     */
    private static void printAccounts(ArrayList<Account> accounts) {

        System.out.println("------------------ACCOUNTS------------------");
        System.out.println("ID-----Account Group-----Account Name-------");

        for (Account account : accounts) {
            System.out.printf("%-5d | %-15.10s | %-30.30s\n", account.getAccountId(),
                    account.getGroup(), account.getSubgroup());
        }
    }

    public static void printXML() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        TransactionDAOXML tXML = new TransactionDAOXML();
        //        tXML.createTransaction(transactions, t6);
        try {
            transactions = tXML.readTransactions();
            printXMLTransactions(transactions);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prints a formatted list of transactions to the user
     *
     * @param transactions
     */
    private static void printXMLTransactions(ArrayList<Transaction> transactions) {

        boolean keepGoing = true;
        int lineCount = 0;
        String userInput;

        printHeader();
        // still a WIP, got it to pause once at least
        for (Transaction t : transactions) {
            System.out.printf("%-5d|%-10.10s|%-10.2f|%-10.10s|%-10.10s|%-30s\n", t.getTransactionID(),
                    t.getDate(), t.getCurrencyAmount(), t.getFrom(),
                    t.getTo(), t.getComment());
            lineCount++;
            if (lineCount >= 16) {

                while (keepGoing == true) {
                    System.out.print("Show next page?(y/n): ");
                    userInput = getUserInput();
                    if (userInput.equalsIgnoreCase("y")) {
                        printHeader();
                        keepGoing = false;
                    } else if (userInput.equalsIgnoreCase("n")) {
                        mainMenu();
                    } else {
                        System.out.print("Invalid user choice. Please try again: ");
                        userInput = getUserInput();
                    }
                }
            }
        }
        mainMenu();
    }

    private static void printHeader() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("-----------------------------TRANSACTIONS---------------------------------------");
        System.out.println("ID-------DATE------AMOUNT------FROM--------TO--------COMMENT--------------------");
    }

    private static int getTransactionIDFromUser() {
        int id;
        String stringID;
        System.out.print("Enter the ID of the existing transaction: ");
        stringID = getUserInput();
        id = Integer.parseInt(stringID);
        return id;
    }

    private static void listTransactionMenu() {
        String choice;
        Scanner in = new Scanner(System.in);
        System.out.println("***********View Transaction Menu***********");
        System.out.println("\t1) View all CSV transactions");
        System.out.println("\t2) View all XML transactions");
        System.out.println("\t3) View all DB transactions");
        System.out.println("\t4) Return to the Main Menu");
        System.out.print("Your choice: ");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                try {
                    csv.readTransactions();
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;
            case "2":
                printXML();
                break;
            case "3":
                System.out.println("Getting transactions from the database...");
                try {
                    db.readTransactions();
                } catch (FileNotFoundException ex) {
                    System.out.println("ERROR: " + ex);
                }
                break;
            case "4":
                mainMenu();
                break;
            default:
                System.out.println("Sorry, that is not a valid choice.");
                System.out.println("Please try again.");
        }
    }

    private static void deleteTransactionMenu() {
        String choice;
        Scanner in = new Scanner(System.in);
        System.out.println("***********Delete Transaction Menu***********");
        System.out.println("\t1) Delete CSV transaction");
        System.out.println("\t2) Delete XML transaction");
        System.out.println("\t3) Delete DB tansaction");
        System.out.println("\t4) Invalidate DB transaction");
        System.out.println("\t5) Return to the Main Menu");
        System.out.print("Your choice: ");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                int id = getTransactionIDFromUser();
                csv.deleteTransaction(id);
                break;
            case "2":
                id = getTransactionIDFromUser();
                xml.deleteTransaction(id);
                break;
            case "3":
                System.out.println("\nGetting transactions from the database...");
                System.out.println("Please choose a transaction to delete: ");
                try {
                    db.readTransactions();
                } catch (FileNotFoundException ex) {
                    System.out.println("ERROR: " + ex);
                }
                id = getTransactionIDFromUser();
                db.deleteTransaction(id);
                System.out.println("Transaction has been deleted.");
                break;
            case "4":
                System.out.println("\nGetting transactions from the database...");
                System.out.println("Please choose a transaction to invalidate: ");
                try {
                    db.readTransactions();
                } catch (FileNotFoundException ex) {
                    System.out.println("ERROR: " + ex);
                }
                id = getTransactionIDFromUser();
                db.invalidateTransaction(id);
                System.out.println("Transaction has been marked as invalid.");
                break;
            case "5":
                mainMenu();
                break;
            default:
                System.out.println("Sorry, that is not a valid choice.");
                System.out.println("Please try again.");
        }
    }

    private static void updateTransactionMenu() {
        String choice;
        Scanner in = new Scanner(System.in);
        System.out.println("***********Update Transaction Menu***********");
        System.out.println("\t1) Update CSV transaction");
        System.out.println("\t2) Update XML transaction");
        System.out.println("\t3) Update DB tansaction");
        System.out.println("\t4) Return to the Main Menu");
        System.out.print("Your choice: ");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                int id = getTransactionIDFromUser();
                Transaction transaction = postTransaction();
                transaction.setTransactionID(id);
                csv.updateTransaction(transaction);
                break;
            case "2":
                id = getTransactionIDFromUser();
                transaction = postTransaction();
                transaction.setTransactionID(id);
                xml.updateTransaction(transaction);
                break;
            case "3":
                System.out.println("\nGetting transactions from the database...");
                System.out.println("Please choose a transaction to update: ");
                try {
                    db.readTransactions();
                    id = getTransactionIDFromUser();
                    transaction = postTransaction();
                    transaction.setTransactionID(id);
                    db.updateTransaction(transaction);
                    System.out.println("\nDatabase Updated.");
                } catch (FileNotFoundException ex) {
                    System.out.println("ERROR: " + ex);
                }

                break;
            case "4":
                mainMenu();
                break;
            default:
                System.out.println("Sorry, that is not a valid choice.");
                System.out.println("Please try again.");
        }
    }

    public static String getUserReason() {
        String reason;
        System.out.print("Enter the reason for modifying this transaction: ");
        reason = getUserInput();
        return reason;
    }
}
