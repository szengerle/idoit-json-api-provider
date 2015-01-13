package dataservice;

import org.junit.Before;
import org.junit.Test;

import de.szengerle.idoit.api.client.generic.GenericObject;
import de.szengerle.idoit.api.client.rpc.GenericFactory;

public class CreateObject {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		//int id = ObjectUpdate.createObject("C__OBJTYPE__SERVER","srv01");
		//assertNotEquals(id, -1);
		
		GenericObject go = GenericFactory.getGenericObjectById(819);
		System.out.println(go.getAllCategories().toString());
		
		//int deleteTest = ObjectUpdate.deleteObject(32);
		//assertEquals(deleteTest, 0);
		
	}

}
