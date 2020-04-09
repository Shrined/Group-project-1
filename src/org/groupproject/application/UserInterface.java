package org.groupproject.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.groupproject.appliances.Appliance;
import org.groupproject.appliances.ApplianceList;
import org.groupproject.customer.Customer;
import org.groupproject.customer.CustomerList;
import org.groupproject.orders.BackOrderList;
import org.groupproject.orders.Inventory;
import org.groupproject.orders.Purchase;
import org.groupproject.orders.RepairPlan;
import org.groupproject.orders.RepairPlanList;

/**
 * 
 * This class implements the user interface for the Appliance Company project.
 * The commands are encoded as integers using a number of static final
 * variables. A number of utility methods exist to make it easier to parse the
 * input.
 *
 * @author Daniel Garces
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
	private static final int ADD_TO_INVETORY = 3;
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
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
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
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
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
		System.out.println(ADD_TO_INVETORY + "  to  add to invetory");
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
		System.out.println("Enter a number between 1 and 5 to select what model" + " you wish to add");
		System.out.println(CLOTH_DRYER + " To add a cloth dryer");
		System.out.println(CLOTH_WASHER + " To add a cloth washer");
		System.out.println(FURNACE + " To add a furnace");
		System.out.println(KITCHEN_RANGE + " To add a kitchen range");
		System.out.println(REFRIGERATOR + " To add a refrigerator");
	}

	/**
	 * Method to be called for adding a model. Prompts the user for the appropriate
	 * values and uses the appropriate Company method for adding the model.
	 * 
	 */
	public void addModels() {
		Appliance result;
		applianceOptions();
		Scanner input = new Scanner(System.in);
		do {
			int selection = input.nextInt();
			switch (selection) {
			case CLOTH_DRYER:
				System.out.println("Enter model name");
				String modelName = input.next();
				System.out.println("Enter repair plan cost");
				double repairPlanCost = input.nextFloat();
				System.out.println("Enter brand name");
				String brandName = input.next();
				result = company.addClothDryerModel(modelName, brandName, repairPlanCost);
				break;
			case CLOTH_WASHER:
				break;
			case FURNACE:
				break;
			case KITCHEN_RANGE:
				break;
			case REFRIGERATOR:
				break;
			}

		} while (yesOrNo("Add more models?"));
		input.close();
	}

//	String modelName = getToken("Enter model name");
//	String type = getToken("Enter type of appliance");
//	if (type.equalsIgnoreCase("washer") || type.equalsIgnoreCase("dryer")) {
//		System.out.println("Enter repair plan cost");
//		double repairPlanCost = input.nextFloat();
//		result = company.addWasherDryerModel(modelName, type, repairPlanCost);
//	} else if (type.equalsIgnoreCase("refrigerator")) {
//		System.out.println("Enter capacity");
//		int capacity = input.nextInt();
//		result = company.addRefrigeratorModel(modelName, type, capacity);
//	} else if (type.equalsIgnoreCase("furnace")) {
//		System.out.println("Enter heat output");
//		int heatOutput = input.nextInt();
//		result = company.addFurnaceModel(modelName, type, heatOutput);
//	} else {
//		result = company.addModel(modelName, type);
//	}
//	if (result != null) {
//		System.out.println(result);
//	} else {
//		System.out.println("Model could not be added");
//	}

	/**
	 * Method to be called for adding a customer. Prompts the user for the
	 * appropriate values and uses the appropriate Company method for adding the
	 * customer.
	 * 
	 */
	public void addCustomer() {
		String name = getToken("Enter customer name");
		String phone = getToken("Enter phone");
		Customer result;
		result = company.addCustomer(name, phone);
		if (result == null) {
			System.out.println("Could not add customer");
		}
		System.out.println(result);
	}

	/**
	 * Method called for adding an appliance to the inventory. Prompts the user for
	 * the appropiate values and uses the appropiate Company method for adding to
	 * inventory.
	 */
	public void addToInventory() {
		Inventory result;
		Scanner input = new Scanner(System.in);
		do {
			String id = getToken("Enter appliance id");
			System.out.println("Enter quantity");
			int quantity = input.nextInt();
			result = company.addAppliance(id, quantity);
			if (result != null) {
				System.out.println(result);
			} else {
				System.out.println("Appliance could not be added to inventory");
			}
		} while (yesOrNo("Add more models?"));
		input.close();
	}

	/**
	 * Method called for processing purchases. Prompts the user for the customer id
	 * and appliance id. It uses the appropiate company methods to process a
	 * purchase.
	 */
	public void purchase() {
		Purchase result = null;
		Scanner input = new Scanner(System.in);
		String customerId = getToken("Enter customer id");
		Customer customer = company.getCustomer(customerId);
		String applianceId = getToken("Enter appliance id");
		Appliance appliance = company.getAppliance(applianceId);
		System.out.println("Enter quantity");
		int quantity = input.nextInt();
		boolean inStock = company.buyAppliance(appliance, quantity);
		if (inStock == true) {
			result = company.addPurchase(customer, appliance, quantity);
		} else if (inStock == false) {
			company.createBackOrder(customer, appliance, quantity);
		}
		if (result != null) {
			System.out.println(result);
		} else {
			System.out.println("Appliance is out of stock");
		}
		input.close();
	}

	/**
	 * Method used to enroll a customer in a repair plan it prompts the user for the
	 * appropiate values and uses the appropiate Company method to enroll the
	 * customer in a repair plan.
	 */
	public void enrollRepairPlan() {
		RepairPlan result;
		String customerId = getToken("Enter customer id");
		String applianceId = getToken("Enter appliance id");
		result = company.enrollRepairPlan(customerId, applianceId);
		if (result != null) {
			System.out.println(result);
		} else {
			System.out.println("Appliance is not eligible for a repair plan");
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
		}
	}

	/**
	 * Method used to print all sales revenue and repair revenue. It uses the
	 * appropiate Company methods to get the repair revenue and get the sales
	 * revenue.
	 */
	public void printRevenue() {
		double salesRevenue = company.getSalesRevenue();
		double repairRevenue = company.getSalesRevenue();
		System.out.println("Sales revenue: " + salesRevenue);
		System.out.println("Repair revenue: " + repairRevenue);
	}

	/**
	 * Method used to list all appliances or to list all the appliances of a
	 * specific type. It uses the appropiate Company methods to get the appliances.
	 */
	public void getAppliances() {
		ApplianceList result;
		String applianceType = getToken("Enter appliance type");
		if (applianceType.equalsIgnoreCase("all")) {
			result = company.getAppliances();
		} else {
			result = company.getAppliancesType(applianceType);
		}
		if (result != null) {
			System.out.println(result);
		} else {
			System.out.println("No appliances found");
		}
	}

	/**
	 * Method used to list all user repair plans. It uses the appropiate Company
	 * method to get all users in repair plans.
	 */
	public void getUsersInRepairPlans() {
		RepairPlanList result;
		result = company.getUsersInRepairPlans();
		if (result != null) {
			System.out.println(result);
		} else {
			System.out.println("No users in repair plans found");
		}
	}

	/**
	 * Method used to list all the customers. It uses the appropiate Company method
	 * to get all the customers.
	 */
	public void getCustomers() {
		CustomerList result;
		result = company.getCustomers();
		if (result != null) {
			System.out.println(result);
		} else {
			System.out.println("No customers found");
		}

	}

	/**
	 * Method used to list all the back orders. It uses the appropiate Company
	 * method to get all the backorders.
	 */
	public void getBackorders() {
		BackOrderList result;
		result = company.getBackOrders();
		if (result != null) {
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
			case ADD_TO_INVETORY:
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