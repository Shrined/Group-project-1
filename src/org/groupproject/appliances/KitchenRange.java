package org.groupproject.appliances;

import java.io.Serializable;

public class KitchenRange extends Appliance implements Serializable {
	private static final long serialVersionUID = 8293907172380653999L;

	public KitchenRange(String brandName, String modelName) {
		super(brandName, modelName);
	}

	@Override
	public String toString() {
		return "Kitchen Range [Brand name: " + getBrandName() + ", Model name: " + getModelName() + ", id: " + getId()
				+ "]";
	}

}
