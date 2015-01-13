package de.szengerle.idoit.api.client.rpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.ImmutableMap;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

import de.szengerle.idoit.api.client.generic.GenericCategoryList;
import de.szengerle.idoit.api.client.generic.GenericObject;

public class GenericFactory {

	private final static Logger log = Logger.getLogger(GenericFactory.class);
	static ObjectMapper mapper = new ObjectMapper();
	static JsonRpcHttpClient client = new JsonRpcHttpClient(Config.getUrl());
	
	public static ArrayList<GenericObject> getGenericObjectsByTitle(String title, int type) {

		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("title", title + "%");
		if(type != 0) filter.put("type", String.valueOf(type));
		try {
			final CollectionType RETURN_CLASS = mapper.getTypeFactory().constructCollectionType(List.class, GenericObject.class);
			
			@SuppressWarnings("unchecked")
			ArrayList<GenericObject> o_arr = (ArrayList<GenericObject>) client.invoke("cmdb.objects",	ImmutableMap.of("apikey", Config.getApiKey(), "filter", filter), RETURN_CLASS);
			
			//cmdb.objectS returns an array with objects; check if only one and extract it
			
			if(o_arr.size() == 0) {
				throw new RuntimeException("Object by Title not found!");
			}
			
			for(GenericObject obj : o_arr) {
				obj = getGenericObjectById(obj.getId());
				GenericCategoryList ol = client.invoke(
						"cmdb.object_type_categories",
						ImmutableMap.of("apikey", Config.getApiKey(), "type",obj.getObjecttype()), GenericCategoryList.class);
				log.info("Category List of Object loaded. count(catg)="
						+ ol.getCatg().size());
				obj.setCat_list(ol);
			}
			return o_arr;
		} catch (JsonRpcClientException e) {
			// handle idoit error
			log.error("idoit: " + e.getMessage() + e.getData());
			throw new RuntimeException("idoit: " + e.getMessage() + e.getData());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static GenericObject getGenericObjectByTitle(String title, int type) {
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("title", title + "%");
		if(type != 0) filter.put("type", String.valueOf(type));
		try {
			final CollectionType RETURN_CLASS = mapper.getTypeFactory().constructCollectionType(List.class, GenericObject.class);
			
			@SuppressWarnings("unchecked")
			ArrayList<GenericObject> o_arr = (ArrayList<GenericObject>) client.invoke("cmdb.objects",	ImmutableMap.of("apikey", Config.getApiKey(), "filter", filter), RETURN_CLASS);
			
			//cmdb.objectS returns an array with objects; check if only one and extract it
			
			if(o_arr.size() == 0) {
				throw new RuntimeException("Object by Title not found!");
			}
			
			if(o_arr.size() > 1) {
				throw new RuntimeException("Object by Title multiple found!");
			}
			
			GenericObject o = getGenericObjectById(o_arr.get(0).getId());
			
			//category loader for later use
			
			GenericCategoryList ol = client.invoke(
					"cmdb.object_type_categories",
					ImmutableMap.of("apikey", Config.getApiKey(), "type",o.getObjecttype()), GenericCategoryList.class);
			log.info("Category List of Object loaded. count(catg)="
					+ ol.getCatg().size());
			o.setCat_list(ol);
			
			return o;
		} catch (JsonRpcClientException e) {

			// handle idoit error
			log.error("idoit: " + e.getMessage() + e.getData());
			throw new RuntimeException("idoit: " + e.getMessage() + e.getData());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public static GenericObject getGenericObjectByTitle(String title) {
		return getGenericObjectByTitle(title, 0);
	}

	public static GenericObject getGenericObjectById(int id) {

		try {
			log.info("load GenericObject by GenericFactory");
			GenericObject o = client.invoke("cmdb.object",
					ImmutableMap.of("apikey", Config.getApiKey(), "id", id),
					GenericObject.class);
			log.info("GenericObject " + o.getTitle() + " + (" + o.getId()
					+ ")loaded");
			GenericCategoryList ol = client.invoke(
					"cmdb.object_type_categories",
					ImmutableMap.of("apikey", Config.getApiKey(), "type",
							o.getObjecttype()), GenericCategoryList.class);
			log.info("Category List of Object loaded. count(catg)="
					+ ol.getCatg().size());
			o.setCat_list(ol);
			return o;
		} catch (JsonRpcClientException e) {

			// handle idoit error
			log.error("idoit: " + e.getMessage() + e.getData());
			throw new RuntimeException("idoit: " + e.getMessage() + e.getData());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return null;
	}

}
