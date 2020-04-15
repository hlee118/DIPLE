package com.diple.diple.location_marker;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.diple.diple.R;
import com.diple.diple.severitem.Photo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public class StaggeredAdapter extends BaseAdapter {
	
	ArrayList<Photo> mPhoto;
	 Context mContext;
	 ImageLoader loader;
	 DisplayImageOptions options;
	public StaggeredAdapter(Context context,
			ArrayList<Photo> photo) {
		mContext = context;
		mPhoto=photo;
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.bin_camera)
		.showImageForEmptyUri(R.drawable.bin_camera)
		.showImageOnFail(R.drawable.bin_camera)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new BitmapDisplayer() {
			
			@Override
			public void display(Bitmap bitmap, ImageAware imageAware,
					LoadedFrom loadedFrom) {
//				Bitmap bm = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, filter);
				imageAware.setImageBitmap(bitmap);
			}
		})
		.build();

	}
	
	public void add(Photo photo){
		mPhoto.add(photo);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mPhoto.size();
	}

	@Override
	public Object getItem(int position) {
		return mPhoto.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView view;
		if(convertView == null){
			view = new ImageView(mContext);
//			view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//			view.setScaleType(ScaleType.FIT_XY);
		} else {
			view = (ImageView)convertView;
		}
		loader.displayImage(mPhoto.get(position).thumbnailImg, view, options);
//		view.setScaleY(ViewGroup.LayoutParams.MATCH_PARENT*(ViewGroup.LayoutParams.MATCH_PARENT/view.getScaleX()));
//		view.setScaleX(ViewGroup.LayoutParams.MATCH_PARENT);
		view.setScaleType(ImageView.ScaleType.CENTER_CROP);
		return view;
	}

}
