package org.groupproject.tests;

import java.util.ArrayList;
import java.util.List;

import org.groupproject.appliances.Appliance;
import org.groupproject.appliances.ClothDryer;
import org.groupproject.appliances.ClothWasher;
import org.groupproject.appliances.Furnace;
import org.groupproject.appliances.KitchenRange;
import org.groupproject.appliances.Refrigerator;
import org.groupproject.application.Company;
import org.groupproject.customer.Customer;
import org.groupproject.orders.BackOrder;
import org.groupproject.orders.Purchase;
import org.groupproject.orders.RepairPlan;

public class Tests {
	private Company company;
	private Customer customer;
	private static Tests tests;

	/**
	 * Customer information
	 */
	private String[] names = new String[] { "George", "Joe", "Jason", "Kevin", "Jack" };
	private String[] phones = new String[] { "612-398-2891", "952-387-9081", "345-989-8192", "816-928-9278",
			"781-928-9877" };
	private List<Customer> customers = new ArrayList<Customer>();

	/**
	 * Appliance information
	 */
	private String[] modelNames = new String[] { "MCD1", "MCD2", "MCD3", "MCD4", "MCW1", "MCW2", "MCW3", "MCW4", "MF1",
			"MF2", "MF3", "MF4", "MKR1", "MKR2", "MKR3", "MKR4", "MR1", "MR2", "MR3", "MR4" };
	private String[] brandNames = new String[] { "BCD1", "BCD2", "BCD3", "BCD4", "BCW1", "BCW2", "BCW3", "BCW4", "BF1",
			"BF2", "BF3", "BF4", "BKR1", "BKR2", "BKR3", "BKR4", "BR1", "BR2", "BR3", "BR4" };
	private Double[] prices = new Double[] { 62.40, 89.30, 291.00, 89.10, 38.90, 28.37, 120.39, 192.38, 98.29, 182.39,
			30.00, 39.98, 48.39, 49.12, 98.32, 18.29, 39.38, 193.21, 189.39, 39.39 };
	private double[] clothDryerRepairCosts = new double[] { 23.35, 43.23, 53.90, 58.00 };
	private double[] clothWasherRepairCosts = new double[] { 43.35, 23.86, 23.70, 68.30, 40.22 };
	private int[] furnaceHeatOutput = new int[] { 450, 300, 840, 290, };
	private float[] refrigeratorCapacity = new float[] { 12.0f, 15.5f, 16.8f, 10.5f, };
	private List<Appliance> appliances = new ArrayList<Appliance>();

	/**
	 * Private constructor supporting the singleton pattern
	 */
	private Tests() {
		company = Company.instance();
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Tests instance() {
		if (tests == null) {
			return (tests = new Tests());
		} else {
			return tests;
		}
	}

	/**
	 * Starts all the tests which includes business processes 1-6
	 */
	public void startTests() {
		processOne();
		processTwo();
		processThree();
		processFour();
		processFive();
		processSix();
	}

	/**
	 * Test for business process 1
	 */
	public void processOne() {
		ClothDryer clothDryer;
		ClothWasher clothWasher;
		Furnace furnace;
		KitchenRange kitchenRange;
		Refrigerator refrigerator;
		for (int i = 0; i < 4; i++) {
			clothDryer = (ClothDryer) company.addClothDryerModel(modelNames[i], brandNames[i], prices[i],
					clothDryerRepairCosts[i]);
			assert clothDryer.getModelName().equals(modelNames[i]);
			assert clothDryer.getBrandName().equals(brandNames[i]);
			assert clothDryer.getPrice() == prices[i];
			assert clothDryer.getRepairPlanCost() == clothDryerRepairCosts[i];
			assert clothDryer.getId().equals("A" + (i + 1));
			appliances.add((Appliance) clothDryer);
		}
		for (int i = 4; i < 8; i++) {
			clothWasher = (ClothWasher) company.addClothWasherModel(modelNames[i], brandNames[i], prices[i],
					clothWasherRepairCosts[i - 4]);
			assert clothWasher.getModelName().equals(modelNames[i]);
			assert clothWasher.getBrandName().equals(brandNames[i]);
			assert clothWasher.getPrice() == prices[i];
			assert clothWasher.getRepairPlanCost() == clothWasherRepairCosts[i - 4];
			assert clothWasher.getId().equals("A" + (i + 1));
			appliances.add((Appliance) clothWasher);
		}
		for (int i = 8; i < 12; i++) {
			furnace = (Furnace) company.addFurnaceModel(modelNames[i], brandNames[i], prices[i],
					furnaceHeatOutput[i - 8]);
			assert furnace.getModelName().equals(modelNames[i]);
			assert furnace.getBrandName().equals(brandNames[i]);
			assert furnace.getPrice() == prices[i];
			assert furnace.getHeatingOutput() == furnaceHeatOutput[i - 8];
			assert furnace.getId().equals("A" + (i + 1));
			appliances.add((Appliance) furnace);
		}
		for (int i = 12; i < 16; i++) {
			kitchenRange = (KitchenRange) company.addKitchenRangeModel(modelNames[i], brandNames[i], prices[i]);
			assert kitchenRange.getModelName().equals(modelNames[i]);
			assert kitchenRange.getBrandName().equals(brandNames[i]);
			assert kitchenRange.getPrice() == prices[i];
			assert kitchenRange.getId().equals("A" + (i + 1));
			appliances.add((Appliance) kitchenRange);
		}
		for (int i = 16; i < 20; i++) {
			refrigerator = (Refrigerator) company.addRefrigeratorModel(modelNames[i], brandNames[i], prices[i],
					refrigeratorCapacity[i - 16]);
			assert refrigerator.getModelName().equals(modelNames[i]);
			assert refrigerator.getBrandName().equals(brandNames[i]);
			assert refrigerator.getPrice() == prices[i];
			assert refrigerator.getCapacity() == refrigeratorCapacity[i - 16];
			assert refrigerator.getId().equals("A" + (i + 1));
			appliances.add((Appliance) refrigerator);
		}
		System.out.println("All of the tests have passed for business process one \n");
	}

	/**
	 * Test for business process 2
	 */
	public void processTwo() {
		Customer customer;
		for (int i = 0; i < names.length; i++) {
			customer = company.addCustomer(names[i], phones[i]);
			assert customer.getName().equals(names[i]);
			assert customer.getPhone().equals(phones[i]);
			assert customer.getId().equals("C" + (i + 1));
			customers.add(customer);
		}
		System.out.println("All of the tests have passed for business process two \n");
	}

	/**
	 * Test for business process 3
	 */
	public void processThree() {
		assert company.addAppliance(appliances.get(0).getId(), 1).equals(appliances.get(0));
		company.createBackOrder(customers.get(1), appliances.get(1), 1);
		assert company.removeBackorder(appliances.get(1).getId());
		System.out.println("All of the tests have passed for business process three \n");
	}

	/**
	 * Test for business process 4
	 */
	public void processFour() {
		BackOrder backorder;
		Purchase purchase;
		backorder = company.createBackOrder(customers.get(2), appliances.get(2), 1);
		assert backorder.getAppliance().equals(appliances.get(2));
		assert backorder.getCustomer().equals(customers.get(2));
		assert backorder.getQuantity() == 1;
		assert company.buyAppliance(appliances.get(0).getId(), 1);
		purchase = company.addPurchase(customers.get(0), appliances.get(0), 1);
		assert purchase.getAppliance().equals(appliances.get(0));
		assert purchase.getCustomer().equals(customers.get(0));
		assert purchase.getQuantity() == 1;
		assert purchase.getTotalSale() == appliances.get(0).getPrice();
		System.out.println("All of the tests have passed for business process four \n");
	}

	/**
	 * Test for business process 5
	 */
	public void processFive() {
		RepairPlan repairPlan;
		repairPlan = Company.instance().enrollRepairPlan(customers.get(0).getId(), appliances.get(0).getId());
		assert repairPlan.getAppliance().equals(appliances.get(0));
		assert repairPlan.getCustomer().equals(customers.get(0));
		System.out.println("All of the tests have passed for business process five \n");
	}

	/**
	 * Test for business process 6
	 */
	public void processSix() {
		RepairPlan repairPlan;
		repairPlan = Company.instance().withdrawRepairPlan(customers.get(0).getId(), appliances.get(0).getId());
		assert repairPlan.getAppliance().equals(appliances.get(0));
		assert repairPlan.getCustomer().equals(customers.get(0));
		System.out.println("All of the tests have passed for business process six \n");
	}
}
