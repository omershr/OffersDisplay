package com.fyberchallenge.offersdisplay.logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Semaphore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapFetcher {

	public Bitmap[] fetchBitmaps(List<String> thumbnailURLs) {
		Bitmap[] bitmaps = new Bitmap[thumbnailURLs.size()];
		Semaphore semaphore = new Semaphore(1);
		new BitmapFetcherThread(semaphore, thumbnailURLs, bitmaps).start();
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bitmaps;
	}
	
	class BitmapFetcherThread extends Thread{
		private Semaphore semaphore;
		private Bitmap[] bitmaps;
		private List<String> thumbnailURLs;
		BitmapFetcherThread(Semaphore semaphore, List<String> thumbnailURLs, Bitmap[] bitmaps){
			this.semaphore = semaphore;
			this.thumbnailURLs = thumbnailURLs;
			this.bitmaps = bitmaps;
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		
		@Override
		public void run() {
			int index = 0;
			for (String thumbnailURL: thumbnailURLs){
				if (index<bitmaps.length){
					bitmaps[index++] = getBitmap(thumbnailURL);
				}
			}
			semaphore.release();
		}
	}

	private Bitmap getBitmap(String thumbnailHires) {
		URL url;
		try {
			url = new URL(thumbnailHires);
			return BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());
		} catch (MalformedURLException e) {
			 e.printStackTrace();
			return null;
		} catch (IOException e) {
			 e.printStackTrace();
			return null;
		}
	}
}
