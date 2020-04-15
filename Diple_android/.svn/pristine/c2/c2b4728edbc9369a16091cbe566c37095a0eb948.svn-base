package com.diple.diple.subview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.diple.diple.R;
import com.diple.diple.subview.TemaItemLayout.setTemaClick;

public class TemaViewAdapter extends BaseAdapter {
	
	Context mContext;
	ArrayList<Drawable> mList =new ArrayList<Drawable>();
	
	
	public TemaViewAdapter(Context context){
		mContext= context;
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_food));
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_movie));
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_cafe));
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_pup));
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_art));
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_shopping));
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_outsider));
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_highlitel));
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_descount));
		mList.add(mContext.getResources().getDrawable(R.drawable.button_selector_all));
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		TemaItemLayout view;
		if(convertView == null){
			view = new TemaItemLayout(mContext);
		}else{
			view = (TemaItemLayout)convertView;
		}
		view.setdata(mList.get(position),position);
		view.setTemaClick(new setTemaClick() {
			
			@Override
			public void setOnTemaClick(int temaNum) {
				mListener.setOnTemaClick(temaNum);
			}
		});
		return view;
	}

	public interface setADTemaClick{
		public void setOnTemaClick(int temaNum);
	}

	setADTemaClick mListener;
	
	public void setADTemaClick(setADTemaClick listener){
		mListener=listener;
	}
}
