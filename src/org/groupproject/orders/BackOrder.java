package org.groupproject.orders;

import java.io.Serializable;

import org.groupproject.appliances.Appliance;
import org.groupproject.customer.Customer;

/**
 * This class represents a back order created when a purchase can't be
 * fulfilled.
 *
 */
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

	/**
	 * Getter for quantity
	 * 
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Used to print the back order's appliance brand, model, and quantity.
	 * 
	 * @return appliance brand, model, and quantity
	 */
	public String printInfo() {
		return "[Brand name: " + appliance.getBrandName() + ", Model name: " + appliance.getModelName() + ", Quantity: "
				+ quantity;
	}

	/**
	 * String for BackOrder fields
	 */
	@Override
	public String toString() {
		return "Backorder Info [Customer ID: " + customer.getId() + ", Appliance ID: " + appliance.getId()
				+ ", Quantity: " + quantity + "]";
	}
}
