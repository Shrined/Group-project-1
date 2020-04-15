package org.groupproject.appliances;

import java.io.Serializable;

/**
 * This class represents a refrigerator appliance. Refrigerators have a field
 * for capacity.
 *
 */
public class Refrigerator extends Appliance implements Serializable {
	private static final long serialVersionUID = -5396198660202495282L;
	private float capacity;

	public Refrigerator(String brandName, String modelName, double price, float capacity) {
		super(brandName, modelName, price);
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
		return "Refrigerator [Brand name: " + getBrandName() + ", Model name: " + getModelName() + ", Price: "
				+ getPrice() + ", Capacity: " + capacity + ", id: " + getId() + "]";
	}

}
