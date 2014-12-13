package de.szengerle.idoit.api.client.rpc;

import org.apache.log4j.Logger;

import com.google.common.collect.ImmutableMap;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

import de.szengerle.idoit.api.client.category.Category;

public class CategoryUpdate {

	private final static Logger log = Logger.getLogger(CategoryUpdate.class);

	static JsonRpcHttpClient client = new JsonRpcHttpClient(Config.getUrl());

	public static boolean updateCategory(Category c) {
		try {

			final Object PARAM;
			
			PARAM = ImmutableMap.of("apikey", Config.getApiKey(),
					"category", c.getGc().getIconst(), "objID",
					c.getObjID(), "data", c);

			/* bug in 1.4.8 pro fixed 
			if (!c.getGc().getIconst().contains("SUBCAT")) {
				PARAM = ImmutableMap.of("apikey", Config.getApiKey(),
						"category", c.getGc().getIconst(), "objID",
						c.getObjID(), "data", c);
			} else {
				PARAM = ImmutableMap.of("apikey", Config.getApiKey(), "catgID",
						c.getGc().getId(), "objID", c.getObjID(), "data", c);
			}*/

			Object o = client.invoke("cmdb.category.update", PARAM,
					Object.class);
			log.info("Category update response:" + o.toString());
			return true;
		} catch (JsonRpcClientException e) {
			// handle idoit error
			log.error("idoit: " + e.getMessage() + e.getData());
			throw new RuntimeException("idoit: " + e.getMessage() + e.getData());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean createCategory(Category c) {
		try {

			final Object PARAM;
			
			PARAM = ImmutableMap.of("apikey", Config.getApiKey(),
					"category", c.getGc().getIconst(), "objID",
					c.getObjID(), "data", c);

			/* bug in 1.4.8 pro fixed 
			if (!c.getGc().getIconst().contains("SUBCAT")) {
				PARAM = ImmutableMap.of("apikey", Config.getApiKey(),
						"category", c.getGc().getIconst(), "objID",
						c.getObjID(), "data", c);
			} else {
				PARAM = ImmutableMap.of("apikey", Config.getApiKey(), "catgID",
						c.getGc().getId(), "objID", c.getObjID(), "data", c);
			}*/

			Object o = client.invoke("cmdb.category.create", PARAM,
					Object.class);
			log.info("Category create response:" + o.toString());
			return true;
		} catch (JsonRpcClientException e) {
			// handle idoit error
			log.error("idoit: " + e.getMessage() + e.getData());
			throw new RuntimeException("idoit: " + e.getMessage() + e.getData());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return false;

	}
	
	public static boolean deleteCategory(Category c) {
		try {

			final Object PARAM;
			
			PARAM = ImmutableMap.of("apikey", Config.getApiKey(),
					"category", c.getGc().getIconst(), "objID",
					c.getObjID(), "cateID", c.getId());

			Object o = client.invoke("cmdb.category.delete", PARAM,
					Object.class);
			log.info("Category create response:" + o.toString());
			return true;
		} catch (JsonRpcClientException e) {
			// handle idoit error
			log.error("idoit: " + e.getMessage() + e.getData());
			throw new RuntimeException("idoit: " + e.getMessage() + e.getData());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return false;

	}


}
