package org.groupproject.customer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.groupproject.application.IdServer;
import org.groupproject.orders.Purchase;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	private String id;
	private static final String CUSTOMER_STRING = "C";
	private List<Purchase> purchaseList = new LinkedList<Purchase>();

	public Customer(String name, String phone) {
		this.name = name;
		this.phone = phone;
		id = CUSTOMER_STRING + (IdServer.instance()).getCustomerId();
	}

	@Override
	public String toString() {
		return "Customer [Name = " + name + ", Phone = " + phone + ", id = " + id + "]";
	}
}
