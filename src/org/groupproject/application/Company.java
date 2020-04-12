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
import org.groupproject.orders.BackOrder;
import org.groupproject.orders.BackOrderList;
import org.groupproject.orders.Inventory;
import org.groupproject.orders.Purchase;
import org.groupproject.orders.PurchaseList;
import org.groupproject.orders.RepairPlan;
import org.groupproject.orders.RepairPlanList;
import org.groupproject.orders.Sales;

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	private ApplianceList applianceList;
	private CustomerList customerList;
	private Inventory inventory;
	private BackOrderList backOrderList;
	private Sales sales;
	private PurchaseList purchaseList;
	private RepairPlanList repairPlanList;
	private static Company company;

	private Company() {
		applianceList = ApplianceList.instance();
		customerList = CustomerList.instance();
		inventory = Inventory.instance();
		backOrderList = BackOrderList.instance();
		sales = Sales.instance();
		purchaseList = PurchaseList.instance();
		repairPlanList = RepairPlanList.instance();
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

	public Appliance addClothDryerModel(String modelName, String brandName, double price, double repairPlanCost) {
		Appliance appliance = new ClothDryer(modelName, brandName, price, repairPlanCost);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	public Appliance addClothWasherModel(String modelName, String brandName, double price, double repairPlanCost) {
		Appliance appliance = new ClothWasher(modelName, brandName, price, repairPlanCost);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	public Appliance addFurnaceModel(String modelName, String brandName, double price, int heatOutput) {
		Appliance appliance = new Furnace(modelName, brandName, price, heatOutput);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	public Appliance addKitchenRangeModel(String modelName, String brandName, double price) {
		Appliance appliance = new KitchenRange(modelName, brandName, price);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	public Appliance addRefrigeratorModel(String modelName, String brandName, double price, float capacity) {
		Appliance appliance = new Refrigerator(modelName, brandName, price, capacity);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	public Appliance addAppliance(String id, int quantity) {
		Appliance appliance;
		appliance = applianceList.search(id);
		inventory.addToStock(appliance, quantity);
		return appliance;
	}

	public Purchase addPurchase(Customer customer, Appliance appliance, int quantity) {
		Purchase purchase = new Purchase(customer, appliance, quantity);
		purchaseList.insertPurchase(purchase);
		return purchase;
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
		return customerList.search(customerId);
	}

	public Appliance getAppliance(String applianceId) {
		return applianceList.search(applianceId);
	}

	public boolean buyAppliance(String id, int quantity) {
		Appliance appliance;
		boolean result;
		appliance = applianceList.search(id);
		result = inventory.removeFromStock(appliance, quantity);
		if (result == true) {
			sales.addRevenue(appliance.getPrice() * quantity);
		}
		return result;
	}

	public BackOrder createBackOrder(Customer customer, Appliance appliance, int quantity) {
		BackOrder backorder = new BackOrder(customer, appliance, quantity);
		backOrderList.insertBackOrder(backorder);
		return backorder;
	}

	public RepairPlan enrollRepairPlan(String customerId, String applianceId) {
		Appliance appliance = applianceList.search(applianceId);
		Customer customer = customerList.search(customerId);
		if (customer != null) {
			if (appliance instanceof ClothWasher || appliance instanceof ClothDryer) {
				RepairPlan repairPlan = new RepairPlan(customer, appliance);
				repairPlanList.insertRepairPlan(repairPlan);
				return repairPlan;
			}
		}
		return null;
	}

	public RepairPlan withdrawRepairPlan(String customerId, String applianceId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean chargeRepairPlans() {
		repairPlanList.chargeRepairPlans();
		return true;
	}

	public double getPurchaseRevenue() {
		return purchaseList.getTotalRevenue();
	}

	public double getRepairPlanRevenue() {
		return repairPlanList.getRepairPlanRevenue();
	}

	public void getAppliances() {
		applianceList.printAllAppliances();
	}

	public void getAppliancesType(String applianceType) {
		applianceList.printSpecificAppliance(applianceType);
	}

	public RepairPlanList getUsersInRepairPlans() {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerList getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public BackOrder searchBackorder(String applianceId) {
		return backOrderList.search(applianceId);
	}

	public boolean removeBackorder(String applianceId) {
		return backOrderList.removeBackorder(applianceId);
	}

}
