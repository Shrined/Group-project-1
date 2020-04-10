package org.groupproject.application;

import java.io.Serializable;

import org.groupproject.appliances.Appliance;
import org.groupproject.appliances.ApplianceList;
import org.groupproject.appliances.ClothDryer;
import org.groupproject.appliances.ClothWasher;
import org.groupproject.appliances.Furnace;
import org.groupproject.appliances.KitchenRange;
import org.groupproject.appliances.Refrigerator;
import org.groupproject.customer.Customer;
import org.groupproject.customer.CustomerList;
import org.groupproject.orders.BackOrderList;
import org.groupproject.orders.Inventory;
import org.groupproject.orders.Purchase;
import org.groupproject.orders.RepairPlan;
import org.groupproject.orders.RepairPlanList;

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	private ApplianceList applianceList;
	private CustomerList customerList;
	private static Company company;

	private Company() {
		applianceList = ApplianceList.instance();
		customerList = CustomerList.instance();
	}

	public static Company instance() {
		if (company == null) {
			IdServer.instance();
			return (company = new Company());
		} else {
			return company;
		}
	}

	public Customer addCustomer(String name, String phone) {
		Customer customer = new Customer(name, phone);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}

	public Appliance addClothDryerModel(String modelName, String brandName, double repairPlanCost) {
		Appliance appliance = new ClothDryer(modelName, brandName, repairPlanCost);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	public Appliance addClothWasherModel(String modelName, String brandName, double repairPlanCost) {
		Appliance appliance = new ClothWasher(modelName, brandName, repairPlanCost);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	public Appliance addFurnaceModel(String modelName, String brandName, int heatOutput) {
		Appliance appliance = new Furnace(modelName, brandName, heatOutput);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	public Appliance addKitchenRangeModel(String modelName, String brandName) {
		Appliance appliance = new KitchenRange(modelName, brandName);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	public Appliance addRefrigeratorModel(String modelName, String brandName, float capacity) {
		Appliance appliance = new Refrigerator(modelName, brandName, capacity);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
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
