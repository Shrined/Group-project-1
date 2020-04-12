package org.groupproject.orders;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.groupproject.appliances.Appliance;
import org.groupproject.appliances.ClothDryer;
import org.groupproject.appliances.ClothWasher;

public class RepairPlanList implements Serializable {
	private static final long serialVersionUID = 1L;
	private static RepairPlanList repairPlanList;
	private Set<RepairPlan> repairPlans = new HashSet<RepairPlan>();
	private double repairPlanRevenue;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private RepairPlanList() {
		repairPlanRevenue = 0;
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static RepairPlanList instance() {
		if (repairPlanList == null) {
			return (repairPlanList = new RepairPlanList());
		} else {
			return repairPlanList;
		}
	}

	/**
	 * This method adds RepairPlan objects to a set
	 * 
	 * @param repairPlan
	 * @return true if the object doesn't exist in the set
	 */
	public boolean insertRepairPlan(RepairPlan repairPlan) {
		return repairPlans.add(repairPlan);
	}

	/**
	 * Checks whether a repair with a given appliance id and customer id exists.
	 * 
	 * @param customerId  the id of the customer
	 * @param applianceId the id of the appliance
	 * @return the repair plan
	 * 
	 */
	public RepairPlan search(String customerId, String applianceId) {
		for (Iterator<RepairPlan> iterator = repairPlans.iterator(); iterator.hasNext();) {
			RepairPlan repairPlan = (RepairPlan) iterator.next();
			if (repairPlan.getAppliance().getId().equals(applianceId)
					&& repairPlan.getCustomer().getId().equals(customerId)) {
				return repairPlan;
			}
		}
		return null;
	}

	/**
	 * Charges each customer in a repair plan and adds to the total revenue
	 */
	public void chargeRepairPlans() {
		for (Iterator<RepairPlan> iterator = repairPlans.iterator(); iterator.hasNext();) {
			RepairPlan repairPlan = (RepairPlan) iterator.next();
			Appliance appliance = repairPlan.getAppliance();
			if (appliance instanceof ClothDryer) {
				repairPlanRevenue += ((ClothDryer) appliance).getRepairPlanCost();
			} else {
				repairPlanRevenue += ((ClothWasher) appliance).getRepairPlanCost();
			}
		}

	}

	/**
	 * Gets the total revenue from all repair plans
	 * 
	 * @return total revenue
	 */
	public double getRepairPlanRevenue() {
		return repairPlanRevenue;
	}

}
