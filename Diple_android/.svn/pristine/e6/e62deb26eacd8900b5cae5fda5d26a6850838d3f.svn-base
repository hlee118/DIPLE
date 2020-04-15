package com.diple.diple.calender;


import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.diple.diple.R;


public class CalenderItemFramelayout extends FrameLayout {

	public CalenderItemFramelayout(Context context) {
		super(context);
		init();
	}
	
	TextView numberView;
	ImageView backImage;
	CalendarItem mItem;
	public final static int NUMBER_COLOR = Color.BLACK;
	public final static int SAT_COLOR = Color.GRAY;
	public final static int SUN_COLOR = Color.GRAY;
	public final static float IN_MONTH_TEXT_SIZE_SP = 20.0f;

	public final static int OUT_MONTH_TEXT_COLOR = Color.WHITE;
	public final static float OUT_MONTH_TEXT_SIZE_SP = 15.0f;
	
	public void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.calender_item, this);
		numberView = (TextView)findViewById(R.id.number);
		backImage =(ImageView)findViewById(R.id.imgTodayDate);
	}
	
	public void setData(CalendarItem item) {
		mItem = item;
		float textsize = IN_MONTH_TEXT_SIZE_SP;
		int textColor = NUMBER_COLOR;
		if (!item.inMonth) {
			textsize = OUT_MONTH_TEXT_SIZE_SP;
			textColor = OUT_MONTH_TEXT_COLOR;
		} else {
			textsize = IN_MONTH_TEXT_SIZE_SP;
			switch (item.dayOfWeek) {
				case Calendar.SUNDAY :
					textColor = SUN_COLOR;
					break;
				case Calendar.SATURDAY :
					textColor = SAT_COLOR;
					break;
				default :
					textColor = NUMBER_COLOR;
					break;
			}
		}
		numberView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textsize);
		numberView.setTextColor(textColor);
		numberView.setText("" + item.dayOfMonth);
		// contentView setting
		
		ArrayList items = item.items;
		int size = items.size();
		StringBuilder sb = new StringBuilder();
		if(size != 0) {
			backImage.setVisibility(View.VISIBLE);
		}else{
			backImage.setVisibility(View.GONE);
		}
		
	}
}
