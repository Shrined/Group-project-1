package org.groupproject.orders;

import java.io.Serializable;

import org.groupproject.appliances.Appliance;
import org.groupproject.customer.Customer;

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

	public Customer getCustomer() {
		return customer;
	}

	public Appliance getAppliance() {
		return appliance;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "Purchase: Total = " + totalSale + " , " + customer + ", Appliance = " + appliance + ", Quantity = "
				+ quantity;
	}

}
