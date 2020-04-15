package com.diple.diple.location_marker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.diple.diple.severitem.Photo;

public class PhotoPagerAdapter extends FragmentPagerAdapter  implements ViewPager.OnPageChangeListener{
	Context mContext;
	private final ViewPager mViewPager;
	ArrayList<Photo> mLphoto;
	
	public PhotoPagerAdapter(FragmentManager fm, Context context,ViewPager pager,ArrayList<Photo> photo) {
		super(fm);
		mContext = context;
		mViewPager = pager;
		mLphoto = photo;
		if(mLphoto.size() < 7 ){
		MAX_PHOTO_COUNT = mLphoto.size()+1;
		}else{
			MAX_PHOTO_COUNT =7;
		}
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
        
	}
	public int MAX_PHOTO_COUNT ;

	public interface startStaggerdListener {
		public void startStaggerd();
	}

	startStaggerdListener staggerdListener;

	public void setOnStartStagger(startStaggerdListener listener) {
		staggerdListener = listener;
	}


	PhotoPagerImageFragment a;

	@Override
	public Fragment getItem(int position) {
		if(position < MAX_PHOTO_COUNT-1){
			return new PhotoPagerImageFragment(mLphoto.get(position));
		}else if(position == MAX_PHOTO_COUNT-1){
			return new PhotoPagerImageFragment(null);
		}
		else{
			return new PhotoPagerImageFragment(null);
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return MAX_PHOTO_COUNT;
	}

	@Override
	public void onPageScrollStateChanged(int position) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		if(position==MAX_PHOTO_COUNT-1){
			staggerdListener.startStaggerd();
		}
	}

}
