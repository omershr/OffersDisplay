package com.fyberchallenge.offersdisplay.logic;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class APIAdapter  {
	public HttpGetOffersResponse getOffers(String httpGetOffersStr) {
		HttpClient http = new DefaultHttpClient();
		HttpResponse response = null;
		String body = null;
		try {
			response = http.execute(new HttpGet(httpGetOffersStr));
			body = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String signature = null;
		if (response!=null){
			Header[] headers = response.getHeaders(MessagesStrings.RESPONSE_SIGNATURE_TAG);
			if (headers!=null&&headers.length>0){
				signature = headers[0].getValue();
			}
		}
		return new HttpGetOffersResponse(body, signature);
	}
}
