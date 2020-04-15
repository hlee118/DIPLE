package com.diple.diple.location_marker;

import java.util.ArrayList;

import com.diple.diple.R;
import com.diple.diple.calender.ItemData;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class MarkerNumberImageFrameLayout extends FrameLayout {
	
	Context mContext;
	int thema;
	int checkInNum=0;
	
	public MarkerNumberImageFrameLayout(Context context, int thema, int checkInNum) {
		super(context);
		mContext = context;
		this.thema = thema;
		this.checkInNum = checkInNum;
		
		init();
	}

	ImageView imgMarkerEmpty;
	TextView textMarkerCheckinNum;
	
	LayoutParams p;
	ArrayList<ItemData> mItemdata = new ArrayList<ItemData>();

	public void init() {
		LayoutInflater.from(getContext()).inflate(R.id.marker_number_framelayout, this);
		imgMarkerEmpty = (ImageView) findViewById(R.id.imgMarkerEmpty);
		textMarkerCheckinNum = (TextView)findViewById(R.id.textMarkerCheckinNum);
//		imgMarkerEmpty.setImageResource(getMarkerResId(thema));
		textMarkerCheckinNum.setText(""+checkInNum);
		
//		mImage.setImageBitmap(position);
		
	}
	
	private int getMarkerResIdEmpty(int thema){
		switch(thema){
		case 0:
			return R.drawable.marker_pin_food_empty;
		case 1:
			return R.drawable.marker_pin_movie_empty;
		case 2:
			return R.drawable.marker_pin_cafe_empty;
		case 3:
			return R.drawable.marker_pin_pup_empty;
		case 4:
			return R.drawable.marker_pin_art_empty;
		case 5:
			return R.drawable.marker_pin_shopping_empty;
		case 6:
			return R.drawable.marker_pin_outsider_empty;
		default:
			return R.drawable.marker_pin_food_empty;
		}	
	}

}
