package org.groupproject.orders;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.groupproject.appliances.Appliance;

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

	public int searchApplianceQuantity(Appliance appliance) {
		String key = appliance.getId();
		if (stock.get(key) != null) {
			return stock.get(key);
		}
		return 0;
	}

}
