package org.groupproject.appliances;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ApplianceList {
	private List<Appliance> appliances = new LinkedList<Appliance>();
	private static ApplianceList applianceList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private ApplianceList() {
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

}
