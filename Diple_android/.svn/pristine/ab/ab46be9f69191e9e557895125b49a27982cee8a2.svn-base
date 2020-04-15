package com.diple.diple.subview;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.diple.diple.R;
import com.diple.diple.location_marker.MarkerItem;
import com.diple.diple.location_marker.MarkerNumberImageFrameLayout;
import com.diple.diple.location_marker.MarkerResult;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerManager {

	private static MarkerManager instance;
	private MarkerManager(){}
	public static MarkerManager getInstance(){
		if(instance == null){
			instance = new MarkerManager();
		}
		return instance;
	}
	
	public static final int MARKER_IMG_WIDTH = 147;
	public static final int MARKER_IMG_HEIGHT = 207;

	TextView textMarkerCheckinNum;
	ImageView imgMarkerEmpty;
	
	public ArrayList<MarkerItem> mList = new ArrayList<MarkerItem>();

	public void add(MarkerResult item){
		mList.clear();
		mList.addAll(item.result);
	}

	public void getMarkerOfTema(int tema, GoogleMap mMap, HashMap<String, Marker> mMarkerResolver, HashMap<Marker, String> mDataResolver, FrameLayout markerEmpty){
		
		if(tema ==9){
			for(int i = 0 ; i<mList.size() ; i++){
					LatLng latLng = new LatLng(mList.get(i).latitude, mList.get(i).longitude);
					MarkerOptions options = new MarkerOptions();
					options.position(latLng);
					options.icon(BitmapDescriptorFactory.fromResource(getMarkerResId(mList.get(i).badgeType)));
//					options.icon(BitmapDescriptorFactory
//							.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
					options.anchor(0.5f, 0.5f);
					options.title(mList.get(i).placeName);
					options.snippet(mList.get(i).placeId);
					options.draggable(false);
					Marker marker = mMap.addMarker(options);
					String text = mList.get(i).addess;
					mMarkerResolver.put(text, marker);
					mDataResolver.put(marker, text);
			}
		}
		else{
			for(int i = 0 ; i<mList.size() ; i++){
				if(mList.get(i).badgeType == tema){
					LatLng latLng = new LatLng(mList.get(i).latitude, mList.get(i).longitude);
					MarkerOptions options = new MarkerOptions();
					options.position(latLng);
					
					// framelayout 받아온거 설정하기
					textMarkerCheckinNum = (TextView)markerEmpty.findViewById(R.id.textMarkerCheckinNum);
					imgMarkerEmpty = (ImageView)markerEmpty.findViewById(R.id.imgMarkerEmpty);
					textMarkerCheckinNum.setText(""+mList.get(i).checkinNum);
					imgMarkerEmpty.setImageResource(getMarkerResIdEmpty(tema));
					// framelayout 받아온거 설정 끝내기
					
					options.icon(BitmapDescriptorFactory.fromBitmap(getViewBitmap(markerEmpty)));
//					options.icon(BitmapDescriptorFactory
//							.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
					options.anchor(0.5f, 0.5f);
					options.title(mList.get(i).placeName);
					options.snippet(mList.get(i).placeId);
					options.draggable(false);
					Marker marker = mMap.addMarker(options);
					String text = mList.get(i).addess;
					mMarkerResolver.put(text, marker);
					mDataResolver.put(marker, text);
				}
			}
		}

	}
	
	private int getMarkerResId(int tema){
		switch(tema){
		case 0:
			return R.drawable.marker_pin_food;
		case 1:
			return R.drawable.marker_pin_movie;
		case 2:
			return R.drawable.marker_pin_cafe;
		case 3:
			return R.drawable.marker_pin_pup;
		case 4:
			return R.drawable.marker_pin_art;
		case 5:
			return R.drawable.marker_pin_shopping;
		case 6:
			return R.drawable.marker_pin_outsider;
		default:
			return R.drawable.marker_pin_food;
		}
		
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
	
	private Bitmap getViewBitmap(View v) {
		v.clearFocus();
		v.setPressed(false);

		boolean willNotCache = v.willNotCacheDrawing();
		v.setWillNotCacheDrawing(false);

		// Reset the drawing cache background color to fully transparent
		// for the duration of this operation
		int color = v.getDrawingCacheBackgroundColor();
		v.setDrawingCacheBackgroundColor(0);

		if (color != 0) {
			v.destroyDrawingCache();
		}
		v.buildDrawingCache();
		Bitmap cacheBitmap = v.getDrawingCache();

		Bitmap bitmap = Bitmap.createScaledBitmap(cacheBitmap,MARKER_IMG_WIDTH,MARKER_IMG_HEIGHT, false);
		
		// Restore the view
		v.destroyDrawingCache();
		v.setWillNotCacheDrawing(willNotCache);
		v.setDrawingCacheBackgroundColor(color);

		return bitmap;
	}

}
