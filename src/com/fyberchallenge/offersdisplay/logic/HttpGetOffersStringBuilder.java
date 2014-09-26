package com.fyberchallenge.offersdisplay.logic;

public class HttpGetOffersStringBuilder {
	private String uid;
	private String apikey; 
	private String appid;
	private String pub0;
	private DeviceAdapter deviceAdapter; 
	
	public HttpGetOffersStringBuilder(String uid, String apikey, String appid,
			String pub0, DeviceAdapter deviceAdapter) {
		initUserParameters(uid, apikey, appid, pub0);
		this.deviceAdapter  = deviceAdapter;
	}
	public void initUserParameters(String uid, String apikey, String appid,
			String pub0) {
		this.uid = uid;
		this.apikey = apikey;
		this.appid = appid;
		this.pub0 = pub0;
	}

	public String build() {
		StringBuilder httpGet = concatenateParameters();
		String hashCode = new SHA1Service().hash(httpGet.toString() + apikey);
		httpGet.append(MessagesStrings.HASHKEY_TAG);
		httpGet.append(hashCode);
		return MessagesStrings.REQUEST_PREFIX + httpGet.toString();
	}

	private  void appendParameter(StringBuilder httpGet, String tag, String value){
		httpGet.append(tag);
		httpGet.append(value);
		httpGet.append(MessagesStrings.PARAMETERS_DIVIDER);
	}
	private  StringBuilder concatenateParameters() {
		StringBuilder httpGet = new StringBuilder();
		appendParameter(httpGet, MessagesStrings.APPID_TAG, appid);
		appendParameter(httpGet, MessagesStrings.DEVICE_ID_TAG, deviceAdapter.getId());
		appendParameter(httpGet, MessagesStrings.LOCALE_TAG, deviceAdapter.getLocale());
		appendParameter(httpGet, MessagesStrings.OS_VERSION_TAG, deviceAdapter.getOsVersion());
		appendParameter(httpGet, MessagesStrings.PUB0_TAG, pub0);
		appendParameter(httpGet, MessagesStrings.TIMESTAMP_TAG, deviceAdapter.getUnixTimestamp());
		appendParameter(httpGet, MessagesStrings.UID_TAG, uid);
		return httpGet;
	}

}
