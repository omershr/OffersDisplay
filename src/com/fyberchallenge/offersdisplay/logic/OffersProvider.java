package com.fyberchallenge.offersdisplay.logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OffersProvider {
	private APIAdapter apiAdapter;
	private DeviceAdapter deviceAdapter;

	public OffersProvider(DeviceAdapter deviceAdapter) {
		this.deviceAdapter = deviceAdapter;
		this.apiAdapter = new APIAdapter();
	}

	public OffersProvider(APIAdapter apiAdapter, DeviceAdapter deviceAdapter) {
		this.apiAdapter = apiAdapter;
		this.deviceAdapter = deviceAdapter;
	}
	
	public String getOffersString(String uid, String apikey, String appid,	String pub0) {
		String httpGetOffersStr = new HttpGetOffersStringBuilder(uid, apikey, appid,	pub0, deviceAdapter).build();
		HttpGetOffersResponse response = apiAdapter.getOffers(httpGetOffersStr);
		if (response.message!=null){
			JSONObject obj = null;
			String code = null;
			String message = null;
			try {
				obj = new JSONObject(response.message);
				code = obj.getString(MessagesStrings.CODE_TAG);
				message = obj.getString(MessagesStrings.MESSAGE_TAG);
			} catch (JSONException e) {
				e.printStackTrace();
				return buildErrorMessage(MessagesStrings.PARSING_FAILURE_ERROR);
			}
			if (hasError(code)){
				return handleErrorCode(code, message);
			}
			SHA1Service sha1Service = new SHA1Service(); 
			if (sha1Service.authenticate(response.message+apikey, response.messageSignature)){
				return process(obj, code, message);
			}
			else{
				return getAuthenticationFailureErrorMessage();
			}
		}
		else{
			return getBadResponseErrorMessage(response);
		}
	}

	private boolean hasError(String code) {
		return (code!=null &&code.startsWith(MessagesStrings.ERROR_PREFIX));

	}

	private String process(JSONObject obj, String code, String message) {
		try {
			if (code.equals(MessagesStrings.CODE_OK)) {
				JSONArray offersArray = obj
						.getJSONArray(MessagesStrings.OFFERS_TAG);
				return MessagesStrings.JSON_OFFERS_OBJ_START + offersArray.toString() + MessagesStrings.JSON_OBJ_END;
			} else if (code.equals(MessagesStrings.CODE_NO_OFFERS)) {
				return MessagesStrings.NO_OFFERS;
			} else {
				return handleErrorCode(code, obj.getString(MessagesStrings.MESSAGE_TAG));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		return buildErrorMessage(MessagesStrings.PARSING_FAILURE_ERROR);
	}

	private String handleErrorCode(String code, String message) {
		return buildErrorMessage(code
				+ System.getProperty("line.separator") + message);
	}

	private String getAuthenticationFailureErrorMessage() {
		return buildErrorMessage(MessagesStrings.SHA1_AUTHENTICATION_FAILURE_ERROR);
	}
	
	private String getBadResponseErrorMessage(HttpGetOffersResponse response) {
		StringBuilder error = new StringBuilder(MessagesStrings.BAD_RESPONSE_ERROR);
		error.append(System.getProperty("line.separator"));
		error.append(MessagesStrings.MESSAGE_BODY_STR);
		error.append(System.getProperty("line.separator"));
		error.append(MessagesStrings.EMPTY_STR);
		error.append(System.getProperty("line.separator"));
		error.append(MessagesStrings.MESSAGE_SIGNATURE_STR);
		error.append(System.getProperty("line.separator"));
		error.append(response.messageSignature==null?"EMPTY":response.messageSignature);
		return buildErrorMessage(error.toString());
	}

	private String buildErrorMessage(String errorMessage){
		StringBuilder error = new StringBuilder(MessagesStrings.ERROR);
		error.append(System.getProperty("line.separator"));
		error.append(errorMessage);
		return error.toString();
	}
}
