package com.diple.diple.choosethebest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.diple.diple.severitem.Result;

public class ChooseTheBestItemView extends FrameLayout {

	public ChooseTheBestItemView(Context context) {
		super(context);
		init();
	}
	
	ImageView itemImage;
	TextView itemName;
	TextView itemAddress;
	Result Data;
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.choosethebest_item_view, this);
		itemImage = (ImageView)findViewById(R.id.choosethebest_item_img);
		itemName = (TextView)findViewById(R.id.choosethebest_item_name);
		itemAddress = (TextView)findViewById(R.id.choosethebest_item_address);
		Button btn = (Button)findViewById(R.id.btndel);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NetworkModel.getInstance().zzimDel(getContext(), Data.placeId, new OnNetworkResult<String>() {

					@Override
					public void onSuccess(String result) {
						mListener.setDel();
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub
						Toast.makeText(getContext(), "실패 서버 불안정", Toast.LENGTH_SHORT).show();
					}
				} );
			}
		});
	}
	
	public void setItemData(Result result, int position){
		Data=result;
		switch(Data.badgeId1){
		case 0:
			itemImage.setImageResource(R.drawable.marker_pin_food_round);
			break;
		case 1:
			itemImage.setImageResource(R.drawable.marker_pin_movie_round);
			break;
		case 2:
			itemImage.setImageResource(R.drawable.marker_pin_cafe_round);
			break;
		case 3:
			itemImage.setImageResource(R.drawable.marker_pin_pup_round);
			break;
		case 4:
			itemImage.setImageResource(R.drawable.marker_pin_art_round);
			break;
		case 5:
			itemImage.setImageResource(R.drawable.marker_pin_shopping_round);
			break;
		case 6:
			itemImage.setImageResource(R.drawable.marker_pin_outsider_round);
			break;
		}
//		itemImage.setImageResource(result.badgeId1);
		itemName.setText(result.placeName);
		itemAddress.setText(result.address);
//		itemAddress.setText("명이 체크인하였습니다.");
		if((position)%2 == 0){
			setBackgroundResource(R.color.zzim_list_dark);
		} else if((position)%2 == 1){
			setBackgroundResource(R.color.zzim_list_bright);
		}
	}
	
	interface setDelListener{
		public void setDel();
	}
	setDelListener mListener;
	public void setDelListener(setDelListener Listener){
		mListener = Listener;
	}
}
