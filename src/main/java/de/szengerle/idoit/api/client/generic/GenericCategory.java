package de.szengerle.idoit.api.client.generic;

public class GenericCategory {
	
	int id;
	String title;
	String iconst;
	int multi_value;
	String source_table;
	Object parent;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getConst() {
		return iconst;
	}
	public void setConst(String iconst) {
		this.iconst = iconst;
	}
	public int getMulti_value() {
		return multi_value;
	}
	public void setMulti_value(int multi_value) {
		this.multi_value = multi_value;
	}
	public String getSource_table() {
		return source_table;
	}
	public void setSource_table(String source_table) {
		this.source_table = source_table;
	}
	public String getIconst() {
		return iconst;
	}
	public void setIconst(String iconst) {
		this.iconst = iconst;
	}
	public Object getParent() {
		return parent;
	}
	public void setParent(Object parent) {
		this.parent = parent;
	}
	
	public GenericCategory() {
	
	}
	
	public GenericCategory(String iconst, String title) {
		this.iconst = iconst;
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "GenericCategory [id=" + id + ", title=" + title + ", iconst="
				+ iconst + ", multi_value=" + multi_value + ", source_table="
				+ source_table + ", parent=" + parent + ", getId()=" + getId()
				+ ", getTitle()=" + getTitle() + ", getConst()=" + getConst()
				+ ", getMulti_value()=" + getMulti_value()
				+ ", getSource_table()=" + getSource_table() + ", getIconst()="
				+ getIconst() + ", getParent()=" + getParent()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	

}
