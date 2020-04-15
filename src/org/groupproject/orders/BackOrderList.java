package org.groupproject.orders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a list of back orders in the company's system.
 *
 */
public class BackOrderList implements Serializable {
	private static final long serialVersionUID = 1L;
	private static BackOrderList backOrderList;
	private ArrayList<BackOrder> backorders = new ArrayList<BackOrder>();

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private BackOrderList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static BackOrderList instance() {
		if (backOrderList == null) {
			return (backOrderList = new BackOrderList());
		} else {
			return backOrderList;
		}
	}

	/**
	 * Used to add a back order to the list.
	 * 
	 * @param backorder
	 * @return true if backorder is added to the list
	 */
	public boolean insertBackOrder(BackOrder backorder) {
		if (backorder != null) {
			backorders.add(backorder);
			return true;
		}
		return false;
	}

	/**
	 * Checks whether a backorder with a given appliance id exists.
	 * 
	 * @param applianceId the id of the appliance
	 * @return the backorder
	 * 
	 */
	public BackOrder search(String applianceId) {
		for (Iterator<BackOrder> iterator = backorders.iterator(); iterator.hasNext();) {
			BackOrder backorder = (BackOrder) iterator.next();
			if (backorder.getAppliance().getId().equals(applianceId)) {
				return backorder;
			}
		}
		return null;
	}

	/**
	 * Removes a backorder from the list
	 * 
	 * @param applianceId
	 * @return true if the backorder is removed
	 */
	public boolean removeBackorder(String applianceId) {
		BackOrder backorder = search(applianceId);
		if (backorder == null) {
			return false;
		} else {
			return backorders.remove(backorder);
		}
	}

	public ArrayList<BackOrder> getBackorders() {
		return backorders;
	}

}
