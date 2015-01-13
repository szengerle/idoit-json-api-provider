package dataservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.szengerle.idoit.api.client.category.C__CMDB__SUBCAT__NETWORK_PORT;
import de.szengerle.idoit.api.client.generic.GenericObject;
import de.szengerle.idoit.api.client.rpc.CategoryUpdate;
import de.szengerle.idoit.api.client.rpc.GenericFactory;

public class NetworkPort {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		importList();
	}

	String csvFilename = "C:\\Users\\sezenger\\Desktop\\sw_vlan4.csv";

	public void importList() {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(
					csvFilename));

			String[] row = null;

			while ((row = reader.readLine().split(";")) != null) {

				if (row.length == 3) { // vlan import 3 rows
					String name = row[0];
					String anschluss = row[1];
					if (row[2] != null && row[2] != "") {
						String[] vlans = row[2].split(",");

						GenericObject o = GenericFactory.getGenericObjectByTitle(name, 6);

						List<C__CMDB__SUBCAT__NETWORK_PORT> ports = o
								.getCategoryByClass(C__CMDB__SUBCAT__NETWORK_PORT.class);
						for (C__CMDB__SUBCAT__NETWORK_PORT p : ports) {
							if (p.getTitle().equals(anschluss)) {
								for (String s : vlans) {
									GenericObject l2_net = GenericFactory.getGenericObjectByTitle("VLAN" + s + "_");
									p.addLayer2_assignment(l2_net.getId());
								}
								CategoryUpdate.updateCategory(p);
							}

							
						}
						System.out.println(o.getTitle().toString());

					}

				}

			}

			reader.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
}
