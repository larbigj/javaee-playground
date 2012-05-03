package net.larbig.rs.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ws.rs.core.MediaType;

import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.BeforeClass;
import org.junit.Test;


public class LaboRestClientTest {

    private static String XML_URL = "http://localhost:8080/labo-rs/services/xml";
    private static String JSON_URL= "http://localhost:8080/labo-rs/services/json";
    
    private static final String XML_RESPONSE = "<xml><result>Hello World!</result></xml>";
    private static final String JSON_RESPONSE = "{\"result\":\"Hello World!\"}";


    @BeforeClass
    public static void beforeClass() {

    }

    /**
     * Test method which executes the runRequest method that calls the RESTful helloworld-rs web service.
     */
    @Test
    public void test() {
        assertEquals("XML Response", LaboRestClientTest.XML_RESPONSE,this.runRequest(LaboRestClientTest.XML_URL, MediaType.APPLICATION_XML_TYPE));
        assertEquals("JSON Response", LaboRestClientTest.JSON_RESPONSE,this.runRequest(LaboRestClientTest.JSON_URL, MediaType.APPLICATION_JSON_TYPE));
    }

    /**
     * The purpose of this method is to run the external REST request.
     * 
     * @param url The url of the RESTful service
     * @param mediaType The mediatype of the RESTful service
     */
    private String runRequest(String url, MediaType mediaType) {
        String result = null;

        System.out.println("===============================================");
        System.out.println("URL: " + url);
        System.out.println("MediaType: " + mediaType.toString());

        try {
            ClientRequest request = new ClientRequest(url);
            request.accept(mediaType);
            ClientResponse<String> response = request.get(String.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed request with HTTP status: " + response.getStatus());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
            System.out.println("\n*** Response from Server ***\n");
            String output = null;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                result = output;
            }
        } catch (ClientProtocolException cpe) {
            System.err.println(cpe);
        } catch (IOException ioe) {
            System.err.println(ioe);
        } catch (Exception e) {
            System.err.println(e);
        }

        System.out.println("\n===============================================");

        return result;
    }

}
