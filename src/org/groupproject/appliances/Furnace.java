package org.groupproject.appliances;

import java.io.Serializable;

public class Furnace extends Appliance implements Serializable {
	private static final long serialVersionUID = -4627808559553144068L;
	private int heatingOutput;

	public Furnace(String brandName, int heatingOutput) {
		super(brandName);
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

}
