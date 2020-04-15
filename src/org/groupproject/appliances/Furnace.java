package org.groupproject.appliances;

import java.io.Serializable;

/**
 * This class represents a furnace appliance. Furnaces have a heating output
 * field.
 *
 */
public class Furnace extends Appliance implements Serializable {
	private static final long serialVersionUID = -4627808559553144068L;
	private int heatingOutput;

	public Furnace(String brandName, String modelName, double price, int heatingOutput) {
		super(brandName, modelName, price);
		this.heatingOutput = heatingOutput;
	}

	/**
	 * Getter for furnace heating output
	 * 
	 * @return heating output in British Thermal Units (BTU)
	 */
	public int getHeatingOutput() {
		return this.heatingOutput;
	}

	@Override
	public String toString() {
		return "Furnace [Brand name: " + getBrandName() + ", Model name: " + getModelName() + ", Price: " + getPrice()
				+ ", Heating output (BTU): " + heatingOutput + ", id: " + getId() + "]";
	}
}
