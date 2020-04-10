package org.groupproject.appliances;

import java.io.Serializable;

public class Refrigerator extends Appliance implements Serializable {
	private static final long serialVersionUID = -5396198660202495282L;
	private float capacity;

	public Refrigerator(String brandName, String modelName, float capacity) {
		super(brandName, modelName);
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

	@Override
	public String toString() {
		return "Refrigerator [Brand name: " + getBrandName() + ", Model name: " + getModelName() + ", Capacity: "
				+ capacity + ", id: " + getId() + "]";
	}

}
