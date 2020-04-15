package com.diple.diple.calender;

import java.util.ArrayList;
import java.util.Calendar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.diple.diple.R;

public class MultyCalenderFragment extends Fragment {
	
	GridView gridView;
	MultyCalendarAdapter mAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.calender_multi_grid, container, false);
		mAdapter = new MultyCalendarAdapter(getActivity());
		mAdapter.addAll(((CalenderMainActivity)getActivity()).list);
		gridView = (GridView)v.findViewById(R.id.gridView1);
		gridView.setAdapter(mAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Calendar calendar = Calendar.getInstance();
				CalendarManager.getInstance().getCalendarData(
						calendar.get(Calendar.YEAR), position);
				((CalenderMainActivity)getActivity()).fm.popBackStack();
			}
		});
		return v;
	}
	public void setData(ArrayList<Bitmap> mList1) {
		mAdapter.addAll(mList1);
	}

}
