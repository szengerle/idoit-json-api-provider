package de.szengerle.idoit.api.client.category;

import de.szengerle.idoit.api.client.generic.GenericCategory;

public class Category {
	
	/**
	 * Add new categories:
	 * 	- create new class inherited from category (use the internal const of the class!)
	 * 	- add all attributes (src: api doc) to class
	 * 	- create getter, setter for jackson & jsonrpc4j
	 * 	- add a toString() method for testing purpose
	 * 	- add a mapping of the const name to category-class in GenericCategoryMapper
	 */
	
	int id;
	int objID;
	
	GenericCategory gc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getObjID() {
		return objID;
	}
	public void setObjID(int objID) {
		this.objID = objID;
	}
	public GenericCategory getGc() {
		return gc;
	}
	public void setGc(GenericCategory gc) {
		this.gc = gc;
	}

}
