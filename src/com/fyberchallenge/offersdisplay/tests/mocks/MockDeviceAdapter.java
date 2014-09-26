package com.fyberchallenge.offersdisplay.tests.mocks;

import com.fyberchallenge.offersdisplay.logic.DeviceAdapter;

public class MockDeviceAdapter extends DeviceAdapter {

	public MockDeviceAdapter() {
		super(null);
	}
	
	@Override
	public String getLocale() {
		return "de";
	}
	
	@Override
	public String getOsVersion() {
		return "4.1.2";
	}
	
	@Override
	public String getId() {
		return "945205a64d62d6fb";
	}
	
	@Override
	public String getUnixTimestamp() {
		return "1411649621";
	}

}
