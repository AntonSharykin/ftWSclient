package com.anton;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientWS {
    public static void main(String[] args) throws ClientProtocolException,IOException {

        Employee employee = new Employee("E09","toha","rty");
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/rest/employees").build());
        ClientResponse response1 = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
        System.out.println("Response " + response1.getEntity(String.class));
        HttpClient client1 = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/rest/employees");
        HttpResponse response = client1.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
}
