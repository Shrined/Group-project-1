package org.groupproject.orders;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.groupproject.appliances.Appliance;

public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> stock = new HashMap();
	private static Inventory inventory;
	private BackOrderList backOrderList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private Inventory() {
		backOrderList = BackOrderList.instance();
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
		String key = appliance.getId();
		if (appliance != null) {
			if (stock.containsKey(key)) {
				stock.put(key, stock.get(key) + 1);
			}
			stock.put(appliance.getId(), quantity);
			return true;
		}
		return false;
	}

}
