package com.diple.diple.subview;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.diple.diple.location_marker.MarkerItem;
import com.diple.diple.location_marker.MarkerResult;

public class TemaBestAdapter extends BaseAdapter {
	Context mContext;
	public TemaBestAdapter(Context context){
		mContext = context;
	}
	ArrayList<MarkerItem> data = new ArrayList<MarkerItem>();
	public void addAll(MarkerResult result){
		data.clear();
		data.addAll(result.result);
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public MarkerItem getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SearchListFrameLayout view;
		if(convertView == null){
			view = new SearchListFrameLayout(mContext);
		}else{
			view = (SearchListFrameLayout)convertView;
		}
		view.setData(data.get(position).placeName, data.get(position).addess, data.get(position).badgeType, position);
		return view;
	}

}
