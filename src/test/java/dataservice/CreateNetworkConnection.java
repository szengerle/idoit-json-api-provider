package dataservice;

import org.junit.Before;
import org.junit.Test;

import de.szengerle.idoit.api.client.category.C__CATG__RELATION;
import de.szengerle.idoit.api.client.generic.GenericObject;
import de.szengerle.idoit.api.client.rpc.CategoryUpdate;
import de.szengerle.idoit.api.client.rpc.GenericFactory;

@SuppressWarnings( {"unused"})
@Deprecated
public class CreateNetworkConnection {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		int DeviceA = 53;
		String PortA = "GigabitEthernet 1/0/24";
		int DeviceB = 55;
		String PortB = "GigabitEthernet 1/0/24";
		
		String RelTitle = DeviceA + " liefert Netzwerk an " + DeviceB;
		String RelType = "C__OBJTYPE__RELATION";
		
		
		GenericObject objA = GenericFactory.getGenericObjectById(DeviceA);
		GenericObject objB = GenericFactory.getGenericObjectById(DeviceB);
		
		/*
		//1st: 	Create Relationship
		int relId = 819; //ObjectUpdate.createObject(RelType, RelTitle);
		GenericObject relObj = GenericFactory.getGenericObjectById(relId);
		C__CATG__RELATION relCat = (C__CATG__RELATION) relObj.getCategoryByClass(C__CATG__RELATION.class).get(0);
		relCat.setObject1(objA);
		relCat.setObject2(objB);
		
		CategoryUpdate.updateCategory(relCat);
		*/
		
		
		//2nd:	Create Connection from Master
		
		//3rd:	Create Connection from Slave
		
		
	}

}
