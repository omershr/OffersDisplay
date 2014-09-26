package com.fyberchallenge.offersdisplay.tests.strings;

import com.fyberchallenge.offersdisplay.logic.MessagesStrings;

public class TestStrings {
	public static final String JSON_2_OFFERS = "\"offers\" :  [ " +
			" {    \"title\":  \"Tap  Fish\",    \"offer_id\":  \" 13554\",     \"teaser\" :  \"Download and START\" ," +
			"    \" required _actions \" :  \"Download and START\",     \"link\" :  \"http://iframe.sponsorpay.com/mbrowser?appid=157&lpid=11387&uid=player1\"," +
			"    \"offer_types\" :  [     {      \"offer_type_id\":  \"101\",      \"readable\":  \"Download\"     }," +
			"     {      \"offer_type_id\":  \"112\",      \"readable\":  \"Free\"     }    ] ," +
			"    \"thumbnail\" :  {     \"lowres\" :  \"http://cdn.sponsorpay.com/assets/1808/icon175x175- 2_square_60.png\" ," +
			"     \"hires\":  \"http://bluefish.openoffice.nl/manual/figures/thumbnail_simple_icon.png\"    },    \"payout\" :  \"90\"," +
			"    \"time_to_payout\" :  {     \"amount\" :  \"1800\" ,     \"readable\":  \"30 minutes\"    }  } ," +
			" {    \"title\":  \"Hallo\",    \"offer_id\":  \" 13554\",     \"teaser\" :  \"Download NOW\" ," +
			"    \" required _actions \" :  \"Download and START\",     \"link\" :  \"http://iframe.sponsorpay.com/mbrowser?appid=157&lpid=11387&uid=player1\"," +
			"    \"offer_types\" :  [     {      \"offer_type_id\":  \"101\",      \"readable\":  \"Download\"     }," +
			"     {      \"offer_type_id\":  \"112\",      \"readable\":  \"Free\"     }    ] ," +
			"    \"thumbnail\" :  {     \"lowres\" :  \"http://image10.bizrate-images.com/resize?sq=60&uid=2216744464\" ," +
			"     \"hires\":  \"http://image10.bizrate-images.com/resize?sq=60&uid=2216744464\"    },    \"payout\" :  \"70\"," +
			"    \"time_to_payout\" :  {     \"amount\" :  \"1800\" ,     \"readable\":  \"30 minutes\"    }  } " +
			"]";

	public static final String JSON_2_OFFERS_OBJECT =  "{"+ TestStrings.JSON_2_OFFERS	 +	"}";
	
	public static final String JSON_SUCCESSFUL_2_OFFERS_RESPONSE = 
		"{		 \"code\" :  \"OK\" ,		 \"message\":  \"OK\",		 \"count\":  \"1\" ,		 \"pages\":  \"1\" ," +
		"		 \"information\" :  {		  \"app_name\":  \"SP Test App\" ,		  \"appid\":  \"157\"," +
		"		  \" virtual_ currency\":  \"Coins\",		  \"country\":  \" US\" ,		  \"language\":  \" EN\" ," +
		"		  \"support_url\" :  \"http://iframe.sponsorpay.com/mobile/DE/157/my_offers\"		 },"
		 + JSON_2_OFFERS + "}";

	public static final String SUCCESSFUL_2_OFFERS_SIGNATURE ="4b4d50e5d07391afd82b544d60384dde7093dd47";
	
	public static final String FAKE_SIGNATURE ="egg32262110052becefa2eea9e8a7e453d082ea6";

	public static final String APPID = "157";

	public static final String UID = "player1";

	public static final String API_KEY = "e95a21621a1865bcbae3bee89c4d4f84";

	public static final String PUB0 = "campaign2";
	
	public static final String HTTP_GET = "http://api.sponsorpay.com/feed/v1/offers.json?appid=157&" +
			"device_id=945205a64d62d6fb&locale=de&os_version=4.1.2&pub0=campaign2&" +
			"timestamp=1411649621&uid=player1&hashkey=acf0c203ae01998c25c4533625a6876207bae11c";

	public static final String JSON_NO_OFFERS_RESPONSE = "{\"code\" :  \"NO_CONTENT\" , \"message\":  \"something\"}";

	public static final String NO_OFFERS_SIGNATURE = "7a6cef29a1ba8829ea1ec476add06a7cd59098fe";
	
	public static final String JSON_ERROR_RESPONSE = "{\"code\":\"ERROR_INVALID_HASHKEY\",\"message\":\"An invalid hashkey for this appid was given as a parameter in the request.\"}";

	public static final String ERROR_SIGNATURE = "c2ee039503abbb14619cb94934b31fbed0b2f716";
	
	
	public static final String SECOND_OFFER_PAYOUT = "70";
	
	public static final String SECOND_OFFER_THUMBNAIL = "http://image10.bizrate-images.com/resize?sq=60&uid=2216744464";
	
	public static final String SECOND_OFFER_TEASER = "Download NOW";
	
	public static final String SECOND_OFFER_TITLE = "Hallo";
	
	public static final String FIRST_OFFER_PAYOUT = "90";
	
	public static final String FIRST_OFFER_THUMBNAIL = "http://bluefish.openoffice.nl/manual/figures/thumbnail_simple_icon.png";
	
	public static final String FIRST_OFFER_TEASER = "Download and START";
	
	public static final String FIRST_OFFER_TITLE = "Tap  Fish";
	
	public static final String ERROR_INVALID_HASHKEY_MESSAGE = MessagesStrings.ERROR +  
			System.getProperty("line.separator")	+	"ERROR_INVALID_HASHKEY" 	
			+ System.getProperty("line.separator")	+ "An invalid hashkey for this appid " +
					"was given as a parameter in the request.";

	public static final String ERROR_AUTHENTICATION_FAILURE_MESSAGE = MessagesStrings.ERROR + 
				System.getProperty("line.separator") + MessagesStrings.SHA1_AUTHENTICATION_FAILURE_ERROR;

	public static final String ERROR_BAD_RESPONSE_MESSAGE = MessagesStrings.ERROR + 
				System.getProperty("line.separator") + MessagesStrings.BAD_RESPONSE_ERROR;

	public static final String ERROR_BAD_RESPONSE_EMPTY_MESSAGE = 
			ERROR_BAD_RESPONSE_MESSAGE + System.getProperty("line.separator") +
			(MessagesStrings.MESSAGE_BODY_STR) + System.getProperty("line.separator") +
			MessagesStrings.EMPTY_STR + System.getProperty("line.separator") +
			MessagesStrings.MESSAGE_SIGNATURE_STR + System.getProperty("line.separator") +
			SUCCESSFUL_2_OFFERS_SIGNATURE;

}
