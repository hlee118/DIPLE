package com.diple.diple.calender;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MultyCalendarAdapter extends BaseAdapter {

	Context mContext;
	
	ArrayList<Bitmap> mList = new ArrayList<Bitmap>();
	public MultyCalendarAdapter(Context context) {
		mContext = context;
	}
//	public void add(Bitmap bitmap) {
//		SingleCalenderFragment.mList.add(bitmap);
//		notifyDataSetChanged();
//	}
	public void addAll(ArrayList<Bitmap> mList1){
		mList.addAll(mList1);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MultyItemFrametLayout view;
		if(convertView == null){
			view = new MultyItemFrametLayout(mContext, mList.get(position));
		}else{
			view = (MultyItemFrametLayout)convertView;
		}
		return view;
	}

	

}
