package de.szengerle.idoit.api.client.dialog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DialogObject {
	
	int id;
	String title;
	String iconst;
	String title_lang;
	
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
	public String getTitle_lang() {
		return title_lang;
	}
	public void setTitle_lang(String title_lang) {
		this.title_lang = title_lang;
	}
	
	@Override
	public String toString() {
		return "DialogObject [id=" + id + ", title=" + title + ", iconst="
				+ iconst + ", title_lang=" + title_lang + "]";
	}
	
	

}
