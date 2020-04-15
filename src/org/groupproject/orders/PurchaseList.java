package org.groupproject.orders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.groupproject.appliances.Appliance;
import org.groupproject.customer.Customer;

/**
 * This class represents a list of purchases in the company's system.
 *
 */
public class PurchaseList implements Serializable {
	private static final long serialVersionUID = 1L;
	private static PurchaseList purchaseList;
	private List<Purchase> purchases = new ArrayList<Purchase>();
	private double totalRevenue;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private PurchaseList() {
		totalRevenue = 0;
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static PurchaseList instance() {
		if (purchaseList == null) {
			return (purchaseList = new PurchaseList());
		} else {
			return purchaseList;
		}
	}

	/**
	 * This method adds RepairPlan objects to a set
	 * 
	 * @param repairPlan
	 * @return true if the object doesn't exist in the set
	 */
	public boolean insertPurchase(Purchase purchase) {
		totalRevenue += purchase.getTotalSale();
		return purchases.add(purchase);
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	/**
	 * Used to search for a purchase in the purchase list.
	 * 
	 * @param customer
	 * @param appliance
	 * @return purchase if found, and null otherwise
	 */
	public Purchase search(Customer customer, Appliance appliance) {
		for (Purchase purchase : purchases) {
			if (purchase.getCustomer().equals(customer) && purchase.getAppliance().equals(appliance)) {
				return purchase;
			}
		}
		return null;
	}
}
