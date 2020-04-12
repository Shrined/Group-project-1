package org.groupproject.orders;

import java.io.Serializable;

import org.groupproject.appliances.Appliance;
import org.groupproject.customer.Customer;

public class RepairPlan implements Serializable {
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private Appliance appliance;

	public RepairPlan(Customer customer, Appliance appliance) {
		this.customer = customer;
		this.appliance = appliance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Appliance getAppliance() {
		return appliance;
	}

	public String printInfo() {
		return customer.printInfoWithBalance() + " " + appliance;
	}

	@Override
	public String toString() {
		return "RepairPlan [Customer id: " + customer.getId() + ", Appliance id: " + appliance.getId() + "]";
	}

}
