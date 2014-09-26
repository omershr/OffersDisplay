package com.fyberchallenge.offersdisplay.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fyberchallenge.offersdisplay.R;
import com.fyberchallenge.offersdisplay.logic.DeviceAdapter;
import com.fyberchallenge.offersdisplay.logic.OffersProvider;

public class ParametersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parameters);
		initParameterTitles();
	}

	public void initParameterTitles() {
		initParameterTitle(R.id.parameter_uid, R.string.parameter_uid);
		initParameterTitle(R.id.parameter_apikey, R.string.parameter_apikey);
		initParameterTitle(R.id.parameter_appid, R.string.parameter_appid);
		initParameterTitle(R.id.parameter_pub0, R.string.parameter_pub0);
	}

	private void initParameterTitle(int panelId, int strRsrc){
		View layout = findViewById(panelId);
		TextView tv = (TextView) layout.findViewById(R.id.parameter_text);
		tv.setText(strRsrc);
	}
	
	private String getParameter(int panelId){
		View layout = findViewById(panelId);
		EditText et = (EditText) layout.findViewById(R.id.parameter_edit);
		return et.getText().toString();
	}
	
	public void getOffers(View view){
		new Thread(new Runnable(){
	        @Override
	         public void run() {
	        	OffersProvider provider = new OffersProvider(new DeviceAdapter(ParametersActivity.this));
	        	String uid = getParameter(R.id.parameter_uid);
	        	String apikey = getParameter(R.id.parameter_apikey);
	        	String appid = getParameter(R.id.parameter_appid);
	        	String pub0 = getParameter(R.id.parameter_pub0);
	        	String offerStr = provider.getOffersString(uid, apikey, appid, pub0);
	    	    Intent intent = new Intent(ParametersActivity.this, ResultsActivity.class);
	    	    intent.putExtra(getString(R.string.offers_str), offerStr);
	    	    startActivity(intent);
	        } 
	       }).start();
	}
}
