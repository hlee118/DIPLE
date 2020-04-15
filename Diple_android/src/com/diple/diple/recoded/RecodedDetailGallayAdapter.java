package com.diple.diple.recoded;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.diple.diple.R;
import com.diple.diple.severitem.Photo;
import com.nostra13.universalimageloader.core.ImageLoader;

public class RecodedDetailGallayAdapter extends BaseAdapter {
	ImageLoader loader;
	Context mContext;
	int width, height;
	ArrayList<Photo> item;
	public RecodedDetailGallayAdapter(Context context, ArrayList<Photo> photo) {
		loader = ImageLoader.getInstance();
		item = photo;
		mContext = context;
		width = (int)context.getResources().getDimension(R.dimen.gallery_image_width);
		height = (int)context.getResources().getDimension(R.dimen.gallery_image_height);
	}
	
	@Override
	public int getCount() {
		return item.size();
	}
	@Override
	public Photo getItem(int position) {
		return item.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
		} else {
			imageView = (ImageView)convertView;
		}
		loader.displayImage(item.get(position).thumbnailImg, imageView);
		imageView.setLayoutParams(new Gallery.LayoutParams(width, height));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		
		return imageView;
	}
	
}
