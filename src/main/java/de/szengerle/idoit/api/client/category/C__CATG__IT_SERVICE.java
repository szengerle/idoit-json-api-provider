package de.szengerle.idoit.api.client.category;

import de.szengerle.idoit.api.client.generic.GenericCategory;
import de.szengerle.idoit.api.client.generic.GenericObject;

public class C__CATG__IT_SERVICE extends Category {
	
	int connected_object;
	String sysid;
	
	public int getConnected_object() {
		return connected_object;
	}
	public void setConnected_object(GenericObject connected_object) {
		this.connected_object = connected_object.getId();
	}
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	
	public C__CATG__IT_SERVICE() {
	
	}
	
	public C__CATG__IT_SERVICE(int connected_object, String sysid, int objID, GenericCategory gc) {
		
		this.setObjID(objID);
		this.setGc(gc);
		
		this.connected_object = connected_object;
		this.sysid = sysid;
	}
	
	@Override
	public String toString() {
		return "C__CATG__IT_SERVICE [connected_object=" + connected_object
				+ ", sysid=" + sysid + "]";
	}
	
	

}
