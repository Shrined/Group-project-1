package org.groupproject.appliances;

import java.io.Serializable;

/**
 * This class represents a cloth dryer appliance. Cloth dryers are eligible for
 * repair plans.
 *
 */
public class ClothDryer extends Appliance implements Serializable {
	private static final long serialVersionUID = 2672724908084246364L;
	private double repairPlanCost;

	public ClothDryer(String modelName, String brandName, double price, double repairPlanCost) {
		super(brandName, modelName, price);
		this.repairPlanCost = repairPlanCost;
	}

	public double getRepairPlanCost() {
		return repairPlanCost;
	}

	@Override
	public String toString() {
		return "Cloth dryer [Brand name: " + getBrandName() + ", Model name: " + getModelName() + ", Price: "
				+ getPrice() + ", Repair plan cost: " + repairPlanCost + ", id: " + getId() + "]";
	}
}
