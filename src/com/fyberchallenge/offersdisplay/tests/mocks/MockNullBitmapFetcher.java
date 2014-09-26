package com.fyberchallenge.offersdisplay.tests.mocks;

import java.util.List;

import android.graphics.Bitmap;

import com.fyberchallenge.offersdisplay.logic.BitmapFetcher;

public class MockNullBitmapFetcher extends BitmapFetcher {

	@Override
	public Bitmap[] fetchBitmaps(List<String> thumbnailURLs) {
		if (thumbnailURLs==null){
			return null;
		}
		return new Bitmap[thumbnailURLs.size()];
	}

}
