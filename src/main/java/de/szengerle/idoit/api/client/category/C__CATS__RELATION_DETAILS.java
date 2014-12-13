package de.szengerle.idoit.api.client.category;

import de.szengerle.idoit.api.client.dialog.DialogObject;
import de.szengerle.idoit.api.client.generic.GenericObject;

public class C__CATS__RELATION_DETAILS extends Category {
	
	GenericObject object1;
	GenericObject object2;
	DialogObject relation_type;
	DialogObject weighting;
	Object itservice;
	String description;
	
	public GenericObject getObject1() {
		return object1;
	}
	public void setObject1(GenericObject object1) {
		this.object1 = object1;
	}
	public GenericObject getObject2() {
		return object2;
	}
	public void setObject2(GenericObject object2) {
		this.object2 = object2;
	}
	public DialogObject getRelation_type() {
		return relation_type;
	}
	public void setRelation_type(DialogObject relation_type) {
		this.relation_type = relation_type;
	}
	public DialogObject getWeighting() {
		return weighting;
	}
	public void setWeighting(DialogObject weighting) {
		this.weighting = weighting;
	}
	public Object getItservice() {
		return itservice;
	}
	public void setItservice(Object itservice) {
		this.itservice = itservice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "C__CATG__RELATION [object1=" + object1 + ", object2=" + object2
				+ ", relation_type=" + relation_type + ", weighting="
				+ weighting + ", itservice=" + itservice + ", description="
				+ description + "]";
	}
	
	
	

}
