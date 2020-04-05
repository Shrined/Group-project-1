package org.groupproject.application;

/**
 * This class allows for the appliance Id and the customer Id to be serialized. Since the Id's are implemented within the Customer and Appliance classes are static, this class is necessary in order to properly serialize those values.
 * 
 * The purpose of this class is the show how to properly serialize a static field in java
 * 
 * @author Reed Harmon
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class IdServer implements Serializable {

	private int customerIdCounter, applianceIdCounter;
	private static IdServer server;

	/*
	 * Private constructor for singleton pattern 
	 * Sets the customer and appliance Id counters both to 1
	 */
	private IdServer() {
		customerIdCounter = 1;
		applianceIdCounter = 1;
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static IdServer instance() {
		if (server == null) {
			return (server = new IdServer());
		} else {
			return server;
		}
	}

	/**
	 * Getter for customerId
	 * 
	 * @return id of the customer
	 */
	public int getCustomerId() {
		return customerIdCounter++;
	}

	/**
	 * Getter for applianceId
	 * 
	 * @return id of the appliance
	 */
	public int getApplianceId() {
		return applianceIdCounter++;
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return ("CustomerIdServer" + customerIdCounter + "\n"
				+ "ApplianceIdServer" + applianceIdCounter);
	}

	/**
	 * Retrieves the server object
	 * 
	 * @param input
	 *            inputstream for deserialization
	 */
	public static void retrieve(ObjectInputStream input) {
		try {
			server = (IdServer) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

}
