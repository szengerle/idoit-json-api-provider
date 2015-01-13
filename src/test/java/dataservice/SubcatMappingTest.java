package dataservice;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

import de.szengerle.idoit.api.client.rpc.Config;

@SuppressWarnings( {"unused"})
@Deprecated
public class SubcatMappingTest {

	static JsonRpcHttpClient client = new JsonRpcHttpClient(Config.getUrl());

	@Test
	public void test() {

		final String COMMAND = "cmdb.category.read";
		final Object PARAM;

		final int objID = 59; 										// rz-ffm-a-sw-01a
		final String category = "C__CMDB__SUBCAT__NETWORK_PORT";	//idoit-const
		final int catgID = 39; 										// ports

		PARAM = ImmutableMap.of("apikey", Config.getApiKey(), "objID", objID, "category", category);
		//PARAM = ImmutableMap.of("apikey", Config.getApiKey(), "objID", objID, "catgID", catgID);

		try {
			Object response = client.invoke(COMMAND, PARAM, Object.class);
			System.out.println(response.toString());
		} catch (JsonRpcClientException e) {
			// handle idoit error
			System.out.println("idoit: " + e.getMessage() + e.getData());
			throw new RuntimeException("idoit: " + e.getMessage() + e.getData());

		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

}
