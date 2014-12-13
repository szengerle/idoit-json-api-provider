package de.szengerle.idoit.api.client.category;

import de.szengerle.idoit.api.client.generic.GenericObject;

public class C__CATS__ORGANIZATION_PERSONS extends Category {
	
	GenericObject object;
	Object contact;
	
	public GenericObject getObject() {
		return object;
	}
	public void setObject(GenericObject object) {
		this.object = object;
	}
	public Object getContact() {
		return contact;
	}
	public void setContact(Object contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "C__CATS__ORGANIZATION_PERSONS [object=" + object + ", contact="
				+ contact + "]";
	}
	
	
	
	
	
	/*int object;
	int contact;

	public int getObject() {
		return object;
	}


	public void setObject(int object) {
		this.object = object;
	}


	public int getContact() {
		return contact;
	}


	public void setContact(int contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
		return "C__CATS__ORGANIZATION_PERSONS [object=" + object + ", contact="
				+ contact + "]";
	}*/
	

}
