package com.diple.diple.location_marker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.diple.diple.R;
import com.diple.diple.severitem.Photo;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PhotoPagerImageFragment extends Fragment {

	ImageLoader imageLoader;
	Photo photo;

	public PhotoPagerImageFragment(Photo mphoto) {
		this.photo = mphoto;
		
	};

	ImageView photoImage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.marker_location_pager_view,
				container, false);
		imageLoader = ImageLoader.getInstance();
		photoImage = (ImageView) v.findViewById(R.id.photoImageView);
		if(photo == null) {
			photoImage.setImageDrawable(getResources().getDrawable(R.drawable.bin_camera));
		}else if(photo != null){
			imageLoader.displayImage(photo.thumbnailImg, photoImage);
			photoImage.setScaleType(ScaleType.CENTER_CROP);
		}
		return v;
	}
}
