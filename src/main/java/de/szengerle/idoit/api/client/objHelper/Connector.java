package de.szengerle.idoit.api.client.objHelper;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.szengerle.idoit.api.client.category.C__CMDB__SUBCAT__NETWORK_PORT;
import de.szengerle.idoit.api.client.category.Category;
import de.szengerle.idoit.api.client.generic.GenericObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Connector {
	
	private final static Logger log = Logger.getLogger(Connector.class);
	
	int connector_type;
	Object con_type;
	String name;			//connector name
	int id;
	String title;			//device name
	String sysid;
	String type;
	String assigned_category;
	
	/*
	public int getConnector_type() {
		return connector_type;
	}
	public void setConnector_type(int connector_type) {
		this.connector_type = connector_type;
	}
	public Object getCon_type() {
		return con_type;
	}
	public void setCon_type(Object con_type) {
		this.con_type = con_type;
	}
	*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAssigned_category() {
		return assigned_category;
	}
	public void setAssigned_category(String assigned_category) {
		this.assigned_category = assigned_category;
	}
	*/
	
	/**
	 * search connector in object and get back the Connector-Object
	 * @param go
	 * @param connectorName
	 * @return
	 */
	public static Connector getConnectorObject(GenericObject go, String connectorName) {
		String title = go.getTitle();
		int id = go.getId();
		String type = go.getType();
		String name = connectorName;
		int connector_type = 2;
		Object con_type = null;
		String assigned_category = "C__CMDB__SUBCAT__NETWORK_PORT";
		
		//check if exist
		for(List<Category> c : go.getAllCategories()) {
			if(!c.isEmpty()) {
				if(c.get(0).getClass().equals(C__CMDB__SUBCAT__NETWORK_PORT.class)) {
					//class selected
					
					for(Category p : c) {
						C__CMDB__SUBCAT__NETWORK_PORT port = (C__CMDB__SUBCAT__NETWORK_PORT)p;
						if(port.getTitle().equals(connectorName)) {
							return new Connector(id, title, name, type, connector_type, con_type, assigned_category);
						}
					}
				}
			}
		}
		
		log.error("Connector " + connectorName + " for " + go.getTitle() + "not found!");
		throw new RuntimeException("Connector " + connectorName + " for " + go.getTitle() + "not found!");
		
	}
	
	/**
	 * std-ctor
	 */
	public Connector() {
		
	}
	
	/**
	 * generate network connector
	 * @param id
	 * @param title
	 * @param name
	 * @param type
	 */
	public Connector(int id, String title, String name, String type, int connector_type, Object con_type, String assigned_category) {
		this.id = id;
		this.title = title;
		this.name = name;
		this.type = type;
		this.connector_type = connector_type;
		this.con_type = con_type;
		this.assigned_category = assigned_category;
	}
	
	@Override
	public String toString() {
		return "Connector [connector_type=" + connector_type + ", con_type="
				+ con_type + ", name=" + name + ", id=" + id + ", title="
				+ title + ", sysid=" + sysid + ", type=" + type
				+ ", assigned_category=" + assigned_category + "]";
	}
	
	

}
