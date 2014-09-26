package com.fyberchallenge.offersdisplay.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyberchallenge.offersdisplay.R;
import com.fyberchallenge.offersdisplay.logic.Offer;

public class OfferListAdapter extends ArrayAdapter<Offer> {

	private List<Offer> offers;
	private Context context;
	public OfferListAdapter(Context context, int textViewResourceId,
			List<Offer> offers) {
		super(context, textViewResourceId, offers);
		this.context = context;
		this.offers = offers;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.offers_list_item, null);
		}
		handleListItemViewContent(position, v);
		return v;
	}

	private void handleListItemViewContent(int position, View v) {
		Offer offer = offers.get(position);
		((TextView) v.findViewById(R.id.title)).setText(offer.title);
		initTextViewInTextsPanel(v, R.id.teaser, offer.teaser);
		initTextViewInTextsPanel(v, R.id.payout, offer.payout);
		if (offer.bitmap != null){
			ImageView iv = (ImageView) v.findViewById(R.id.list_item_content_panel).
					findViewById(R.id.thumbnail);
			iv.setImageBitmap(offer.bitmap);
		}
		else{
			TextView tv = (TextView) v.findViewById(R.id.list_item_content_panel).findViewById(R.id.thumbnail_str);
			tv.setText(offer.thumbnailHires);
			tv.setWidth((int) context.getResources().getDimension(R.dimen.thumbnail_str_len));
		}
	}

	private void initTextViewInTextsPanel(View layout, int id, String value) {
		((TextView) layout.findViewById(R.id.list_item_content_panel).
				findViewById(R.id.list_item_content_texts).findViewById(id)).setText(value);
	}
}
