package org.groupproject.appliances;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.groupproject.orders.Inventory;

/**
 * The ApplianceList class keeps a list of all the appliance models in the
 * company's system.
 *
 */
public class ApplianceList implements Serializable {
	private static final long serialVersionUID = 1L;
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

	/**
	 * Searches for an appliance using the appliance id.
	 * 
	 * @param applianceId
	 * @return appliance if found and null otherwise
	 */
	public Appliance search(String applianceId) {
		for (Iterator iterator = appliances.iterator(); iterator.hasNext();) {
			Appliance appliance = (Appliance) iterator.next();
			if (appliance.getId().equals(applianceId)) {
				return appliance;
			}
		}
		return null;
	}

	/**
	 * Used to print all the appliances in the list and how many are in stock.
	 */
	public void printAllAppliances() {
		for (Appliance appliance : appliances) {
			System.out.println("Appliance [" + appliance.getId() + ", Brand Name: " + appliance.getBrandName()
					+ ", Model Name: " + appliance.getModelName() + ", price: " + appliance.getPrice() + ", quantity: "
					+ inventory.searchApplianceQuantity(appliance) + "]");
		}
	}

	/**
	 * Used to print all models of an specific type of appliance.
	 * 
	 * @param type
	 */
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
