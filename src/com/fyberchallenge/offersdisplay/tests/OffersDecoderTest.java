package com.fyberchallenge.offersdisplay.tests;

import java.util.List;

import junit.framework.TestCase;

import com.fyberchallenge.offersdisplay.logic.Offer;
import com.fyberchallenge.offersdisplay.logic.OffersDecoder;
import com.fyberchallenge.offersdisplay.tests.mocks.MockNullBitmapFetcher;
import com.fyberchallenge.offersdisplay.tests.strings.TestStrings;

public class OffersDecoderTest extends TestCase {
	private OffersDecoder offersDecoder;
	
	public OffersDecoderTest() {
		offersDecoder = new OffersDecoder(new MockNullBitmapFetcher());
	}
	
	public void testDecode(){
		String offersStr = TestStrings.JSON_2_OFFERS_OBJECT;
		List<Offer> offers = offersDecoder.decode(offersStr);
		assertEquals(2, offers.size());
		validateOffer(offers.get(0), TestStrings.FIRST_OFFER_TITLE, 
				TestStrings.FIRST_OFFER_TEASER, TestStrings.FIRST_OFFER_THUMBNAIL,
				TestStrings.FIRST_OFFER_PAYOUT);
		validateOffer(offers.get(1), TestStrings.SECOND_OFFER_TITLE, 
				TestStrings.SECOND_OFFER_TEASER, TestStrings.SECOND_OFFER_THUMBNAIL,
				TestStrings.SECOND_OFFER_PAYOUT);
	}

	private void validateOffer(Offer offer, String title, String teaser,
			String thumbnailHires, String payout) {
		assertEquals(title, offer.title);
		assertEquals(teaser, offer.teaser);
		assertEquals(thumbnailHires, offer.thumbnailHires);
		assertEquals(payout, offer.payout);
	}
}
