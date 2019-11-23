package com.windea.study.integrated.java9;

import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientTest {
	@Test
	public void test1() throws Exception {

		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create("http://www.atguitu.com")).GET().build();
		var response = client.send(request, BodyHandlers.ofString());

		System.out.println(response.statusCode());
		System.out.println(response.body());
	}
}
