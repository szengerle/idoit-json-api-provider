package de.szengerle.idoit.api.client.rpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Config {
	
	private static String url;
	private static String apikey;
	
	public static void setUrl(String url) {
		Config.url = url;
	}

	public static void setApikey(String apikey) {
		Config.apikey = apikey;
	}

	public static URL getUrl() {
		
		enableUntrustedConnection();
		
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getApiKey() {
		return apikey;
	}
	
	private static void enableUntrustedConnection() {
		try {
			/*
			 *  fix for
			 *    Exception in thread "main" javax.net.ssl.SSLHandshakeException:
			 *       sun.security.validator.ValidatorException:
			 *           PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
			 *               unable to find valid certification path to requested target
			 */
			TrustManager[] trustAllCerts = new TrustManager[] {
			   new X509TrustManager() {
			      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			        return null;
			      }

			      public void checkClientTrusted(X509Certificate[] certs, String authType) {  }

			      public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

			   }
			};

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
			    public boolean verify(String hostname, SSLSession session) {
			      return true;
			    }
			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			/*
			 * end of the fix
			 */
		} catch (KeyManagementException e) {
			
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
	}

}
