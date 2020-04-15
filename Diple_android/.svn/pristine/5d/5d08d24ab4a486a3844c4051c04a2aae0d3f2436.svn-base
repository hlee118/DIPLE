package com.diple.diple.location_marker;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.diple.diple.R;
import com.diple.diple.location_marker.PhotoPagerAdapter.startStaggerdListener;
import com.diple.diple.severitem.Photo;

public class PhotoPagerFragmentMain extends FrameLayout  {
	Context mContext;
	FragmentManager mFm;
	
	public PhotoPagerFragmentMain(Context context,FragmentManager fm,ArrayList<Photo> item) {
		super(context);
		mContext = context;
		mFm = fm;
		photo = item;
		init();
	}

	ArrayList<Photo> photo;
	
	ViewPager pager;
	PhotoPagerAdapter mPagerAdapter;
	GestureDetector mDetector;
	private void init() {
		
		LayoutInflater.from(mContext).inflate(R.layout.marker_location_photo,this);
		pager = (ViewPager)findViewById(R.id.pager);
		
		
		mPagerAdapter = new PhotoPagerAdapter(mFm, mContext,pager, photo);
		
		pager.setAdapter(mPagerAdapter);
		
		mDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDown(MotionEvent e) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				mStaggerListener.startStaggerd();
				return super.onSingleTapUp(e);
			}
		});
		
		pager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return mDetector.onTouchEvent(event);
			}
		});
		
		mPagerAdapter.setOnStartStagger(new startStaggerdListener() {
			
			@Override
			public void startStaggerd() {
				// TODO Auto-generated method stub
				pager.setCurrentItem(mPagerAdapter.MAX_PHOTO_COUNT-2);
				startStaggerd.sendMessageDelayed(
						startStaggerd.obtainMessage(0),
						200);
			}
		});
		
	}
	
	public interface stagerPagerListener{
		public void startStaggerd();
	}
	stagerPagerListener mStaggerListener;
	
	public void getStartStaggerd(stagerPagerListener listener){
		mStaggerListener=listener;
	}

	Handler startStaggerd = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			mStaggerListener.startStaggerd();
			super.handleMessage(msg);
		}
	};


	
}