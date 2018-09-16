/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package awesomeexpress.dataaccessobjects;

import awesomeexpress.dataobjects.Transaction;
import awesomeexpress.interfaces.TransactionDAO;
import domutility.DomUtility;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Reuben
 */
public class TransactionDAOXML implements TransactionDAO{    
    
    static final String FILE_NAME = "transactions.xml";
    
    @Override
    public void createTransaction(ArrayList<Transaction> transactions, Transaction transaction) {
        transactions.add(transaction);
        save(transactions);
    }

    @Override
    public ArrayList<Transaction> readTransactions() throws FileNotFoundException {
        
        ArrayList<Transaction> transactions = null;
        
        try {
            transactions = load();
        } catch (ParserConfigurationException ex) {
            ex.getMessage();
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
            return;
        } catch (ParserConfigurationException ex) {
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
            
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
    /**
     * Saves to an XML file.
     * @param transactions 
     */
    private void save(ArrayList<Transaction> transactions) {
        try {
            Document document = DomUtility.getFreshDocument();
            
            Element rootNode = document.createElement(FILE_NAME);
            
            Element transactionNode;
            Element fromNode;
            Element toNode;
            Element currencyAmountNode;
            Element dateNode;
            Element transactionIDNode;
            Element commentNode;
            
            for (Transaction transaction : transactions) {
                transactionNode = document.createElement("transaction");
                
                fromNode = document.createElement("from");
                fromNode.setTextContent(transaction.getFrom());
                transactionNode.appendChild(fromNode);
                
                toNode = document.createElement("to");
                toNode.setTextContent(transaction.getTo());
                transactionNode.appendChild(toNode);
                
                currencyAmountNode = document.createElement("currencyAmount");
                currencyAmountNode.setTextContent(String.valueOf(transaction.getCurrencyAmount()));
                transactionNode.appendChild(currencyAmountNode);
                
                dateNode = document.createElement("date");
                dateNode.setTextContent(transaction.getDate());
                transactionNode.appendChild(dateNode);
                
                transactionIDNode = document.createElement("transactionID");
                transactionIDNode.setTextContent(String.valueOf(transaction.getTransactionID()));
                transactionNode.appendChild(transactionIDNode);
                
                commentNode = document.createElement("comments");
                commentNode.setTextContent(transaction.getComment());
                transactionNode.appendChild(commentNode);
                
                rootNode.appendChild(transactionNode);
            }
            
            document.appendChild(rootNode);
            DomUtility.writeDom(document, FILE_NAME);
            
        } catch (ParserConfigurationException | TransformerException ex) {
            ex.getMessage();
        }
    }
    
    /**
     * loads transactions from XML
     * @return
     * @throws ParserConfigurationException 
     */
    private ArrayList<Transaction> load() throws ParserConfigurationException {
        
        ArrayList<Transaction> transactions = null;
        
        try{
            Document document = DomUtility.readDocumentFromFile(FILE_NAME);
            
            Element rootNode = document.getDocumentElement();
            NodeList transactionNodes = rootNode.getElementsByTagName("transaction");
            
            if (null!= transactionNodes) {
                Element currentTransaction;
                Element currentAttribute;
                String from;
                String to;
                String currencyAmountString;
                double currencyAmount;
                String date;
                String transactionIDString;
                int transactionID;
                String comment;
                Transaction transaction;
                ArrayList<Transaction> readTransactionList = new ArrayList<>();
                
                for (int i = 0; i < transactionNodes.getLength(); i++) {
                    currentTransaction = (Element)transactionNodes.item(i);
                    
                    currentAttribute = (Element)currentTransaction
                                    .getElementsByTagName("from").item(0);
                    from = currentAttribute.getTextContent();
                    
                    currentAttribute = (Element)currentTransaction
                                    .getElementsByTagName("to").item(0);
                    to = currentAttribute.getTextContent();
                    
                    currentAttribute = (Element)currentTransaction
                                    .getElementsByTagName("currencyAmount").item(0);
                    currencyAmountString = currentAttribute.getTextContent();
                    currencyAmount = Double.parseDouble(currencyAmountString);
                    
                    currentAttribute = (Element)currentTransaction
                                    .getElementsByTagName("date").item(0);
                    date = currentAttribute.getTextContent();
                    
                    currentAttribute = (Element)currentTransaction
                                    .getElementsByTagName("transactionID").item(0);
                    transactionIDString = currentAttribute.getTextContent();
                    transactionID = Integer.parseInt(transactionIDString);
                    
                    currentAttribute = (Element)currentTransaction
                                    .getElementsByTagName("comments").item(0);
                    comment = currentAttribute.getTextContent();
                    
                    transaction = new Transaction(from, to, currencyAmount, 
                            date, transactionID, comment);
                    readTransactionList.add(transaction);
                }
                transactions = readTransactionList;
            }
            
        } catch (SAXException ex) { 
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
      
        return transactions;
    }
}

