package com.diple.diple.recoded;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.diple.diple.R;
import com.diple.diple.severitem.Record;

public class RecodedDetailWheelAdapter extends BaseAdapter {
	Context mContext;
	ArrayList<String> mList = new ArrayList<String>();

	public RecodedDetailWheelAdapter(Context context) {
		mContext = context;
		for(int i =0; i< RecordedPreviewAdapter.getInstance().date.length  ; i++){
			mList.add(RecordedPreviewAdapter.getInstance().date[i]);
		}
	}

	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public String getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RecodedWheelFrameLayout View;
		if (convertView == null) {
			View = new RecodedWheelFrameLayout(mContext,mList.get(position));
		} else {
			View = (RecodedWheelFrameLayout) convertView;
		}
		View.setLayoutParams(new Gallery.LayoutParams((int) mContext
				.getResources().getDimension(R.dimen.While_image_width),
				(int) mContext.getResources().getDimension(
						R.dimen.While_image_height)));
		return View;
	}

}
