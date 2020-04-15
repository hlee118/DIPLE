package com.diple.diple.photodetail;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.recoded.RecodedDetailItemFrameLayout;
import com.diple.diple.severitem.Photo;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PhotoDetailActivity extends ActionBarActivity {
	
	ImageLoader loader;
	ActionBar actionBar;
	Uri url;
	ImageView imageView;
	TextView textView;
	GestureDetector mDetector;
	ArrayList<Photo> data;
	float x = 0;
	float y = 0;
	int position=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.photo_detail_page);
	    actionBar = getSupportActionBar();
	    actionBar.setTitle("");
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.btn_back);
	    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
	    
	    loader = ImageLoader.getInstance();
	    getActionBar().hide();
	    Intent i = getIntent();
	    data = i.getParcelableArrayListExtra(RecodedDetailItemFrameLayout.INTENT_PARAM_PHOTO);
	    position= i.getIntExtra("i", 0);
	    imageView = (ImageView)findViewById(R.id.imageView1);
	    textView = (TextView)findViewById(R.id.textView1);
	    loader.displayImage(data.get(position).originalImg, imageView);

		mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDown(MotionEvent e) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return super.onSingleTapUp(e);
			}
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				x=e1.getX();
				y=e1.getY();
				if(x > e2.getX() && Math.abs(x - e2.getX()) > Math.abs(y - e2.getY())){
					NextImage();
				}else if(x < e2.getX() && Math.abs(x - e2.getX()) > Math.abs(y - e2.getY())){
					BeforImage();
				}
				return super.onFling(e1, e2, velocityX, velocityY);
			}
		});
		
		imageView.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return mDetector.onTouchEvent(event);
			}
		});
	    
	    // TODO Auto-generated method stub
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home ){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	public void NextImage(){
		position++;
		if(position == data.size()){
			Toast.makeText(this, "마지막 이미지 입니다.", Toast.LENGTH_SHORT).show();
			position--;
		}else{
			loader.displayImage(data.get(position).originalImg, imageView);
		}
	}
	
	public void BeforImage(){
		position--;
		if(position < 0){
			Toast.makeText(this, "처음 이미지 입니다.", Toast.LENGTH_SHORT).show();
			position++;
		}else{
			loader.displayImage(data.get(position).originalImg, imageView);
		}
	}
}
