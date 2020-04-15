package com.diple.diple.calender;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.diple.diple.R;
import com.diple.diple.calender.CalendarManager.NoComparableObjectException;

public class MultyItemFrametLayout extends FrameLayout {
	Bitmap position;
	Context mContext;

	public MultyItemFrametLayout(Context context, Bitmap position) {
		super(context);
		mContext = context;
		this.position = position;
		
		init();
	}

	ImageView mImage;
	
	LayoutParams p;
	ArrayList<ItemData> mItemdata = new ArrayList<ItemData>();

	public void init() {
		LayoutInflater.from(getContext()).inflate(
				R.layout.calender_image_layout, this);
		mImage = (ImageView) findViewById(R.id.imageView1);
//		p = new LayoutParams((int) getResources().getDimension(
//				R.dimen.calender_image_width), (int) getResources()
//				.getDimension(R.dimen.calender_image_height));
//		mImage.setLayoutParams(p);
		
		mImage.setImageBitmap(position);
		
	}
}
