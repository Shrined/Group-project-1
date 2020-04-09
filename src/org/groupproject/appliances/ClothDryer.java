package org.groupproject.appliances;

import java.io.Serializable;

public class ClothDryer extends Appliance implements Serializable {
	private static final long serialVersionUID = 2672724908084246364L;

	public ClothDryer(String brandName, String modelName) {
		super(brandName, modelName);
	}

}
