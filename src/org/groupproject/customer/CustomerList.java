package org.groupproject.customer;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CustomerList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List customers = new LinkedList();
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

	public boolean insertCustomer(Customer customer) {
		customers.add(customer);
		return true;
	}

	public Customer search(String customerId) {
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if (customer.getId().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}

}
