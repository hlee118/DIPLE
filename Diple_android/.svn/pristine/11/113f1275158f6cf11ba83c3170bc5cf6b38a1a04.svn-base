package com.diple.diple.recoded;

import java.util.ArrayList;

import com.diple.diple.severitem.Record;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RecodedDetailAdapter extends BaseAdapter {

	ArrayList<Record> mList;
	Context mContext;
	public RecodedDetailAdapter(Context context, int oldPosition){
		mContext = context;
		mList=RecordedPreviewAdapter.getInstance().reData.get(oldPosition).result;;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RecodedDetailItemFrameLayout view;
		if(convertView == null){
			view = new RecodedDetailItemFrameLayout(mContext,mList.get(position));
		}else{
			view = (RecodedDetailItemFrameLayout)convertView;
		}
		return view;
	}

}
