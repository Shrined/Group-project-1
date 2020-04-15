package org.groupproject.orders;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.groupproject.appliances.Appliance;
import org.groupproject.appliances.ClothDryer;
import org.groupproject.appliances.ClothWasher;

/**
 * This class represents a list of repair plans in the company's system.
 *
 */
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
	 * Used to check if a customer has a repair plan in the list.
	 * 
	 * @param customerId
	 * @return true if customer has at least one repair plan
	 */
	public boolean containsCostumer(String customerId) {
		for (Iterator<RepairPlan> iterator = repairPlans.iterator(); iterator.hasNext();) {
			RepairPlan repairPlan = (RepairPlan) iterator.next();
			if (repairPlan.getCustomer().getId().equals(customerId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Charges each customer in a repair plan and adds to the total revenue
	 * 
	 * @return true if repair plan list is not empty
	 */
	public boolean chargeRepairPlans() {
		if (repairPlans.isEmpty()) {
			return false;
		}
		for (Iterator<RepairPlan> iterator = repairPlans.iterator(); iterator.hasNext();) {
			RepairPlan repairPlan = (RepairPlan) iterator.next();
			Appliance appliance = repairPlan.getAppliance();
			if (appliance instanceof ClothDryer) {
				repairPlanRevenue += ((ClothDryer) appliance).getRepairPlanCost();
				repairPlan.getCustomer().updateBalance(((ClothDryer) appliance).getRepairPlanCost());
			} else {
				repairPlanRevenue += ((ClothWasher) appliance).getRepairPlanCost();
				repairPlan.getCustomer().updateBalance(((ClothWasher) appliance).getRepairPlanCost());
			}
		}
		return true;
	}

	/**
	 * Gets the total revenue from all repair plans
	 * 
	 * @return total revenue
	 */
	public double getRepairPlanRevenue() {
		return repairPlanRevenue;
	}

	public Set<RepairPlan> getRepairPlans() {
		return repairPlans;
	}

	/**
	 * Used to remove a repair plan from the list.
	 * 
	 * @param customerId
	 * @param applianceId
	 * @return the removed repair plan, and null if the repair plan is not found
	 */
	public RepairPlan remove(String customerId, String applianceId) {
		RepairPlan repairPlan = this.search(customerId, applianceId);
		if (repairPlan != null) {
			repairPlans.remove(repairPlan);
			if (containsCostumer(customerId) == false) {
				repairPlan.getCustomer().setInRepairPlan(false);
			}
			return repairPlan;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Repair Plan List [Repair Plans = " + repairPlans + "]";
	}

}
