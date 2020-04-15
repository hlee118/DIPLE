package com.diple.diple.checkin;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.diple.diple.R;

public class CheckinPopupItemLayout extends FrameLayout {
 
	public static final int BTN_PLUS = 0;
	public static final int BTN_MINUS = 1;
	public CheckinPopupItemLayout(Context context) {
		super(context);
		init();
	}
	
	ImageView photo;
	ImageView imagebtn;
	public void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.checkin_btn_item, this);
		photo = (ImageView)findViewById(R.id.imagePhoto);
		imagebtn = (ImageView)findViewById(R.id.imageminus);
	}
	
	public void setDate(Bitmap bm, int btn){
		photo.setImageBitmap(bm);
		if(btn == BTN_MINUS){
			imagebtn.setImageResource(R.drawable.picture_delete_btn);
		}else if(btn == BTN_PLUS){
			imagebtn.setImageResource(R.drawable.pictrue_plus_btn);
		}
	}
}
