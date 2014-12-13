package de.szengerle.idoit.api.client.rpc;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.ImmutableMap;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

import de.szengerle.idoit.api.client.category.Category;
import de.szengerle.idoit.api.client.generic.GenericCategory;
import de.szengerle.idoit.api.client.generic.GenericObject;

public class GenericCategoryMapper {
	
	static JsonRpcHttpClient client = new JsonRpcHttpClient(Config.getUrl());
	static ObjectMapper mapper = new ObjectMapper();
	
	private final static Logger log = Logger.getLogger(GenericCategoryMapper.class);
	
	public static <E extends Category> List<E> mapper(GenericObject go, Class<?> CatClass) {
		
		String category_name = CatClass.getSimpleName();
		
		log.info("Object mapper startet for category:" + category_name);
		
		GenericCategory gc = go.getCat_list().getCatByConst(category_name);
		
		final String COMMAND = "cmdb.category.read";
		
		final Object PARAM;
		
		PARAM = ImmutableMap.of("apikey", Config.getApiKey(), "objID", go.getId(), "category", gc.getIconst());
		
		//bug in 1.4.8 pro: changed with 1.4.9
		/*
		if(!gc.getIconst().contains("SUBCAT")) {
			//no subcat map the name
			PARAM = ImmutableMap.of("apikey", Config.getApiKey(), "objID", go.getId(), "category", gc.getIconst());
		} else {
			log.info("Subcat selected: " + gc.getId() + " " + gc.getSource_table() + " " + gc.getTitle() + " " + gc.getParent() + gc.getConst());
			PARAM = ImmutableMap.of("apikey", Config.getApiKey(), "objID", go.getId(), "catgID", gc.getId());
		}
		*/
		
		
		final CollectionType RETURN_CLASS = mapper.getTypeFactory().constructCollectionType(List.class, CatClass);		//List<E>
		
		try {

			@SuppressWarnings("unchecked")
			List<E> d = (List<E>)client.invoke(COMMAND, PARAM, RETURN_CLASS); 
			for(Category c : d) c.setGc(gc);
			log.info("Object mapper done for " + d.size() + " categories.");
			return d;
		} catch (JsonRpcClientException e) {
			
			//handle idoit error
			log.error("idoit: " + e.getMessage() + e.getData());
			throw new RuntimeException("idoit: " + e.getMessage() + e.getData());
			
		} catch (Throwable e) {

			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/* used in older versions without reflections 
	@Deprecated
	public static List<C__CATS__ORGANIZATION_MASTER_DATA> getC__CATS__ORGANIZATION_MASTER_DATA(GenericObject go) {
		
		GenericCategory gc = go.getCat_list().getCatByConst("C__CATS__ORGANIZATION_MASTER_DATA");
		
		try {
			@SuppressWarnings("unchecked")
			List<C__CATS__ORGANIZATION_MASTER_DATA> d = (List<C__CATS__ORGANIZATION_MASTER_DATA>) client.invoke("cmdb.category.read", ImmutableMap.of("apikey", Config.getApiKey(), "objID", go.getId(), "category", gc.getIconst()), mapper.getTypeFactory().constructCollectionType(List.class, C__CATS__ORGANIZATION_MASTER_DATA.class));
			for(Category c : d) {
				c.setGc(gc);
			}
			return d;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Deprecated
	public static List<C__CATS__ORGANIZATION_PERSONS> getC__CATS__ORGANIZATION_PERSONS(GenericObject go) {
			
			GenericCategory gc = go.getCat_list().getCatByConst("C__CATS__ORGANIZATION_PERSONS");
			
			try {
				@SuppressWarnings("unchecked")
				List<C__CATS__ORGANIZATION_PERSONS> d = (List<C__CATS__ORGANIZATION_PERSONS>) client.invoke("cmdb.category.read", ImmutableMap.of("apikey", Config.getApiKey(), "objID", go.getId(), "category", gc.getIconst()), mapper.getTypeFactory().constructCollectionType(List.class, C__CATS__ORGANIZATION_PERSONS.class));
				for(Category c : d) {
					c.setGc(gc);
				}
				return d;
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			return null;
	}
	*/
		


}
