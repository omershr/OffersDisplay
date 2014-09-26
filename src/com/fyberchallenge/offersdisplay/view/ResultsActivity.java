package com.fyberchallenge.offersdisplay.view;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.fyberchallenge.offersdisplay.R;
import com.fyberchallenge.offersdisplay.logic.MessagesStrings;
import com.fyberchallenge.offersdisplay.logic.Offer;
import com.fyberchallenge.offersdisplay.logic.OffersDecoder;

public class ResultsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        String offersStr = getIntent().getExtras().getString((getString(R.string.offers_str)));
        displayOffersInfo(offersStr);
    }

	private void displayOffersInfo(String offersStr) {
		if (offersStr.startsWith(MessagesStrings.ERROR) ||
				offersStr.equals(MessagesStrings.NO_OFFERS)){
			((TextView) findViewById(R.id.no_offers)).setText(offersStr);
		}
		else{
	    	ListView listView = (ListView) findViewById(R.id.offers_list);
	    	List<Offer>offers = new OffersDecoder().decode(offersStr);
	    	listView.setAdapter(new OfferListAdapter(this, R.layout.offers_list_item, offers));
		}
	}

}
