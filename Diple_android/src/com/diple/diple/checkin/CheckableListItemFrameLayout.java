package com.diple.diple.checkin;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diple.diple.R;

public class CheckableListItemFrameLayout extends FrameLayout {

	public CheckableListItemFrameLayout(Context context) {
		super(context);
		init();
	}
	LinearLayout laylay;
	ImageView image;
	TextView fullAddrText;
	public void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.checkin_list_item, this);
		
		fullAddrText = (TextView)findViewById(R.id.textFullAddr);
		image = (ImageView)findViewById(R.id.imageView1);
		laylay = (LinearLayout)findViewById(R.id.laylay);
	}
	
	public void setData(String fullAddress, int position, int type) {
		fullAddrText.setText(fullAddress);
		if(position == 0){
			image.setImageResource(R.drawable.ic_launcher);
		}
		else{
			switch(type){
			case 0:
				image.setImageResource(R.drawable.marker_pin_food_round);
				break;
			case 1:
				image.setImageResource(R.drawable.marker_pin_movie_round);
				break;
			case 2:
				image.setImageResource(R.drawable.marker_pin_cafe_round);
				break;
			case 3:
				image.setImageResource(R.drawable.marker_pin_pup_round);
				break;
			case 4:
				image.setImageResource(R.drawable.marker_pin_art_round);
				break;
			case 5:
				image.setImageResource(R.drawable.marker_pin_shopping_round);
				break;
			case 6:
				image.setImageResource(R.drawable.marker_pin_outsider_round);
				break;
			default :
				image.setImageResource(R.drawable.marker_pin_food_round);
				break;
			}
		}
		
		// 배경색 지정하기
		if((position)%2 == 0){
			laylay.setBackgroundResource(R.color.zzim_list_dark);
		} else if((position)%2 == 1){
			laylay.setBackgroundResource(R.color.zzim_list_bright);
		}
		
	}

}
