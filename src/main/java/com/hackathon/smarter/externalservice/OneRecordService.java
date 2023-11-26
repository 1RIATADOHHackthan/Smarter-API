/**
 * 
 */
package com.hackathon.smarter.externalservice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hackathon.smarter.schema.Shipment;

/**
 * @author veera
 *
 */
@Component
public class OneRecordService {

	private final static String CLIENT_ID = "neone-client";
	private final static String CLIENT_SECRET = "lx7ThS5aYggdsMm42BP3wMrVqKm9WpNY";
	private final static String RESOURCE = "https://XXXX.crmXX.dynamics.com";
	private final static String AUTHORITY = "https://vwsdut339q.us-east-1.awsapprunner.com/realms/neone/protocol/openid-connect/token";
	private final static String ONERECORD_SERVER = "https://8psprsmhz5.us-east-1.awsapprunner.com";

	public String getAccessToken() throws MalformedURLException, IOException {
		String token = getAccessToken(RESOURCE, CLIENT_ID, CLIENT_SECRET, AUTHORITY);
		// According OAuth documentation we need to send the client id and secret key in
		// the header for authentication

		return token;
	}

	public static String getAccessToken(String RESOURCE, String CLIENT_ID, String CLIENT_SECRET, String AUTHORITY)
			throws MalformedURLException, IOException {
		// token
		
		String parameters = "client_id="
				+ URLEncoder.encode(CLIENT_ID, java.nio.charset.StandardCharsets.UTF_8.toString()) + "&client_secret="
				+ URLEncoder.encode(CLIENT_SECRET, java.nio.charset.StandardCharsets.UTF_8.toString())
				+ "&grant_type=client_credentials";

		URL url;
		HttpURLConnection connection = null;
		url = new URL(AUTHORITY);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Length", "" + Integer.toString(parameters.getBytes().length));
		connection.setDoOutput(true);
		connection.connect();

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
		out.write(parameters);
		out.close();

		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		// String line;
		// StringBuffer response = new StringBuffer();
		String line = rd.lines().collect(Collectors.joining());
		rd.close();

		Object jResponse;
		jResponse = JSONValue.parse(line);
		JSONObject jObject = (JSONObject) jResponse;
		String access_token = jObject.get("access_token").toString();
		return access_token.toString();
	}

	public JSONObject callShipmentService(String accessToken) throws Exception {
		Shipment shipment = new Shipment();
		URL url;
		HttpURLConnection connection = null;
		String parameters = "client_id="
				+ URLEncoder.encode(CLIENT_ID, java.nio.charset.StandardCharsets.UTF_8.toString()) + "&client_secret="
				+ URLEncoder.encode(CLIENT_SECRET, java.nio.charset.StandardCharsets.UTF_8.toString())
				+ "&grant_type=client_credentials";
		url = new URL(ONERECORD_SERVER);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization","Bearer "+accessToken);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/ld+json");
		connection.setRequestProperty("Accept", "application/ld+json");
		//connection.setRequestProperty("Content-Length", "" + Integer.toString(parameters.getBytes().length));
		connection.setDoOutput(true);
		connection.connect();

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
		out.write(parameters);
		out.close();

		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		// String line;
		// StringBuffer response = new StringBuffer();
		String line = rd.lines().collect(Collectors.joining());
		rd.close();

		Object jResponse;
		jResponse = JSONValue.parse(line);
		JSONObject jObject = (JSONObject) jResponse;
		//String access_token = jObject.get("access_token").toString();
		
		
		return jObject;

	}

}
