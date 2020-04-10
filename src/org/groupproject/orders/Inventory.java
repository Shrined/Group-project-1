package org.groupproject.orders;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.groupproject.appliances.Appliance;

public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<Appliance, Integer> stock = new HashMap();
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
		stock.put(appliance, quantity);
		return true;
	}

}
