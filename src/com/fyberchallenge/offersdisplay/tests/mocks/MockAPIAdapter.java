package com.fyberchallenge.offersdisplay.tests.mocks;

import com.fyberchallenge.offersdisplay.logic.APIAdapter;
import com.fyberchallenge.offersdisplay.logic.HttpGetOffersResponse;

public class MockAPIAdapter extends APIAdapter {
	public String mockMessage;
	public String mockMessageSignature;
	public MockAPIAdapter(String mockMessage, String mockMessageSignature){
		this.mockMessage = mockMessage;
		this.mockMessageSignature = mockMessageSignature;
	}
	
	@Override
	public HttpGetOffersResponse getOffers(String httpGetOffersStr) {
		return new HttpGetOffersResponse(mockMessage, mockMessageSignature);
	}
}
