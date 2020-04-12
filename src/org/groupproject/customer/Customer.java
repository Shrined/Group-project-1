package org.groupproject.customer;

import java.io.Serializable;

import org.groupproject.application.IdServer;

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

	public String getId() {
		return id;
	}

	public String customerInRepairPlan() {
		if (this.inRepairPlan == true) {
			return "Yes";
		} else {
			return "No";
		}
	}

	public boolean isInRepairPlan() {
		return inRepairPlan;
	}

	public void setInRepairPlan(boolean inRepairPlan) {
		this.inRepairPlan = inRepairPlan;
	}

	public String printInfo() {
		return "Customer [Name = " + name + ", Phone = " + phone + ", id = " + id + ", Repair plan = "
				+ customerInRepairPlan() + "]";
	}

	public String printInfoWithBalance() {
		return "Customer [Name = " + name + ", Phone = " + phone + ", id = " + id + ", Account Balance = " + balance
				+ "]";
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void updateBalance(double balance) {
		this.balance += balance;
	}

	@Override
	public String toString() {
		return "Customer [Name = " + name + ", Phone = " + phone + ", id = " + id + "]";
	}

}
