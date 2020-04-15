package com.diple.diple.subview;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.diple.diple.checkinitem.Poi;
import com.diple.diple.checkinitem.TMapPOIResult;

public class SearchAdapter extends BaseAdapter {
	Context mContext;
	ArrayList<Poi> mList = new ArrayList<Poi>();;
	public SearchAdapter(Context context){
		mContext = context;
	}
	public void addAll(TMapPOIResult item){
		mList.addAll(item.getSearchPoiInfo().getPois().getPoi());
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
	public Poi getItem(int position) {
		return mList.get(position);
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
		view.setData(mList.get(position).name, mList.get(position).getAddr(), mList.get(position).getCategory(), position);
		return view;
	}

}
