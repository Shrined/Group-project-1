package org.groupproject.orders;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.groupproject.appliances.Appliance;

/**
 * This class represents the company's appliance inventory.
 *
 */
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> stock = new HashMap();
	private static Inventory inventory;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private Inventory() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Inventory instance() {
		if (inventory == null) {
			return (inventory = new Inventory());
		} else {
			return inventory;
		}
	}

	/**
	 * Used to add an appliance model and its quantity to the inventory.
	 * 
	 * @param appliance
	 * @param quantity
	 * @return true if added to inventory
	 */
	public boolean addToStock(Appliance appliance, int quantity) {
		if (appliance != null) {
			String key = appliance.getId();
			if (stock.containsKey(key)) {
				stock.put(key, stock.get(key) + quantity);
			}
			stock.put(appliance.getId(), quantity);
			return true;
		}
		return false;
	}

	/**
	 * Used to remove the purchased quantity of an appliance model from the
	 * inventory. If there is no more in stock this method removes the appliance
	 * model from the inventory.
	 * 
	 * @param appliance
	 * @param quantity
	 * @return true if quantity is removed
	 */
	public boolean removeFromStock(Appliance appliance, int quantity) {
		if (appliance != null) {
			String key = appliance.getId();
			if (stock.containsKey(key)) {
				if (quantity < stock.get(key)) {
					stock.put(key, stock.get(key) - quantity);
					return true;
				} else if (quantity == stock.get(key)) {
					stock.remove(key);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Used to check the quantity in stock for an appliance model.
	 * 
	 * @param appliance
	 * @return quantity in stock for the appliance model
	 */
	public int searchApplianceQuantity(Appliance appliance) {
		String key = appliance.getId();
		if (stock.get(key) != null) {
			return stock.get(key);
		}
		return 0;
	}

}
