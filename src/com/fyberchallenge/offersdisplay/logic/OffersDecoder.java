package com.fyberchallenge.offersdisplay.logic;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

public class OffersDecoder {
	private BitmapFetcher bitmapFetcher;

	public OffersDecoder() {
		this.bitmapFetcher = new BitmapFetcher();
	}

	public OffersDecoder(BitmapFetcher bitmapFetcher) {
		this.bitmapFetcher = bitmapFetcher;
	}
	
	public List<Offer> decode(String offersStr) {
		List<Offer> offerList = new ArrayList<Offer>();
		try {
			JSONObject obj = new JSONObject(offersStr);
			JSONArray offersArray = obj.getJSONArray(MessagesStrings.OFFERS_TAG);
			for (int i = 0; i < offersArray.length(); ++i) {
				JSONObject offerObject = offersArray.getJSONObject(i);
				offerList.add(new Offer(offerObject.getString(MessagesStrings.TITLE_TAG),
						offerObject.getString(MessagesStrings.TEASER_TAG), offerObject
								.getJSONObject(MessagesStrings.THUMBNAIL_TAG).getString(
										MessagesStrings.HIRES_TAG), offerObject
								.getString(MessagesStrings.PAYOUT_TAG)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}	
		updateBitmaps(offerList);
		return offerList;
	}
	
	private void updateBitmaps(List<Offer> offers) {
		List<String> thumbnailURLs = extractThumbnailURLs(offers);
		Bitmap[] bitmaps = bitmapFetcher.fetchBitmaps(thumbnailURLs);
		int index = 0;
		for (Offer offer: offers){
			if (index < bitmaps.length){
				offer.bitmap = bitmaps[index++];
			}
		}
	}

	private List<String> extractThumbnailURLs(List<Offer> offers) {
		List<String> thumbnailURLs = new ArrayList<String>();
		for (Offer offer: offers){
			thumbnailURLs.add(offer.thumbnailHires);
		}
		return thumbnailURLs;
	}

}
