package org.groupproject.orders;

import java.io.Serializable;

import org.groupproject.appliances.Appliance;
import org.groupproject.customer.Customer;

/**
 * This class represents a single purchase of an appliance model and its
 * quantity by a customer.
 *
 */
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private Appliance appliance;
	private int quantity;
	private double totalSale;

	public Purchase(Customer customer, Appliance appliance, int quantity) {
		this.appliance = appliance;
		this.customer = customer;
		this.quantity = quantity;
		this.totalSale = appliance.getPrice() * quantity;
	}

	/**
	 * Getter for customer
	 * 
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Getter for appliance
	 * 
	 * @return appliance
	 */
	public Appliance getAppliance() {
		return appliance;
	}

	/**
	 * Getter for quantity
	 * 
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Getter for totalSale
	 * 
	 * @return totalSale
	 */
	public double getTotalSale() {
		return totalSale;
	}

	/**
	 * String for purchase information
	 */
	@Override
	public String toString() {
		return "Purchase: Total = " + totalSale + " , " + customer + ", Appliance = " + appliance + ", Quantity = "
				+ quantity;
	}

}
