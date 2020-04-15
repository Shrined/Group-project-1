package org.groupproject.orders;

import java.io.Serializable;

/**
 * This class contains the sales revenue of the company.
 *
 */
public class Sales implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Sales sales;
	private double salesRevenue;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private Sales() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Sales instance() {
		if (sales == null) {
			return (sales = new Sales());
		} else {
			return sales;
		}
	}

	/**
	 * Used to add the amount of a purchase to the sales revenue.
	 * 
	 * @param amount
	 */
	public void addRevenue(double amount) {
		salesRevenue += amount;
	}

	public double getSalesRevenue() {
		return salesRevenue;
	}

	@Override
	public String toString() {
		return "Sales Revenue = " + salesRevenue;
	}

}
