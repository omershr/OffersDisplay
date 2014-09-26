package com.fyberchallenge.offersdisplay.tests;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fyberchallenge.offersdisplay.logic.MessagesStrings;
import com.fyberchallenge.offersdisplay.logic.OffersProvider;
import com.fyberchallenge.offersdisplay.tests.mocks.MockAPIAdapter;
import com.fyberchallenge.offersdisplay.tests.mocks.MockDeviceAdapter;
import com.fyberchallenge.offersdisplay.tests.strings.TestStrings;

public class OffersProviderTest extends TestCase {
	private MockDeviceAdapter mockDeviceAdapter;
	private OffersProvider offersProvider;
	private MockAPIAdapter mockAPIAdapter;

	public OffersProviderTest() {
		mockDeviceAdapter = new MockDeviceAdapter();
	}
	
	public void testSuccessfulResponse() throws JSONException{
		testOffersResponse(TestStrings.JSON_SUCCESSFUL_2_OFFERS_RESPONSE, 
				TestStrings.SUCCESSFUL_2_OFFERS_SIGNATURE,
				TestStrings.JSON_2_OFFERS_OBJECT);
	}
	
	public void testEmptyMessageResponse(){
		testNoOffersResponse(null, TestStrings.SUCCESSFUL_2_OFFERS_SIGNATURE, 
				TestStrings.ERROR_BAD_RESPONSE_EMPTY_MESSAGE);
	}

	public void testAuthenticationFailure(){
		testNoOffersResponse(TestStrings.JSON_SUCCESSFUL_2_OFFERS_RESPONSE, 
				TestStrings.FAKE_SIGNATURE,   TestStrings.ERROR_AUTHENTICATION_FAILURE_MESSAGE);
	}
	
	public void testNoOffersResponse(){
		testNoOffersResponse(TestStrings.JSON_NO_OFFERS_RESPONSE, 
				TestStrings.NO_OFFERS_SIGNATURE,  MessagesStrings.NO_OFFERS);
	}
	
	public void testErrorResponse(){
		testNoOffersResponse(TestStrings.JSON_ERROR_RESPONSE, 
				TestStrings.ERROR_SIGNATURE,  TestStrings.ERROR_INVALID_HASHKEY_MESSAGE );
	}
	
	private void testNoOffersResponse(String mockMessage, String mockMessageSignature, String expectedOffersStr){
		String OffersStr = getTestOffers(mockMessage, mockMessageSignature);
		assertEquals(expectedOffersStr, OffersStr);
	}
	
	private void testOffersResponse(String mockMessage, String mockMessageSignature, String expectedOffersStr) throws JSONException{
		String OffersStr = getTestOffers(mockMessage, mockMessageSignature);
		validateOffersObjects(new JSONObject(expectedOffersStr), new JSONObject(OffersStr));
	}

	private void validateOffersObjects(JSONObject expectedOffers,
			JSONObject offers) throws JSONException {
		JSONArray offersArray = offers.getJSONArray(MessagesStrings.OFFERS_TAG);
		JSONArray expectedOffersArray = expectedOffers.getJSONArray(MessagesStrings.OFFERS_TAG);
		assertTrue(expectedOffersArray.length()==2);
		assertTrue(offersArray.length()==2);
		validateOffer(expectedOffersArray.getJSONObject(0), offersArray.getJSONObject(0));
		validateOffer(expectedOffersArray.getJSONObject(1), offersArray.getJSONObject(1));
	}

	private void validateOffer(JSONObject expectedOffer, JSONObject offer) throws JSONException {
		assertEquals(expectedOffer.getString(MessagesStrings.TITLE_TAG), offer.getString(MessagesStrings.TITLE_TAG));
		assertEquals(expectedOffer.getString(MessagesStrings.TEASER_TAG), offer.getString(MessagesStrings.TEASER_TAG));
		assertEquals(expectedOffer.getString(MessagesStrings.PAYOUT_TAG),
				offer.getString(MessagesStrings.PAYOUT_TAG));
		assertEquals(expectedOffer.getJSONObject(MessagesStrings.THUMBNAIL_TAG).
				getString(MessagesStrings.HIRES_TAG), 
				offer.getJSONObject(MessagesStrings.THUMBNAIL_TAG).
				getString(MessagesStrings.HIRES_TAG));
	}

	private String getTestOffers(String mockMessage, String mockMessageSignature){
		mockAPIAdapter = new MockAPIAdapter(mockMessage, mockMessageSignature);
		offersProvider = new OffersProvider(mockAPIAdapter, mockDeviceAdapter);
		return offersProvider.getOffersString(TestStrings.UID, TestStrings.API_KEY, 
				TestStrings.APPID, TestStrings.PUB0);
	}
}
	