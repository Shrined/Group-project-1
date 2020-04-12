package org.groupproject.appliances;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.groupproject.orders.Inventory;

public class ApplianceList {
	private List<Appliance> appliances = new LinkedList<Appliance>();
	private static ApplianceList applianceList;
	private Inventory inventory;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private ApplianceList() {
		inventory = Inventory.instance();
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ApplianceList instance() {
		if (applianceList == null) {
			return (applianceList = new ApplianceList());
		} else {
			return applianceList;
		}
	}

	/**
	 * Inserts a appliance into the collection
	 * 
	 * @param appliance the appliance to be inserted
	 * @return true if the appliance is inserted successfully
	 */
	public boolean insertAppliance(Appliance appliance) {
		appliances.add(appliance);
		return true;
	}

	public Appliance search(String applianceId) {
		for (Iterator iterator = appliances.iterator(); iterator.hasNext();) {
			Appliance appliance = (Appliance) iterator.next();
			if (appliance.getId().equals(applianceId)) {
				return appliance;
			}
		}
		return null;
	}

	public void printAllAppliances() {
		for (Appliance appliance : appliances) {
			System.out.println("Appliance [" + appliance.getId() + ", Brand Name: " + appliance.getBrandName()
					+ ", Model Name: " + appliance.getModelName() + ", price: " + appliance.getPrice() + ", quantity: "
					+ inventory.searchApplianceQuantity(appliance) + "]");
		}
	}

	public void printSpecificAppliance(String type) {
		for (Appliance appliance : appliances) {
			if (appliance.getClass().getSimpleName().equals(type)) {
				System.out.println("Appliance [" + appliance.getId() + ", Brand Name: " + appliance.getBrandName()
						+ ", Model Name: " + appliance.getModelName() + ", price: " + appliance.getPrice()
						+ ", quantity: " + inventory.searchApplianceQuantity(appliance) + "]");
			}
		}
	}

}
