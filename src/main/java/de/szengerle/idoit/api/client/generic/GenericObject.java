package de.szengerle.idoit.api.client.generic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.szengerle.idoit.api.client.category.Category;
import de.szengerle.idoit.api.client.rpc.GenericCategoryMapper;
import de.szengerle.idoit.api.client.rpc.GenericFactory;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericObject {

	private final static Logger log = Logger.getLogger(GenericObject.class);

	String title;
	String sysid;
	int objecttype;
	String type; // used only in object references in categories
	String type_title;
	String type_icon;
	String type_group_title;
	int status;
	int cmdb_status;
	String cmdb_status_title;
	String created;
	String updated;
	String image;

	GenericCategoryList cat_list;

	int id;

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

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public int getObjecttype() {
		return objecttype;
	}

	public void setObjecttype(int objecttype) {
		this.objecttype = objecttype;
	}

	public String getType_title() {
		return type_title;
	}

	public void setType_title(String type_title) {
		this.type_title = type_title;
	}

	public String getType_icon() {
		return type_icon;
	}

	public void setType_icon(String type_icon) {
		this.type_icon = type_icon;
	}

	public String getType_group_title() {
		return type_group_title;
	}

	public void setType_group_title(String type_group_title) {
		this.type_group_title = type_group_title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCmdb_status() {
		return cmdb_status;
	}

	public void setCmdb_status(int cmdb_status) {
		this.cmdb_status = cmdb_status;
	}

	public String getCmdb_status_title() {
		return cmdb_status_title;
	}

	public void setCmdb_status_title(String cmdb_status_title) {
		this.cmdb_status_title = cmdb_status_title;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String creaed) {
		this.created = creaed;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public GenericCategoryList getCat_list() {
		return cat_list;
	}

	public void setCat_list(GenericCategoryList cat_list) {
		this.cat_list = cat_list;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * the getCategory-method with the default namespace
	 * @param gc
	 * @return
	 */
	public List<Category> getCategory(GenericCategory gc) {
		return getCategory(gc, "de.szengerle.idoit.api.client.category.");
	}
	

	/**
	 * Get Category as Raw List<Category> by GenericCategory Object; here and in the GenericCategoryMapper the reflections are done
	 * @param gc	the GenericCategory Object which represents the wanted Category
	 * @return the category as List<Category>
	 */
	public List<Category> getCategory(GenericCategory gc, String searchNamespace) {
		List<Category> cat = new ArrayList<Category>();
		for (GenericCategory gcl : this.cat_list.getAllGenericCategories()) {
			if (gcl.getIconst() == gc.getIconst()) {
				// found

				// get class (reflections) by name

				final String CAT_CLASS_PREFIX = searchNamespace;
				Class<?> loadedClass;
				try {
					loadedClass = Class.forName(CAT_CLASS_PREFIX
							+ gc.getIconst());
				} catch (ClassNotFoundException e) {
					log.error("The category " + gc.getIconst()
							+ "isn't implemented!");
					throw new IllegalArgumentException("The category "
							+ gc.getIconst() + "isn't implemented!");
				}

				// loaded class must be a inherited by Category
				if (loadedClass.getSuperclass() != null
						&& loadedClass.getSuperclass().equals(Category.class)) {
					@SuppressWarnings("unchecked")
					Class<? extends Category> catClass = (Class<? extends Category>) loadedClass;

					// map to real category class
					List<? extends Category> l = GenericCategoryMapper.mapper(
							this, catClass);
					cat.addAll(l);
					return cat;
				} else {
					log.error("The category " + gc.getIconst()
							+ "isn't inherited by Category Superclass!");
					throw new IllegalArgumentException("The category "
							+ gc.getIconst()
							+ "isn't inherited by Category Superclass!");
				}

			}
		}

		throw new IllegalArgumentException("Category " + gc.getTitle()
				+ " for " + this.getObjecttype() + "not found!");

	}
	
	
	public <E extends Category> E getCategoryByClassSingle(
			@SuppressWarnings("rawtypes") Class E) {
		return getCategoryByClassSingle(E, "de.szengerle.idoit.api.client.category.");
	}
	
	/**
	 * this is a decorator for the getCategory Method; it loads the Category by Class-Type and returns the Category as generic List<Class-Type>
	 * @param cl the wanted category/return class-type
	 * @return as specific Category-Object 
	 */
	public <E extends Category> E getCategoryByClassSingle(
			@SuppressWarnings("rawtypes") Class E, String namespace) {
		List<E> allCategories = getCategoryByClass(E, namespace);
		if(allCategories.size() == 1) {
			//only one 
			return allCategories.get(0);
		} else {
			throw new RuntimeException("requested only single category, but multiple returned!");
		}
	}
	
	public <E extends Category> List<E> getCategoryByClass(
			@SuppressWarnings("rawtypes") Class E) {
		return getCategoryByClass(E, "de.szengerle.idoit.api.client.category.");
	}
	

	/**
	 * this is a decorator for the getCategory Method; it loads the Category by Class-Type and returns the Category as generic List<Class-Type>
	 * @param cl the wanted category/return class-type
	 * @return as specific Category-Object 
	 */
	@SuppressWarnings("unchecked")
	public <E extends Category> List<E> getCategoryByClass(
			@SuppressWarnings("rawtypes") Class E, String namespace) {
		
		
		for (List<Category> c : this.getAllCategories(namespace)) {
			if (!c.isEmpty()) {
				if (c.get(0).getClass().equals(E)) {
					// class selected
					ArrayList<E> conv = new ArrayList<E>();
					for (Category cc : c) {
						conv.add((E) cc);
					}
					return conv;
				}
			}
		}

		throw new IllegalArgumentException("Category " + E.getSimpleName()
				+ " for " + this.getObjecttype() + "not found!");
	}
	
	public List<List<Category>> getAllCategories() {
		return getAllCategories("de.szengerle.idoit.api.client.category.");
	}

	/**
	 * the object know what categories it include in cat_list as GenericCategories. this method load all of them. not implemented categories are warned
	 * @return a raw list of categories-list: List<List<Category>>
	 */
	public List<List<Category>> getAllCategories(String namespace) {
		log.info("getAllCategories()");

		List<List<Category>> all = new ArrayList<List<Category>>();
		if (cat_list == null) {
			//if the object has not loaded categories (eg. as child of a parent pbject) 
			log.info("Object not complete. Load cat_list.");
			GenericObject o_with_cats = GenericFactory.getGenericObjectById(id);
			cat_list = o_with_cats.getCat_list();
		}
		log.info("load cats: size=" + cat_list.getAllGenericCategories().size());
		for (GenericCategory gc : cat_list.getAllGenericCategories()) {

			final String CAT_CLASS_PREFIX = namespace; //;

			// before adding; check if implemented
			try {
				Class.forName(CAT_CLASS_PREFIX + gc.getIconst()); // check only

				// if implemented add
				all.add(getCategory(gc, namespace));
			} catch (ClassNotFoundException e) {
				log.info("The category " + gc.getIconst()
						+ "isn't implemented and was ignored!");

			}

		}

		return all;
	}

	/**
	 * for testing: all categories are returned as string by using the toString()-method of all childs recursivly
	 * @return
	 */
	public String allCategoriesToString() {
		String a = new String();
		for (List<Category> c : this.getAllCategories()) {
			if (!c.isEmpty()) {
				// class selected
				for (Category cc : c) {
					a += cc.toString();
					a += "\n";
				}

			}
		}
		return a;

	}

	@Override
	public String toString() {
		return "GenericObject [title=" + title + ", sysid=" + sysid
				+ ", objecttype=" + objecttype + ", type=" + type
				+ ", type_title=" + type_title + ", type_icon=" + type_icon
				+ ", type_group_title=" + type_group_title + ", status="
				+ status + ", cmdb_status=" + cmdb_status
				+ ", cmdb_status_title=" + cmdb_status_title + ", created="
				+ created + ", updated=" + updated + ", image=" + image
				+ ", cat_list=" + cat_list + ", id=" + id + "]";
	}

}
