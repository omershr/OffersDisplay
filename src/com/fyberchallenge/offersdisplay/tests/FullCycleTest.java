package com.fyberchallenge.offersdisplay.tests;

import junit.framework.TestCase;

import org.json.JSONException;

import com.fyberchallenge.offersdisplay.logic.OffersDecoder;
import com.fyberchallenge.offersdisplay.logic.OffersProvider;
import com.fyberchallenge.offersdisplay.tests.mocks.MockAPIAdapter;
import com.fyberchallenge.offersdisplay.tests.mocks.MockDeviceAdapter;
import com.fyberchallenge.offersdisplay.tests.mocks.MockNullBitmapFetcher;
import com.fyberchallenge.offersdisplay.tests.strings.TestStrings;

public class FullCycleTest extends TestCase {
	private OffersProvider offersProvider;
	private OffersDecoder offersDecoder;
	private MockDeviceAdapter mockDeviceAdapter;
	private MockAPIAdapter mockAPIAdapter;

	public FullCycleTest() {
		mockDeviceAdapter = new MockDeviceAdapter();
		offersDecoder = new OffersDecoder(new MockNullBitmapFetcher());
	}

	public void testSuccessfulResponse() throws JSONException{
		testFullCycle(TestStrings.JSON_SUCCESSFUL_2_OFFERS_RESPONSE, 
				TestStrings.SUCCESSFUL_2_OFFERS_SIGNATURE, 2);
	}
	
	private void testFullCycle(String mockOffersResponse,
			String mockOffersResponseSignature, int expectedOffersNum) {
		mockAPIAdapter = new MockAPIAdapter(mockOffersResponse, mockOffersResponseSignature);
		offersProvider = new OffersProvider(mockAPIAdapter, mockDeviceAdapter);
		assertTrue(expectedOffersNum == offersDecoder.decode(offersProvider.getOffersString(TestStrings.UID, TestStrings.API_KEY, 
				TestStrings.APPID, TestStrings.PUB0)).size());
	}

	public void testEmptyMessageResponse(){
		testFullCycle(null, TestStrings.SUCCESSFUL_2_OFFERS_SIGNATURE, 0);
	}

	public void testAuthenticationFailure(){
		testFullCycle(TestStrings.JSON_SUCCESSFUL_2_OFFERS_RESPONSE, 
				TestStrings.FAKE_SIGNATURE,  0);
	}
	
	public void testNoOffersResponse(){
		testFullCycle(TestStrings.JSON_NO_OFFERS_RESPONSE, 
				TestStrings.NO_OFFERS_SIGNATURE,  0);
	}
	
	public void testErrorResponse(){
		testFullCycle(TestStrings.JSON_ERROR_RESPONSE, 
				TestStrings.ERROR_SIGNATURE,  0);
	}
}
