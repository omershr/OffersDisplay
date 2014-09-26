package com.fyberchallenge.offersdisplay.tests;

import junit.framework.TestCase;

import com.fyberchallenge.offersdisplay.logic.HttpGetOffersStringBuilder;
import com.fyberchallenge.offersdisplay.tests.mocks.MockDeviceAdapter;
import com.fyberchallenge.offersdisplay.tests.strings.TestStrings;

public class HttpGetOffersStringBuilderTest extends TestCase {
	private HttpGetOffersStringBuilder httpGetBuilder;
	public HttpGetOffersStringBuilderTest() {
		String uid = TestStrings.UID;
		String apikey = TestStrings.API_KEY;
		String appid = TestStrings.APPID;
		String pub0 = TestStrings.PUB0;
		httpGetBuilder = new HttpGetOffersStringBuilder(uid, apikey, appid, pub0, new MockDeviceAdapter());
	}
	public void testBuildString() {
		assertEquals(TestStrings.HTTP_GET, httpGetBuilder.build());
	}
}
