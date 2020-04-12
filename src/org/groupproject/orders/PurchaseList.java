package org.groupproject.orders;

import java.util.ArrayList;
import java.util.List;

public class PurchaseList {
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
}
