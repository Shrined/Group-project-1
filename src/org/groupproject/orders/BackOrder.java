package org.groupproject.orders;

import java.io.Serializable;

import org.groupproject.appliances.Appliance;
import org.groupproject.customer.Customer;

public class BackOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private Appliance appliance;
	private int quantity;

	public BackOrder(Customer customer, Appliance appliance, int quantity) {
		this.appliance = appliance;
		this.customer = customer;
		this.quantity = quantity;
	}

	/**
	 * Getter for Customer
	 * 
	 * @return Customer who created the backorder
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Getter for Appliance
	 * 
	 * @return Appliance in the backorder
	 */
	public Appliance getAppliance() {
		return appliance;
	}

	public int getQuantity() {
		return quantity;
	}

	public String printInfo() {
		return appliance.getBrandName() + ", " + appliance.getModelName() + ", Quantity: " + quantity;
	}

	@Override
	public String toString() {
		return "Backorder Info [Customer ID: " + customer.getId() + ", Appliance ID: " + appliance.getId()
				+ ", Quantity: " + quantity + "]";
	}
}
