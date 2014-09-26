package com.fyberchallenge.offersdisplay.logic;

import java.util.Locale;

import android.content.Context;
import android.provider.Settings.Secure;

public class DeviceAdapter {
	private Context context;
	
	public DeviceAdapter(Context context) {
		this.context = context;
	}
	
	public String getLocale() {
		return Locale.getDefault().toString();
	}

	public String getId() {
		 return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID); 
	}

	public String getOsVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	public String getUnixTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000L);
	}
}
