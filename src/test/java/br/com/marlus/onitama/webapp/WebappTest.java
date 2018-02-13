package br.com.marlus.onitama.webapp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class WebappTest {
	
	private static String INDEX_URL = "http://localhost:8080/";

	@Test
	public void test() {
		
		Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(INDEX_URL);
        String response = webTarget.request().get(String.class);
        System.out.println(response);
        assertThat(response, is("Test"));
	}

}
