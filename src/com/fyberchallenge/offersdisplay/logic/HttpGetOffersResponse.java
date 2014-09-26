package com.fyberchallenge.offersdisplay.logic;

public class HttpGetOffersResponse {
	public String message;
	public String messageSignature;

	public HttpGetOffersResponse(String message, String messageSignature){
		this.message = message;
		this.messageSignature = messageSignature;
	}
}
