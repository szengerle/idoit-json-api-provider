package de.szengerle.idoit.api.client.category;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class C__CMDB__SUBCAT__NETWORK_PORT extends Category {
	
	String title;


	ArrayList<Integer> layer2_assignment = new ArrayList<Integer>();	//objtype=LAYER2_NET -> objID of vlans
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	//only id's
	@JsonProperty("layer2_assignment")
	public void setLayer2_assignment(ArrayList<HashMap<String, String>> l) {
		if(l == null) return;
		for(HashMap<String, String> m : l) {
			try {
				int id = Integer.parseInt(m.get("id"));
				layer2_assignment.add(id);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Integer expected!");
			}
			
		}
	}
	
	public void addLayer2_assignment(int l2) {
		layer2_assignment.add(l2);
	}
	
	public ArrayList<Integer> getLayer2_assignment() {
		return layer2_assignment;
	}
	
	
	/*	as child POJO
	public void setLayer2_assignment(Object layer2_assignment) {
		this.layer2_assignment = layer2_assignment;
	}*/
	
	
	@Override
	public String toString() {
		return "C__CMDB__SUBCAT__NETWORK_PORT [title=" + title
				+ ", layer2_assignment=" + layer2_assignment + "]";
	}
	
	
	
	
	

}
