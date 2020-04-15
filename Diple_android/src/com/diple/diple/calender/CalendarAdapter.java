package com.diple.diple.calender;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CalendarAdapter extends BaseAdapter {

	Context mContext;
	CalendarData mData;
	int month = 0;
	public CalendarAdapter(Context context, CalendarData data) {
		mContext = context;
		mData = data;
	}

	public void setCalendarData(CalendarData data) {
		mData = data;
		notifyDataSetChanged();
	}

	public void setPhoto(int month) {
		this.month = month;
		if ( month < 12) {
			setCalendarData(CalendarManager.getInstance().getCalendarData(
					mData.year, month));
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int size = 0;
		if (mData != null) {
			size = mData.days.size();
		}
		return size;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.days.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		CalenderItemFramelayout view;
		if (convertView == null) {
			view = new CalenderItemFramelayout(mContext);
		} else {
			view = (CalenderItemFramelayout) convertView;
		}
		view.setData(mData.days.get(position));
		return view;
	}

}
