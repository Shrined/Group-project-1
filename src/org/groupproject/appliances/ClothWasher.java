package org.groupproject.appliances;

import java.io.Serializable;

public class ClothWasher extends Appliance implements Serializable {
	private static final long serialVersionUID = 1784444746354884922L;
	private double repairPlanCost;

	public ClothWasher(String brandName, String modelName, double price, double repairPlanCost) {
		super(brandName, modelName, price);
		this.repairPlanCost = repairPlanCost;
	}

	public double getRepairPlanCost() {
		return repairPlanCost;
	}

	@Override
	public String toString() {
		return "Cloth washer [Brand name: " + getBrandName() + ", Model name: " + getModelName() + ", Price: "
				+ getPrice() + ", Repair plan cost: " + repairPlanCost + ", id: " + getId() + "]";
	}
}
