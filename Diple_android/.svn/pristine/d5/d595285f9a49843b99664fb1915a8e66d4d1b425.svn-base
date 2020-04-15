package com.diple.diple.calender;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.calender.CalendarManager.NoComparableObjectException;
import com.diple.diple.recoded.RecodedDetailActivity;
import com.diple.diple.recoded.RecordedPreviewAdapter;

public class SingleCalenderFragment extends Fragment {

	LinearLayout layout;
	TextView titleView;
	GridView gridView;
	CalendarAdapter mAdapter;
	GestureDetector mDetector;
	ArrayList<ItemData> mItemdata = new ArrayList<ItemData>();
	CalendarData data;
	String[] months;
	TextView dateView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.calender_calender, container, false);
		layout = (LinearLayout) v.findViewById(R.id.capture);
		gridView = (GridView) v.findViewById(R.id.calenderGridView);
		titleView = (TextView) v.findViewById(R.id.title);

		months = getActivity().getResources().getStringArray(R.array.months);

		Button btn = (Button) v.findViewById(R.id.btnnextMonth);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CalendarData data = CalendarManager.getInstance()
						.getNextMonthCalendarData();
				titleView.setText(months[data.month] + ", " + (data.year));
				mAdapter.setCalendarData(data);
			}
		});

		btn = (Button) v.findViewById(R.id.btnlastMonth);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CalendarData data = CalendarManager.getInstance()
						.getLastMonthCalendarData();
				titleView.setText(months[data.month] + ", " + (data.year));
				mAdapter.setCalendarData(data);
			}
		});
		if (RecordedPreviewAdapter.getInstance().reData.size() != 0) {
			for (int i = 0; i < RecordedPreviewAdapter.getInstance().reData
					.size(); i++) {
				String date_temp = RecordedPreviewAdapter.getInstance().reData
						.get(i).result.get(0).info.regdate;
				String[] temp1 = date_temp.split("T");
				String[] temp2 = temp1[0].split("-");
				mItemdata.add(new ItemData(Integer.parseInt(temp2[0]), Integer
						.parseInt(temp2[1])-1, Integer.parseInt(temp2[2]), "A"));
			}
			try {
				CalendarManager.getInstance().setDataObject(mItemdata);
			} catch (NoComparableObjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			CalendarManager.getInstance().setDataObject(mItemdata);
		} catch (NoComparableObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = CalendarManager.getInstance().getCalendarData();
		titleView.setText(months[data.month] + ", " + (data.year));
		mAdapter = new CalendarAdapter(getActivity(), data);
		gridView.setAdapter(mAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				CalendarItem item = (CalendarItem) mAdapter.getItem(position);
				int newPosition;
				item.items.size();
				if(item.items.size() != 0){
					// 기록 상세페이지로 해당 날짜 찾아서 이동
					dateView = (TextView)view.findViewById(R.id.number);
					newPosition = RecordedPreviewAdapter.getInstance().getDateList().indexOf(getDateString(dateView.getText().toString()));
					Intent i = new Intent(getActivity(), RecodedDetailActivity.class);
					i.putExtra("put", newPosition);
					startActivity(i);
				} else {
					Toast.makeText(getActivity(), "해당 날짜의 기록이 없습니다. ", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		nowmonth = data.month;
		year = data.year;
		
		return v;
	}

	public void setCapture() {
		mHandler.post(captureRunnable);
//		getCapturCalender();
	}

	public int getYear() {
		return year;
	}

	interface getBitmap {
		public void setOnBitmap(Bitmap bit);
	}

	getBitmap mListener;

	public void getBitmap(getBitmap listener) {
		mListener = listener;
	}

	int year;
	ArrayList<Bitmap> mList = new ArrayList<Bitmap>();

	private void getCapturCalender() {
		
		
		month = 0;

		// mHandler.post(captureRunnable);
//		for (int i = 0; i < 12; i++) {
//			titleView.setText(months[i] + ", " + (data.year));
//			mAdapter.setCalendarData(CalendarManager.getInstance()
//					.getCalendarData(data.year, i));
//		}
	}
	int nowmonth = 0;
	int month = 0;

	Handler mHandler = new Handler();

	Runnable captureRunnable = new Runnable() {

		@Override
		public void run() {
			if (month != 0) {
				mList.add(getViewBitmap(layout));
				if(month == 12){
					mAdapter.setCalendarData(data);
					((CalenderMainActivity)getActivity()).list.addAll(mList);
					titleView.setText(months[data.month] + ", " + (data.year));
				}
			}
			if (month < 12) {
				titleView.setText(months[month] + ", " + (year));
				mAdapter.setPhoto(month);
				month++;
				mHandler.postDelayed(this, 2);
			}
		}
	};

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

		Bitmap bitmap = Bitmap
				.createScaledBitmap(
						cacheBitmap,
						(int) getResources().getDimension(
								R.dimen.calender_image_width),
						(int) getResources().getDimension(
								R.dimen.calender_image_height), false);
		// Restore the view
		v.destroyDrawingCache();
		v.setWillNotCacheDrawing(willNotCache);
		v.setDrawingCacheBackgroundColor(color);

		return bitmap;
	}
	
	private String getDateString(String day){
		String nowMonthString;
		String nowDayString = null;
		
		if((nowmonth+1) < 10){
			nowMonthString = "0"+(nowmonth+1);
		} else {
			nowMonthString = ""+(nowmonth+1);
		}
		
		if(day.length() == 1){
			nowDayString = "0"+day;
		} else {
			nowDayString = day;
		}
		return (year + "-" + nowMonthString + "-" + nowDayString);
	}
}
