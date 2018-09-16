/*
 * 
 * Program made for Java II
 * 
 */
package awesomeexpress.dataobjects;

import java.util.Objects;

/**
 *
 * @author Reuben
 */
public class Transaction {
    
    /**
     * The account the transaction withdraws from
     */
    private String from;
    
    /**
     * The account the transaction deposits to 
     */
    private String to;
    
    /**
     * The amount of currency moved in the transaction
     */
    private double currencyAmount;
    
    /**
     * The date the transaction occurred
     */
    private String date;
    
    /**
     * The id of the transaction
     */
    private int transactionID;
    
    /**
     * Comments regarding the transaction
     */
    private String comment;

    /**
     * The full constructor
     * @param from
     * @param to
     * @param currencyAmount
     * @param date
     * @param transactionID
     * @param comment 
     */
    public Transaction(String from, String to, double currencyAmount, String date, int transactionID, String comment) {
        this.from = from;
        this.to = to;
        this.currencyAmount = currencyAmount;
        this.date = date;
        this.transactionID = transactionID;
        this.comment = comment;
    }

    public Transaction() {
        
    }

    /**
     * The account the transaction withdraws from
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * The account the transaction withdraws from
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * The account the transaction deposits to
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * The account the transaction deposits to
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * The amount of currency moved in the transaction
     * @return the currencyAmount
     */
    public double getCurrencyAmount() {
        return currencyAmount;
    }

    /**
     * The amount of currency moved in the transaction
     * @param currencyAmount the currencyAmount to set
     */
    public void setCurrencyAmount(double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    /**
     * The date the transaction occurred
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * The date the transaction occurred
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * The id of the transaction
     * @return the transactionID
     */
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * The id of the transaction
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Comments regarding the transaction
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Comments regarding the transaction
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (!Objects.equals(this.transactionID, other.transactionID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.transactionID;
        return hash;
    }
    
}
