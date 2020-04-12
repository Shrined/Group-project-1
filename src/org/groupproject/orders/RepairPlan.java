package org.groupproject.orders;

import org.groupproject.appliances.Appliance;
import org.groupproject.customer.Customer;

public class RepairPlan {

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

	@Override
	public String toString() {
		return "RepairPlan [cutomerId: " + customer.getId() + ", applianceId: " + appliance.getId() + "]";
	}

}
