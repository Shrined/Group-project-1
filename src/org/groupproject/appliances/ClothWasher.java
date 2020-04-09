package org.groupproject.appliances;

import java.io.Serializable;

public class ClothWasher extends Appliance implements Serializable {
	private static final long serialVersionUID = 1784444746354884922L;

	public ClothWasher(String brandName, String modelName) {
		super(brandName, modelName);
	}
}
