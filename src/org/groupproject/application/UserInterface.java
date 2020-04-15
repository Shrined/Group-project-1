/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */

package org.groupproject.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.groupproject.appliances.Appliance;
import org.groupproject.customer.Customer;
import org.groupproject.orders.BackOrder;
import org.groupproject.orders.Purchase;
import org.groupproject.orders.RepairPlan;

/**
 * 
 * This class implements the user interface for the Appliance Company project.
 * The commands are encoded as integers using a number of static final
 * variables. A number of utility methods exist to make it easier to parse the
 * input.
 *
 */
public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Company company;

	/**
	 * Menu options
	 */
	private static final int EXIT = 0;
	private static final int ADD_MODEL = 1;
	private static final int ADD_CUSTOMER = 2;
	private static final int ADD_TO_INVENTORY = 3;
	private static final int PURCHASE = 4;
	private static final int ENROLL_REPAIR_PLAN = 5;
	private static final int WITHDRAW_REPAIR_PLAN = 6;
	private static final int CHARGE_REPAIR_PLANS = 7;
	private static final int PRINT_REVENUE = 8;
	private static final int LIST_APPLIANCES = 9;
	private static final int LIST_USERS_IN_REPAIR_PLANS = 10;
	private static final int LIST_CUSTOMERS = 11;
	private static final int LIST_BACKORDERS = 12;
	private static final int SAVE = 13;
	private static final int HELP = 14;

	/**
	 * Model options
	 */
	private static final int CLOTH_DRYER = 1;
	private static final int CLOTH_WASHER = 2;
	private static final int FURNACE = 3;
	private static final int KITCHEN_RANGE = 4;
	private static final int REFRIGERATOR = 5;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton Company object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			company = Company.instance();
		}
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " Please type yes or anything else for no");
		if (!more.equalsIgnoreCase("yes") || !more.equalsIgnoreCase("y")) {
			return false;
		}
		return true;
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object
	 * 
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command: " + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 14 as explained below:");
		System.out.println(EXIT + " to  Exit\n");
		System.out.println(ADD_MODEL + "  to  add a model");
		System.out.println(ADD_CUSTOMER + "  to  add a customer");
		System.out.println(ADD_TO_INVENTORY + "  to  add to inventory");
		System.out.println(PURCHASE + "  to  process a purchase ");
		System.out.println(ENROLL_REPAIR_PLAN + "  to  enroll in a repair plan");
		System.out.println(WITHDRAW_REPAIR_PLAN + "  to  withdraw from a repair plan");
		System.out.println(CHARGE_REPAIR_PLANS + "  to  charge repair plans");
		System.out.println(PRINT_REVENUE + "  to  print all revenue");
		System.out.println(LIST_APPLIANCES + "  to  list all the appliances");
		System.out.println(LIST_USERS_IN_REPAIR_PLANS + " to  list users in repair plans");
		System.out.println(LIST_CUSTOMERS + " to  list all customers");
		System.out.println(LIST_BACKORDERS + " to  list all backorders");
		System.out.println(SAVE + " to  save data");
		System.out.println(HELP + " for help");
	}

	/**
	 * Displays all the options for types of appliances the user can add
	 */
	private void applianceOptions() {
		System.out.println(CLOTH_DRYER + " Cloth dryer");
		System.out.println(CLOTH_WASHER + " Cloth washer");
		System.out.println(FURNACE + " Furnace");
		System.out.println(KITCHEN_RANGE + " Kitchen range");
		System.out.println(REFRIGERATOR + " Refrigerator");
	}

	/**
	 * Method to be called for adding a model. Prompts the user for the appropriate
	 * values and uses the appropriate Company method for adding the model.
	 * 
	 */
	public void addModels() {
		int command;
		Appliance result = null;
		do {
			applianceOptions();
			command = Integer
					.parseInt(getToken("Enter a number between 1 and 5 to select what" + " model you wish to add"));
			switch (command) {
			case CLOTH_DRYER: {
				String modelName = getToken("Enter model name");
				String brandName = getToken("Enter brand name");
				double price = Double.parseDouble(getToken("Enter price"));
				double repairPlanCost = Double.parseDouble(getToken("Enter repair plan cost"));
				result = company.addClothDryerModel(modelName, brandName, price, repairPlanCost);
				break;
			}
			case CLOTH_WASHER: {
				String modelName = getToken("Enter model name");
				String brandName = getToken("Enter brand name");
				double price = Double.parseDouble(getToken("Enter price"));
				double repairPlanCost = Double.parseDouble(getToken("Enter repair plan cost"));
				result = company.addClothWasherModel(modelName, brandName, price, repairPlanCost);
				break;
			}
			case FURNACE: {
				String modelName = getToken("Enter model name");
				String brandName = getToken("Enter brand name");
				double price = Double.parseDouble(getToken("Enter price"));
				int heatingOutput = Integer.parseInt(getToken("Enter heating output"));
				result = company.addFurnaceModel(modelName, brandName, price, heatingOutput);
				break;
			}
			case KITCHEN_RANGE: {
				String modelName = getToken("Enter model name");
				String brandName = getToken("Enter brand name");
				double price = Double.parseDouble(getToken("Enter price"));
				result = company.addKitchenRangeModel(modelName, brandName, price);
				break;
			}
			case REFRIGERATOR: {
				String modelName = getToken("Enter model name");
				String brandName = getToken("Enter brand name");
				double price = Double.parseDouble(getToken("Enter price"));
				float capacity = Integer.parseInt(getToken("Enter heating output"));
				result = company.addRefrigeratorModel(modelName, brandName, price, capacity);
				break;
			}
			default:
				System.out.println("Error occurred: Please enter a number between 1-5");
				break;
			}
			if (result == null) {
				System.out.println("Could not add a model");
			} else {
				System.out.println(result);
			}
		} while (yesOrNo("Add more models?"));
	}

	/**
	 * Method to be called for adding a customer. Prompts the user for the
	 * appropriate values and uses the appropriate Company method for adding the
	 * customer.
	 * 
	 */
	public void addCustomer() {
		Customer result;
		String name = getToken("Enter customer name");
		String phone = getToken("Enter phone");
		result = company.addCustomer(name, phone);
		if (result == null) {
			System.out.println("Could not add customer");
		}
		System.out.println(result);
	}

	/**
	 * Method called for adding an appliance to the inventory. Prompts the user for
	 * the appropriate values and uses the appropriate Company method for adding to
	 * inventory.
	 */
	public void addToInventory() {
		Appliance result = null;
		BackOrder backorder;
		do {
			String id = getToken("Enter appliance id");
			while (company.getAppliance(id) == null) {
				System.out.println("Invalid appliance id");
				id = getToken("Re-enter appliance id");
			}
			int quantity = Integer.parseInt(getToken("Enter quantity"));
			while (quantity > 0) {
				backorder = company.searchBackorder(id);
				if (company.removeBackorder(id)) {
					System.out.println("Backorder request fulfilled");
					System.out.println(backorder);
					quantity--;
				}
				break;
			}
			if (quantity != 0) {
				result = company.addAppliance(id, quantity);
			}
			if (result != null) {
				System.out.println(result + " Quantity: " + quantity);
			} else {
				System.out.println("Appliance could not be added to inventory");
			}
		} while (yesOrNo("Add more models to inventory?"));
	}

	/**
	 * Method called for processing purchases. Prompts the user for the customer id
	 * and appliance id. It uses the appropriate company methods to process a
	 * purchase.
	 */
	public void purchase() {
		Purchase result;
		do {
			result = null;
			String customerId = getToken("Enter customer id");
			Customer customer = company.getCustomer(customerId);
			while (customer == null) {
				System.out.println("Invalid customer id");
				customerId = getToken("Re-enter customer id");
				customer = company.getCustomer(customerId);
			}
			String applianceId = getToken("Enter appliance id");
			Appliance appliance = company.getAppliance(applianceId);
			while (appliance == null) {
				System.out.println("Invalid appliance id");
				applianceId = getToken("Re-enter appliance id");
				appliance = company.getAppliance(applianceId);
			}
			int quantity = Integer.parseInt(getToken("Enter quantity"));
			boolean inStock = company.buyAppliance(applianceId, quantity);
			if (inStock == true) {
				result = company.addPurchase(customer, appliance, quantity);
			} else if (inStock == false) {
				System.out.println("This appliance is not in stock. A backorder has been placed");
				System.out.println(company.createBackOrder(customer, appliance, quantity));
			}
			if (result != null) {
				System.out.println(result);
			}
		} while (yesOrNo("Do you want to make another purchase?"));
	}

	/**
	 * Method used to enroll a customer in a repair plan it prompts the user for the
	 * appropriate values and uses the appropriate Company method to enroll the
	 * customer in a repair plan.
	 */
	public void enrollRepairPlan() {
		RepairPlan result;
		String customerId = getToken("Enter customer id");
		String applianceId = getToken("Enter appliance id");
		result = company.enrollRepairPlan(customerId, applianceId);
		if (result != null) {
			System.out.println("Enrollment succesfull");
			System.out.println(result);
		} else {
			System.out.println("Not eligible for repair plan");
		}
	}

	/**
	 * Method used to withdraw a customer from a repair plan it prompts the user for
	 * the appropiate values and uses the appropiate Company method to withdraw the
	 * customer from a repair plan.
	 */
	public void withdrawRepairPlan() {
		RepairPlan result;
		String customerId = getToken("Enter customer id");
		String applianceId = getToken("Enter appliance id");
		result = company.withdrawRepairPlan(customerId, applianceId);
		if (result != null) {
			System.out.println(result);
			System.out.println("Has been withdrawn");
		} else {
			System.out.println("No repair plan found");
		}
	}

	/**
	 * Method used to charge all customers repair plans. It uses the appropiate
	 * Company method to charge all customers repair plans.
	 */
	public void chargeRepairPlan() {
		boolean success = false;
		success = company.chargeRepairPlans();
		if (success == true) {
			System.out.println("Repair plans have been charged");
		} else {
			System.out.println("No repair plans found");
		}
	}

	/**
	 * Method used to print all sales revenue and repair revenue. It uses the
	 * appropiate Company methods to get the repair revenue and get the sales
	 * revenue.
	 */
	public void printRevenue() {
		double purchaseRevenue = company.getPurchaseRevenue();
		double repairRevenue = company.getRepairPlanRevenue();
		System.out.println("Sales revenue: " + purchaseRevenue);
		System.out.println("Repair revenue: " + repairRevenue);
	}

	/**
	 * Method used to list all appliances or to list all the appliances of a
	 * specific type. It uses the appropriate Company methods to get the appliances.
	 */
	public void getAppliances() {
		applianceOptions();
		String applianceType = getToken("Enter a number for the appliances of the specific type"
				+ " or type 'all' to get all appliances of a every type");
		if (applianceType.equalsIgnoreCase("all")) {
			company.getAppliances();
		} else {
			switch (Integer.parseInt(applianceType)) {
			case CLOTH_DRYER:
				company.getAppliancesType("ClothDryer");
				break;
			case CLOTH_WASHER:
				company.getAppliancesType("ClothWasher");
				break;
			case FURNACE:
				company.getAppliancesType("Furnace");
				break;
			case KITCHEN_RANGE:
				company.getAppliancesType("KitchenRange");
				break;
			case REFRIGERATOR:
				company.getAppliancesType("Refrigerator");
				break;
			}
		}
	}

	/**
	 * Method used to list all user repair plans. It uses the appropriate Company
	 * method to get all users in repair plans.
	 */
	public void getUsersInRepairPlans() {
		String result;
		result = company.getUsersInRepairPlans();
		if (!result.equals("")) {
			System.out.println(result);
		} else {
			System.out.println("No users in repair plans found");
		}
	}

	/**
	 * Method used to list all the customers. It uses the appropriate Company method
	 * to get all the customers.
	 */
	public void getCustomers() {
		String result;
		result = company.getCustomers();
		if (!result.equals("")) {
			System.out.println(result);
		} else {
			System.out.println("No customers found");
		}

	}

	/**
	 * Method used to list all the back orders. It uses the appropriate Company
	 * method to get all the backorders.
	 */
	public void getBackorders() {
		String result;
		result = company.getBackOrders();
		if (!result.equals("")) {
			System.out.println(result);
		} else {
			System.out.println("No backorders found");
		}
	}

	/**
	 * Method to be called for saving the Company object. Uses the appropriate
	 * Company method for saving.
	 * 
	 */
	private void save() {
		if (company.save()) {
			System.out.println(" The company has been successfully saved in the file CompanyData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate Company
	 * method for retrieval.
	 * 
	 */
	private void retrieve() {
		try {
			if (company == null) {
				company = Company.retrieve();
				if (company != null) {
					System.out.println(" The company has been successfully retrieved from the file CompanyData \n");
				} else {
					System.out.println("File doesnt exist; creating new company");
					company = Company.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Orchestrates the whole process. Calls the appropriate method for the
	 * different functionalities.
	 * 
	 */
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_MODEL:
				addModels();
				break;
			case ADD_CUSTOMER:
				addCustomer();
				break;
			case ADD_TO_INVENTORY:
				addToInventory();
				break;
			case PURCHASE:
				purchase();
				break;
			case ENROLL_REPAIR_PLAN:
				enrollRepairPlan();
				break;
			case WITHDRAW_REPAIR_PLAN:
				withdrawRepairPlan();
				break;
			case CHARGE_REPAIR_PLANS:
				chargeRepairPlan();
				break;
			case PRINT_REVENUE:
				printRevenue();
				break;
			case LIST_APPLIANCES:
				getAppliances();
				break;
			case LIST_USERS_IN_REPAIR_PLANS:
				getUsersInRepairPlans();
				break;
			case LIST_CUSTOMERS:
				getCustomers();
				break;
			case LIST_BACKORDERS:
				getBackorders();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}