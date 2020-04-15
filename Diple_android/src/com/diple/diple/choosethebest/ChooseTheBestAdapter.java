package com.diple.diple.choosethebest;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.diple.diple.choosethebest.ChooseTheBestItemView.setDelListener;
import com.diple.diple.severitem.Result;
import com.diple.diple.severitem.ZzimResult;

public class ChooseTheBestAdapter extends BaseAdapter {
	
	Context context;
	ArrayList<Result> items = new ArrayList<Result>();
	
	public ChooseTheBestAdapter(Context context){
		this.context = context;
	}
	public void newAdd(ZzimResult result){
		items.clear();
		items.addAll(result.result);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Result getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ChooseTheBestItemView itemView;
		if (convertView == null) {
			itemView = new ChooseTheBestItemView(context);
			
		} else {
			itemView = (ChooseTheBestItemView)convertView;
		}
		itemView.setItemData(items.get(position),position);
		itemView.setDelListener(new setDelListener() {
			
			@Override
			public void setDel() {
				mListener.setDel();
			}
		});
		return itemView;
	}
	
	interface setDelAListener{
		public void setDel();
	}
	setDelAListener mListener;
	public void setDelAListener(setDelAListener Listener){
		mListener = Listener;
	}

}
