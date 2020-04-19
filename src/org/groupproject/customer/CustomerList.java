package org.groupproject.customer;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the list of customers in the company's system.
 *
 */
public class CustomerList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Customer> customers = new LinkedList<Customer>();
	private static CustomerList customerList;

	private CustomerList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static CustomerList instance() {
		if (customerList == null) {
			return (customerList = new CustomerList());
		} else {
			return customerList;
		}
	}

	/**
	 * Used to add a customer to the list.
	 * 
	 * @param customer
	 * @return true if customer is added to the list
	 */
	public boolean insertCustomer(Customer customer) {
		if (customer != null) {
			customers.add(customer);
			return true;
		}
		return false;
	}

	/**
	 * Used to search for a customer in the list.
	 * 
	 * @param customerId
	 * @return customer if found, null otherwise
	 */
	public Customer search(String customerId) {
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if (customer.getId().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}

	/**
	 * getter for Customers
	 * 
	 * @return customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

}
