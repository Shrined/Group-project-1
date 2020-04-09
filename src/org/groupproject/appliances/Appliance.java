package org.groupproject.appliances;

import java.io.Serializable;

import org.groupproject.application.IdServer;

public abstract class Appliance implements Serializable {
	private static final long serialVersionUID = -3102951998712591043L;
	private String brandName;
	private String modelName;
	private String id;
	private static final String APPLIANCE_STRING = "A";

	public Appliance(String brandName, String modelName) {
		this.brandName = brandName;
		this.modelName = modelName;
		this.id = APPLIANCE_STRING + IdServer.instance().getApplianceId();
	}

	/**
	 * Getter for appliance brand name
	 * 
	 * @return appliance brand name
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * Getter for appliance model name
	 * 
	 * @return appliance model name
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * Getter for appliance ID
	 * 
	 * @return appliance ID
	 */
	public String getId() {
		return id;
	}

}
