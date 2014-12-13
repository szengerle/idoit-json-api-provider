package de.szengerle.idoit.api.client.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class C__CATS__ORGANIZATION_MASTER_DATA extends Category {
	
	String title;
	String telephone;
	String fax;
	String website;
	//int headquarter; //reference to isys_connection__id
	String description;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	/*
	public int getHeadquarter() {
		return headquarter;
	}
	public void setHeadquarter(int headquarter) {
		this.headquarter = headquarter;
	}
	*/
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "C__CATS__ORGANIZATION_MASTER_DATA [title=" + title
				+ ", telephone=" + telephone + ", fax=" + fax + ", website="
				+ website + ", description=" + description + ", id=" + id
				+ ", objID=" + objID + ", getTitle()=" + getTitle()
				+ ", getTelephone()=" + getTelephone() + ", getFax()="
				+ getFax() + ", getWebsite()=" + getWebsite()
				+ ", getDescription()=" + getDescription() + ", getId()="
				+ getId() + ", getObjID()=" + getObjID() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}
