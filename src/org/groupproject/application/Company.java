package org.groupproject.application;

import java.io.Serializable;

import org.groupproject.appliances.Appliance;
import org.groupproject.appliances.ApplianceList;
import org.groupproject.customer.Customer;
import org.groupproject.customer.CustomerList;
import org.groupproject.orders.BackOrderList;
import org.groupproject.orders.Inventory;
import org.groupproject.orders.Purchase;
import org.groupproject.orders.RepairPlan;
import org.groupproject.orders.RepairPlanList;

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	public static Company instance() {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer addCustomer(String name, String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	public Appliance addModel(String modelName, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public Appliance addFurnaceModel(String modelName, String type, int heatOutput) {
		// TODO Auto-generated method stub
		return null;
	}

	public Appliance addRefrigeratorModel(String modelName, String type, int capacity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Appliance addWasherDryerModel(String modelName, String type, double repairPlanCost) {
		// TODO Auto-generated method stub
		return null;
	}

	public Inventory addAppliance(String id, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Purchase addPurchase(Customer customer, Appliance appliance, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	public static Company retrieve() {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer getCustomer(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Appliance getAppliance(String applianceId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean buyAppliance(Appliance appliance, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

	public void createBackOrder(Customer customer, Appliance appliance, int quantity) {
		// TODO Auto-generated method stub

	}

	public RepairPlan enrollRepairPlan(String customerId, String applianceId) {
		// TODO Auto-generated method stub
		return null;
	}

	public RepairPlan withdrawRepairPlan(String customerId, String applianceId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean chargeRepairPlans() {
		return false;
		// TODO Auto-generated method stub

	}

	public double getSalesRevenue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ApplianceList getAppliances() {
		// TODO Auto-generated method stub
		return null;
	}

	public ApplianceList getAppliancesType(String applianceType) {
		// TODO Auto-generated method stub
		return null;
	}

	public RepairPlanList getUsersInRepairPlans() {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerList getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public BackOrderList getBackOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
