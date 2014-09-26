package com.fyberchallenge.offersdisplay.tests;

import junit.framework.TestCase;

import com.fyberchallenge.offersdisplay.logic.SHA1Service;

public class SHA1ServiceTest extends TestCase {
	private SHA1Service sha1Service;
	
	public SHA1ServiceTest() {
		sha1Service = new SHA1Service();
	}
	
	public void testHash(){
		String output =  sha1Service.hash("appid=157&device_id=2b6f0cc904d137be2e1730235f5664094b831186&" +
				"ip=212.45.111.17&locale=de&page=2&ps_time=1312211903&pub0=campaign2&timestamp=1312553361&" +
				"uid=player1&e95a21621a1865bcbae3bee89c4d4f84");
		assertEquals("7a2b1604c03d46eec1ecd4a686787b75dd693c4d", output);
	}
	
	public void testAuthenticateSuccess(){
		assertTrue(sha1Service.authenticate("AAAAAAAAAAA", "004537f3b1fd67347489185a1c4b55da58f6edca"));
	}
	
	public void testAuthenticateFailure(){
		assertFalse(sha1Service.authenticate("AAA", "004537f3b1fd67347489185a1c4b55da58f6edca"));
	}
}
