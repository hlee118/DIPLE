package com.diple.diple.subview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.diple.diple.R;

public class SearchListFrameLayout extends FrameLayout {

	public SearchListFrameLayout(Context context) {
		super(context);
		init();
	}
	TextView name;
	TextView addr;
	ImageView catecoryImage;
	public void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.search_list_layout, this);
		name = (TextView)findViewById(R.id.textView1);
		addr = (TextView)findViewById(R.id.textView2);
		catecoryImage = (ImageView)findViewById(R.id.imageView1);
	}
	
	public void setData(String name, String addr, int catecory, int position){
		this.name.setText(name);
		this.addr.setText(addr);
		switch(catecory){
		case 0:
			catecoryImage.setImageResource(R.drawable.marker_pin_food_round);
			break;
		case 1:
			catecoryImage.setImageResource(R.drawable.marker_pin_movie_round);
			break;
		case 2:
			catecoryImage.setImageResource(R.drawable.marker_pin_cafe_round);
			break;
		case 3:
			catecoryImage.setImageResource(R.drawable.marker_pin_pup_round);
			break;
		case 4:
			catecoryImage.setImageResource(R.drawable.marker_pin_art_round);
			break;
		case 5:
			catecoryImage.setImageResource(R.drawable.marker_pin_shopping_round);
			break;
		case 6:
			catecoryImage.setImageResource(R.drawable.marker_pin_outsider_round);
			break;
		default:
			catecoryImage.setImageResource(R.drawable.marker_pin_food_round);
			break;
		}
		
		if((position)%2 == 0){
			setBackgroundResource(R.color.zzim_list_dark);
		} else if((position)%2 == 1){
			setBackgroundResource(R.color.zzim_list_bright);
		}
	}
}
