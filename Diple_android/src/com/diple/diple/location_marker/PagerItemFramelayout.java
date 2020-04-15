package com.diple.diple.location_marker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.checkin.CheckinPopupViewActivity;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.diple.diple.severitem.LocationInfo;
import com.diple.diple.shortestroute.ShortestRouteActivity;
import com.google.android.gms.maps.model.LatLng;

public class PagerItemFramelayout extends FrameLayout {
	
	

	Context mContext;
	
	public PagerItemFramelayout(Context context) {
		super(context);
		mContext=context;
		init();
	}
	LocationInfo myItem;
	TextView rankText;
	TextView nameText;
	TextView addrText;
	TextView checText;
	ImageView badge1;
	ImageView badge2;
	ImageView badge3;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.marker_location_item, this);
		
		rankText = (TextView)findViewById(R.id.textRank);
		nameText = (TextView)findViewById(R.id.textLoName);
		addrText = (TextView)findViewById(R.id.textLoAddr);
		checText = (TextView)findViewById(R.id.textCheckin);
		badge1 = (ImageView)findViewById(R.id.imageTema1);
		badge2 = (ImageView)findViewById(R.id.imageTema2);
		badge3 = (ImageView)findViewById(R.id.imageTema3);
		
		Button btn = (Button)findViewById(R.id.btnCheckIn);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), CheckinPopupViewActivity.class);
				LatLng latLng2 = new LatLng(myItem.latitude, myItem.longitude);
//				latLng.latitude = (double)Double.valueOf(mAdapter.mList.get(position).frontLat);
				i.putExtra("latlng", latLng2);
				i.putExtra("name", myItem.placeName);
				i.putExtra("addr", myItem.address);
				i.putExtra("telno", myItem.storePhone);
				i.putExtra("placeId", myItem.placeId);
				i.putExtra("id", 1);
				getContext().startActivity(i);
			}
		});	
		
		btn =(Button)findViewById(R.id.button5);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse("http://search.naver.com/search.naver?where=nexearch&query="+myItem.placeName+"&sm=top_hty&fbm=1&ie=utf8"));
				mContext.startActivity(i);
			}
		});
		btn = (Button)findViewById(R.id.btnPhoneCall);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(myItem.storePhone==null || myItem.storePhone.equals("")){
					Toast.makeText(mContext, "전화번호가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
				}else{
					String[] temp = myItem.storePhone.split("-");
					Intent phoneCallIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+temp[0]+temp[1]+temp[2]));
					getContext().startActivity(phoneCallIntent);
				}
			}
		});
		btn = (Button)findViewById(R.id.btnShortestRoot);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(mContext	, ShortestRouteActivity.class);
				i.putExtra(ShortestRouteActivity.LOCATION_LONGTITUDE, myItem.longitude);
				i.putExtra(ShortestRouteActivity.LOCATION_LATITUDE, myItem.latitude);
				mContext.startActivity(i);
			}
		});
		btn = (Button)findViewById(R.id.btnPedestrianRoute);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NetworkModel.getInstance().zzimReg(mContext, myItem.placeId, new OnNetworkResult<String>() {

					@Override
					public void onSuccess(String result) {
						Toast.makeText(mContext, "찜 완료", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(mContext, "찜 실패", Toast.LENGTH_SHORT).show();
					}
				});;
			}
		});
	}
	
	public void setData(LocationInfo item){
		myItem = item;
		nameText.setText(item.placeName);
		addrText.setText(item.address);
		checText.setText("체크인 수 : " + item.checkinNum);
		if(item.checkinNum < 5){
			rankText.setText("1."+item.checkinNum*2);
		}else if(item.checkinNum <10){
			rankText.setText("2."+(item.checkinNum - 5)*2);
		}else if(item.checkinNum <20){
			rankText.setText("3."+(item.checkinNum)%10);
		}else if(item.checkinNum <40){
			rankText.setText("4."+(item.checkinNum-20)%20);
		}else if(item.checkinNum <60){
			rankText.setText("5."+(item.checkinNum-40)%20);
		}else if(item.checkinNum <80){
			rankText.setText("6."+(item.checkinNum-60)%20);
		}else if(item.checkinNum <120){
			rankText.setText("7."+(item.checkinNum-80)%40);
		}else if(item.checkinNum <200){
			rankText.setText("8."+(item.checkinNum-120)%80);
		}else if(item.checkinNum <400){
			rankText.setText("9."+(item.checkinNum-200)%200);
		}else{
			rankText.setText("10.0");
		}
		
	}
	
}
