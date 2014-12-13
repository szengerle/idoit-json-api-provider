package de.szengerle.idoit.api.client.rpc;

import org.apache.log4j.Logger;

import com.google.common.collect.ImmutableMap;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class ObjectUpdate {
	
	private final static Logger log = Logger.getLogger(ObjectUpdate.class);
	
	static JsonRpcHttpClient client = new JsonRpcHttpClient(Config.getUrl());
	
	public static int createObject(String type, String title) {
		try {
			Response r = client.invoke("cmdb.object.create", ImmutableMap.of("apikey", Config.getApiKey(), "type", type, "title", title), Response.class);
			if(r.isSuccess()) {
				log.info("Create successfull. id=" + r.getId());
				return r.getId();
			} else {
				return -1;
			}
		} catch (Throwable e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return -1;
	}
	
	
	public static int deleteObject(int id) {
		throw new RuntimeException("not implemented!");	//error in method.
		/*
		try {
			Object o = client.invoke("cmdb.object.delete", ImmutableMap.of("apikey", Config.getApiKey(), "id", id), Object.class);
		} catch (Throwable e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
		*/
	}
	
	
}

