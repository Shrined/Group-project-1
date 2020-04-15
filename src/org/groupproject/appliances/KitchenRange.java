package org.groupproject.appliances;

import java.io.Serializable;

/**
 * This class represents a kitchen range appliance.
 *
 */
public class KitchenRange extends Appliance implements Serializable {
	private static final long serialVersionUID = 8293907172380653999L;

	public KitchenRange(String brandName, String modelName, double price) {
		super(brandName, modelName, price);
	}

	@Override
	public String toString() {
		return "Kitchen Range [Brand name: " + getBrandName() + ", Model name: " + getModelName() + ", Price: "
				+ getPrice() + ", id: " + getId() + "]";
	}

}
