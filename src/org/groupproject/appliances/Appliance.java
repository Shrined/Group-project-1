package org.groupproject.appliances;

import java.io.Serializable;

import org.groupproject.application.IdServer;

public abstract class Appliance implements Serializable {
	private static final long serialVersionUID = -3102951998712591043L;
	private String brandName;
	private String id;
	private static final String APPLIANCE_STRING = "A";

	public Appliance(String brandName) {
		this.brandName = brandName;
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
	 * Getter for appliance ID
	 * 
	 * @return appliance ID
	 */
	public String getId() {
		return id;
	}

}
