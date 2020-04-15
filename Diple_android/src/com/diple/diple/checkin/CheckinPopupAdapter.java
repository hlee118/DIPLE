package com.diple.diple.checkin;

import java.util.ArrayList;

import com.diple.diple.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CheckinPopupAdapter extends BaseAdapter {
	Context mContext;
	public CheckinPopupAdapter(Context context){
		mContext = context;
		CheckinItem item = new CheckinItem();
		item.btnR = CheckinPopupItemLayout.BTN_PLUS;
		item.photo = null;
		mList.add(item);
	}
	ArrayList<CheckinItem> mList = new ArrayList<CheckinItem>();

	public void add(CheckinItem item){
		mList.remove(mList.size()-1);
		mList.add(item);
		CheckinItem item2 = new CheckinItem();
		item2.btnR = CheckinPopupItemLayout.BTN_PLUS;
		item2.photo = null;
		mList.add(item2);
		notifyDataSetChanged();
	}
	
	public void del(int position){
		mList.remove(position);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CheckinPopupItemLayout view;
		if(convertView == null){
			view = new CheckinPopupItemLayout(mContext);
		}else{
			view = (CheckinPopupItemLayout)convertView;
		}
		view.setDate(mList.get(position).photo, mList.get(position).btnR);
		return view;
	}
}
