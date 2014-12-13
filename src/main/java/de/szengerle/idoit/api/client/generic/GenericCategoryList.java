package de.szengerle.idoit.api.client.generic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GenericCategoryList {

	private final static Logger log = Logger.getLogger(GenericCategoryList.class);

	List<GenericCategory> catg;
	List<GenericCategory> cats;

	List<GenericCategory> custom; // only idoit pro used this for customer
									// defined categoies

	public List<GenericCategory> getCatg() {
		return catg;
	}

	public void setCatg(List<GenericCategory> catg) {
		this.catg = catg;
	}

	public List<GenericCategory> getAllGenericCategories() {
		// log.info("getAllgenericCategories()");
		ArrayList<GenericCategory> l = new ArrayList<GenericCategory>();
		if (catg != null)
			l.addAll(catg);
		if (cats != null)
			l.addAll(cats);
		if (custom != null)
			l.addAll(custom);
		// log.info("getAllgenericCategories() end");
		if (!l.isEmpty()) {
			
			/*
			boolean f = false;
			for(GenericCategory c : catg) {
				if(c.getIconst().equals("C__CATG__RELATION")) f = true;
			}
			
			if(f==false) {		
				GenericCategory gc_n = new GenericCategory();
				gc_n.setConst("C__CATG__RELATION");
				gc_n.setIconst("C__CATG__RELATION");
				gc_n.setMulti_value(1);
				gc_n.setId(82);
				gc_n.setTitle("C__CATG__RELATION");
				
				catg.add(gc_n);
				log.warn("No relation category found! added automaticaly");
				
			}
			
			*/
		
			return l;
		}
		{
			log.warn("No categories loaded!");
			throw new RuntimeException("No categories loaded!");
		}
	}

	public GenericCategory getCatByConst(String iconst) {

		log.info("getCatByConst(...)");

		for (GenericCategory gc : getAllGenericCategories()) {
			if (gc.getIconst().equals(iconst)) {
				log.info("getCatByConst(...): found; return()");
				return gc;
			}
		}

		throw new IllegalArgumentException("Category " + iconst + " not found!");
	}

	public List<GenericCategory> getCats() {
		return cats;
	}

	public void setCats(List<GenericCategory> cats) {
		this.cats = cats;
	}

	public List<GenericCategory> getCustom() {
		return custom;
	}

	public void setCustom(List<GenericCategory> custom) {
		this.custom = custom;
	}

	public static Logger getLog() {
		return log;
	}

	@Override
	public String toString() {
		return "GenericCategoryList [catg=" + catg + ", cats=" + cats
				+ ", getCatg()=" + getCatg() + ", getCats()=" + getCats()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
