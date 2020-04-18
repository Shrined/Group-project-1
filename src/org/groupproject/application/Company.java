package org.groupproject.application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

/**
 * The Company class stores the entire company data and acts as a Facade for the
 * rest of classes.
 *
 */
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

	/**
	 * Supports Singleton pattern
	 * 
	 * @return Singleton object
	 */
	public static Company instance() {
		if (company == null) {
			IdServer.instance();
			return (company = new Company());
		} else {
			return company;
		}
	}

	/**
	 * Method used to add a customer to the customerList.
	 * 
	 * @param name
	 * @param phone
	 * @return customer
	 */
	public Customer addCustomer(String name, String phone) {
		Customer customer = new Customer(name, phone);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}

	/**
	 * Method used to add a Cloth Dryer model.
	 * 
	 * @param modelName
	 * @param brandName
	 * @param price
	 * @param repairPlanCost
	 * @return
	 */
	public Appliance addClothDryerModel(String modelName, String brandName, double price, double repairPlanCost) {
		Appliance appliance = new ClothDryer(modelName, brandName, price, repairPlanCost);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	/**
	 * Method used to add a Cloth Washer model.
	 * 
	 * @param modelName
	 * @param brandName
	 * @param price
	 * @param repairPlanCost
	 * @return
	 */
	public Appliance addClothWasherModel(String modelName, String brandName, double price, double repairPlanCost) {
		Appliance appliance = new ClothWasher(modelName, brandName, price, repairPlanCost);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	/**
	 * Method used to add a Furnace model.
	 * 
	 * @param modelName
	 * @param brandName
	 * @param price
	 * @param heatOutput
	 * @return
	 */
	public Appliance addFurnaceModel(String modelName, String brandName, double price, int heatOutput) {
		Appliance appliance = new Furnace(modelName, brandName, price, heatOutput);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	/**
	 * Method used to add a Kirchen Range model.
	 * 
	 * @param modelName
	 * @param brandName
	 * @param price
	 * @return
	 */
	public Appliance addKitchenRangeModel(String modelName, String brandName, double price) {
		Appliance appliance = new KitchenRange(modelName, brandName, price);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	/**
	 * Method used to add a Refrigerator model.
	 * 
	 * @param modelName
	 * @param brandName
	 * @param price
	 * @param capacity
	 * @return
	 */
	public Appliance addRefrigeratorModel(String modelName, String brandName, double price, float capacity) {
		Appliance appliance = new Refrigerator(modelName, brandName, price, capacity);
		if (applianceList.insertAppliance(appliance)) {
			return appliance;
		}
		return null;
	}

	/**
	 * Method used to add any kind of appliance to the inventory.
	 * 
	 * @param id
	 * @param quantity
	 * @return appliance
	 */
	public Appliance addAppliance(String id, int quantity) {
		Appliance appliance;
		appliance = applianceList.search(id);
		inventory.addToStock(appliance, quantity);
		return appliance;
	}

	/**
	 * Method used to process a purchase and to add it to the purchaseList.
	 * 
	 * @param customer
	 * @param appliance
	 * @param quantity
	 * @return purchase
	 */
	public Purchase addPurchase(Customer customer, Appliance appliance, int quantity) {
		Purchase purchase = new Purchase(customer, appliance, quantity);
		purchaseList.insertPurchase(purchase);
		return purchase;
	}

	/**
	 * Searches for a customer using the Customer id.
	 * 
	 * @param customerId
	 * @return Customer if found
	 */
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

	/**
	 * Method used to create a back order when an item is not in stock or the
	 * stock's quantity is insuficient.
	 * 
	 * @param customer
	 * @param appliance
	 * @param quantity
	 * @return backorder
	 */
	public BackOrder createBackOrder(Customer customer, Appliance appliance, int quantity) {
		BackOrder backorder = new BackOrder(customer, appliance, quantity);
		backOrderList.insertBackOrder(backorder);
		return backorder;
	}

	/**
	 * Method used to enroll a valid customer and appliance combination into a
	 * repair plan.
	 * 
	 * @param customerId
	 * @param applianceId
	 * @return repair plan
	 */
	public RepairPlan enrollRepairPlan(String customerId, String applianceId) {
		Appliance appliance = applianceList.search(applianceId);
		Customer customer = customerList.search(customerId);
		Purchase purchase = purchaseList.search(customer, appliance);
		if (purchase != null) {
			if (appliance instanceof ClothWasher || appliance instanceof ClothDryer) {
				RepairPlan repairPlan = new RepairPlan(customer, appliance);
				repairPlanList.insertRepairPlan(repairPlan);
				customer.setInRepairPlan(true);
				return repairPlan;
			}
		}
		return null;
	}

	/**
	 * Method used to withdraw a customer from a repair plan.
	 *
	 * @param customerId
	 * @param applianceId
	 * @return withdrawn repair plan
	 */
	public RepairPlan withdrawRepairPlan(String customerId, String applianceId) {
		return repairPlanList.remove(customerId, applianceId);
	}

	/**
	 * Method used to charge all costumers repair plans.
	 * 
	 * @return true if repair plans are found and charged
	 */
	public boolean chargeRepairPlans() {
		return repairPlanList.chargeRepairPlans();
	}

	/**
	 * Method used to get the sales revenue of the company.
	 * 
	 * @return total sales revenue
	 */
	public double getPurchaseRevenue() {
		return purchaseList.getTotalRevenue();
	}

	/**
	 * Method used to get the repair plan revenue of the company.
	 * 
	 * @return total repair plan revenue
	 */
	public double getRepairPlanRevenue() {
		return repairPlanList.getRepairPlanRevenue();
	}

	/**
	 * Method used to list all appliances and how many are in stock.
	 */
	public void getAppliances() {
		applianceList.printAllAppliances();
	}

	/**
	 * Method used to list all the appliances of a specific type.
	 * 
	 * @param applianceType
	 */
	public void getAppliancesType(String applianceType) {
		applianceList.printSpecificAppliance(applianceType);
	}

	/**
	 * Method used to list all the customers currently enrolled in a repair plan and
	 * the appliances for which they have enrolled.
	 * 
	 * @return list of costumer in repair plans
	 */
	public String getUsersInRepairPlans() {
		String output = "";
		for (RepairPlan plan : repairPlanList.getRepairPlans()) {
			output += plan.printInfo() + "\n";
		}
		return output;
	}

	/**
	 * Method used to list all the customers and indicate whether they are enrolled
	 * in a repair plan.
	 * 
	 * @return list of customers
	 */
	public String getCustomers() {
		String output = "";
		for (Customer customer : customerList.getCustomers()) {
			output += customer.printInfo() + "\n";
		}
		return output;
	}

	/**
	 * Method used to list all backorders showing appliance brand, model, and
	 * quantity.
	 * 
	 * @return
	 */
	public String getBackOrders() {
		String output = "";
		for (BackOrder backorder : backOrderList.getBackorders()) {
			output += backorder.printInfo() + "\n";
		}
		return output;
	}

	/**
	 * Method used to search for a backorder in backOrderList using the appliance
	 * id.
	 * 
	 * @param applianceId
	 * @return backorder if found
	 */
	public BackOrder searchBackorder(String applianceId) {
		return backOrderList.search(applianceId);
	}

	/**
	 * Method used to remove a backorder from the backOrderList.
	 * 
	 * @param applianceId
	 * @return true if the backorder was removed
	 */
	public boolean removeBackorder(String applianceId) {
		return backOrderList.removeBackorder(applianceId);
	}

	/**
	 * Serializes the Company data.
	 * 
	 * @return true if the data could be saved
	 */
	public boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("data/CompanyData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(company);
			output.writeObject(IdServer.instance());
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * Returns a deserialized version of company stored on disk.
	 * 
	 * @return a Company object
	 */
	public static Company retrieve() {
		try {
			FileInputStream file = new FileInputStream("data/CompanyData");
			ObjectInputStream input = new ObjectInputStream(file);
			company = (Company) input.readObject();
			IdServer.retrieve(input);
			return company;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

}
