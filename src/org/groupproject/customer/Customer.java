package org.groupproject.customer;

import java.io.Serializable;

import org.groupproject.application.IdServer;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	private String id;
	private static final String CUSTOMER_STRING = "C";

	public Customer(String name, String phone) {
		this.name = name;
		this.phone = phone;
		id = CUSTOMER_STRING + (IdServer.instance()).getCustomerId();
	}

	@Override
	public String toString() {
		return "Customer [Name = " + name + ", Phone = " + phone + ", id = " + id + "]";
	}

	public String getId() {
		return id;
	}

}
