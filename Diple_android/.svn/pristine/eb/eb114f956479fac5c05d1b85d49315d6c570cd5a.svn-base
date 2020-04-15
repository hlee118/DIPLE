package com.diple.diple.location_marker;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.diple.diple.location_marker.PhotoPagerFragmentMain.stagerPagerListener;
import com.diple.diple.severitem.LocationResult;
import com.google.android.gms.maps.model.LatLng;

public class MarkerLocationAdapter extends BaseAdapter {

	LocationResult myItem;
	Context mContext;
	FragmentManager mFm;
	public static final int VIEW_TYPE_COUNT = 3;
	public static final int PHOTO = 0;
	public static final int VALUE = 1;
	public static final int COMENT = 2;
	
	ArrayList<Integer> typeList = new ArrayList<Integer>();
	public MarkerLocationAdapter(Context context, FragmentManager fm, LocationResult result){
		mContext = context;
		mFm = fm;
		typeList.add(PHOTO);
		typeList.add(VALUE);
		myItem = result;
		for( int i =0 ; i <  myItem.comment.size() ; i++){
			typeList.add(COMENT);
		}
	}
	public void add(LocationResult result){
		typeList.clear();
		typeList.add(PHOTO);
		typeList.add(VALUE);
		myItem = result;
		for( int i =0 ; i <  myItem.comment.size() ; i++){
			typeList.add(COMENT);
		}
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return typeList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE_COUNT;
	}
	
	@Override
	public int getItemViewType(int position) {
		switch(typeList.get(position)) {
		case PHOTO:
			return PHOTO;
		case VALUE:
			return VALUE;
		case COMENT :
			return COMENT;
	
		}
		return COMENT;
	}
	public interface StaggerdListener{
		public void startStaggerd();
	}
	StaggerdListener staggerdListener;
	
	public void setOnStartStagger(StaggerdListener listener){
		staggerdListener = listener;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		switch (getItemViewType(position)) {
		
		case MarkerItem.MAKER_LIST_TYPE_PHOTO:
			PhotoPagerFragmentMain v;
			if (convertView == null) {
				v = new PhotoPagerFragmentMain(mContext, mFm,myItem.photo);
			} else {
				v = (PhotoPagerFragmentMain) convertView;
			}
			v.getStartStaggerd(new stagerPagerListener() {
				
				@Override
				public void startStaggerd() {
					staggerdListener.startStaggerd();
				}
			});

			return v;
			
		case MarkerItem.MAKER_LIST_TYPE_VALUE:
			PagerItemFramelayout v2;
			if (convertView == null) {
				v2 = new PagerItemFramelayout(mContext);
			} else {
				v2 = (PagerItemFramelayout) convertView;
			}
			v2.setData(myItem.locationInfo.get(0));
			return v2;
		case MarkerItem.MAKER_LIST_TYPE_COMNET:
			PhotoPagerComentLayout v3;
			if (convertView == null) {
				v3 = new PhotoPagerComentLayout(mContext);
			} else {
				v3 = (PhotoPagerComentLayout)convertView;
			}
			v3.setData(myItem.comment.get(position-2));
			return v3;
		}
		return null;
	}

}
