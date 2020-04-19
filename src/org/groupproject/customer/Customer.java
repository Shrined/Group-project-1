package org.groupproject.customer;

import java.io.Serializable;

import org.groupproject.application.IdServer;

/**
 * This class represents a single customer.
 *
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	private String id;
	private boolean inRepairPlan;
	private double balance;
	private static final String CUSTOMER_STRING = "C";

	public Customer(String name, String phone) {
		this.name = name;
		this.phone = phone;
		id = CUSTOMER_STRING + (IdServer.instance()).getCustomerId();
	}

	/**
	 * Getter for id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for phone
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Getter for name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Used to check if customer is in a repair plan.
	 * 
	 * @return yes if in a repair plan, no otherwise
	 */
	public String customerInRepairPlan() {
		if (this.inRepairPlan == true) {
			return "Yes";
		} else {
			return "No";
		}
	}

	/**
	 * Checks if customer is in repair plan
	 * 
	 * @return true if customer is in repair plan
	 */
	public boolean isInRepairPlan() {
		return inRepairPlan;
	}

	/**
	 * Sets the inRepairPlan value to true
	 * 
	 * @param inRepairPlan
	 */
	public void setInRepairPlan(boolean inRepairPlan) {
		this.inRepairPlan = inRepairPlan;
	}

	/**
	 * Used to print the customer info and if they are in a repair plan.
	 * 
	 * @return customer info with repair plan
	 */
	public String printInfo() {
		return "Customer [Name = " + name + ", Phone = " + phone + ", id = " + id + ", Repair plan = "
				+ customerInRepairPlan() + "]";
	}

	/**
	 * Used to print the customer info with account balance.
	 * 
	 * @return customer info with account balance
	 */
	public String printInfoWithBalance() {
		return "Customer [Name = " + name + ", Phone = " + phone + ", id = " + id + ", Account Balance = " + balance
				+ "]";
	}

	/**
	 * Getter for balance
	 * 
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Used to update the customer's account balance.
	 * 
	 * @param balance
	 */
	public void updateBalance(double balance) {
		this.balance += balance;
	}

	/**
	 * String of customer information
	 */
	@Override
	public String toString() {
		return "Customer [Name = " + name + ", Phone = " + phone + ", id = " + id + "]";
	}

}
