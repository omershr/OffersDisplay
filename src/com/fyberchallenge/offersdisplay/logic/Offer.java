package com.fyberchallenge.offersdisplay.logic;

import android.graphics.Bitmap;

public class Offer {
	public Offer(String title, String teaser, String thumbnailHires, String payout) {
		this.title = title;
		this.teaser = teaser;
		this.thumbnailHires = thumbnailHires;
		this.payout = payout;
	}
	public String title;
	public String teaser;
	public String thumbnailHires;
	public String payout;
	public Bitmap bitmap; 
}
