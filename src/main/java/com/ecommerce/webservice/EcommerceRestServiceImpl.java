package com.ecommerce.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EcommerceRestServiceImpl<T> {
	private T t;
	
	private static final String serviceUrl = "http://localhost:8085/user";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public EcommerceRestServiceImpl(T t) {
		this.t = t;
	}
	
	public T getEntity(String endPoint) {
		String uri = serviceUrl+endPoint;
		RestTemplate rt = new RestTemplate();
		ResponseEntity<T> response = (ResponseEntity<T>) rt.getForEntity(uri, this.t.getClass());
		return response.getBody();
	}
	
	public T postEntity(String endPoint, Object obj) {
		String uri = serviceUrl+endPoint;
		RestTemplate rt = new RestTemplate();
		ResponseEntity<T> response = (ResponseEntity<T>) rt.postForEntity(uri, obj, this.t.getClass());
		return response.getBody();
	}
	
}
