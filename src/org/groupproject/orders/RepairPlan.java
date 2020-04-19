package org.groupproject.orders;

import java.io.Serializable;

import org.groupproject.appliances.Appliance;
import org.groupproject.customer.Customer;

/**
 * This class represents a single repair plan for an appliance. Only cloth
 * washers and cloth dryers are eligible for a repair plan.
 *
 */
public class RepairPlan implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private Appliance appliance;

	public RepairPlan(Customer customer, Appliance appliance) {
		this.customer = customer;
		this.appliance = appliance;
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
	 * Used to print a repair plan including the customer's account balance and the
	 * appliance.
	 * 
	 * @return repair plan with customer's account balance
	 */
	public String printInfo() {
		return customer.printInfoWithBalance() + " " + appliance;
	}

	/**
	 * String of repair plan information
	 */
	@Override
	public String toString() {
		return "RepairPlan [Customer id: " + customer.getId() + ", Appliance id: " + appliance.getId() + "]";
	}

}
