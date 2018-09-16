/*
 * 
 * Program made for Java II
 * 
 */
package awesomeexpress.dataobjects;

/**
 *
 * @author Reuben
 */
public class Account {
    
    /**
     * The ID number of the account
     */
    private int accountId;
    
    /**
     * The main group the account is in
     */
    private String group;
    
    /**
     * The subgroup the account is in
     */
    private String subgroup;
    
    /**
     * The current balance of the account
     */
    private double balance;
    
    /**
     * Tells if the account is active or not
     */
    private int active;

    /**
     * constructor
     * @param accountId
     * @param group
     * @param subgroup
     * @param balance 
     * @param active 
     */
    public Account(int accountId, String group, String subgroup, double balance, 
            int active) {
        this.accountId = accountId;
        this.group = group;
        this.subgroup = subgroup;
        this.balance = balance;
        this.active = active;
    }
    
    public Account(int accountId, String group, String subgroup) {
        this.accountId = accountId;
        this.group = group;
        this.subgroup = subgroup;
    }
    
    /**
     * The ID number of the account
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * The ID number of the account
     * @param accountId the accountId to set
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * The main group the account is in
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * The main group the account is in
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * The subgroup the account is in
     * @return the subgroup
     */
    public String getSubgroup() {
        return subgroup;
    }

    /**
     * The subgroup the account is in
     * @param subgroup the subgroup to set
     */
    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    /**
     * The current balance of the account
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * The current balance of the account
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Tells if the account is active or not
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * Activates an account
     * 
     */
    public void setActive() {
        this.active = 1;
    }
    
    /**
     * Deactivates an account
     */
    public void deactivate() {
        this.active = 0;
    }
    
    @Override
    public String toString() {
        return Integer.toString(accountId) + " " 
                + group + " " 
                + subgroup + " " 
                + Double.toString(balance) +  " " 
                + Integer.toString(active);
    }
}