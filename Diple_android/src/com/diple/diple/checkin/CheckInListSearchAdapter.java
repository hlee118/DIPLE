package com.diple.diple.checkin;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.diple.diple.checkinitem.Poi;
import com.diple.diple.checkinitem.TMapPOIResult;
import com.diple.diple.location_marker.MarkerItem;
import com.diple.diple.subview.MarkerManager;

public class CheckInListSearchAdapter extends BaseAdapter {
	Context mContext;
	ArrayList<MarkerItem> mList = new ArrayList<MarkerItem>();;
	public CheckInListSearchAdapter(Context context){
		mContext = context;
		MarkerItem item = new MarkerItem(
				);
		item.placeName = "새지역 만들기";
		mList.add(item);
		mList.addAll(MarkerManager.getInstance().mList);
	}
	public void addAll(TMapPOIResult item){
		
		notifyDataSetChanged();
	}
	public void deleteAll(){
		mList.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public MarkerItem getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CheckableListItemFrameLayout view;
		if(convertView == null){
			view = new CheckableListItemFrameLayout(mContext);
		}else{
			view = (CheckableListItemFrameLayout)convertView;
		}
		view.setData(mList.get(position).placeName,position,mList.get(position).badgeType);
		return view;
	}

}
