package org.groupproject.appliances;

import java.io.Serializable;

public class Refrigerator extends Appliance implements Serializable {
	private static final long serialVersionUID = -5396198660202495282L;
	private float capacity;

	public Refrigerator(String brandName, float capacity) {
		super(brandName);
		this.capacity = capacity;
	}

	/**
	 * Getter for appliance capacity
	 * 
	 * @return capacity in liters
	 */
	public float getCapacity() {
		return capacity;
	}

}
