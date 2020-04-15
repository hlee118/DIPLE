package com.diple.diple.recoded;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;

import com.diple.diple.R;
import com.diple.diple.severitem.Record;

public class RecodedDetailActivity extends ActionBarActivity {


	RecodedDetailWheelAdapter mAdapter;
	Gallery gallery;
	FragmentManager fm;
	int oldPosition;
	ActionBar actionBar;
	ArrayList<Record> data;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.recoded_dedail_main);
	    Intent i = getIntent();
	    int position = i.getIntExtra("put", 0);
	    data = RecordedPreviewAdapter.getInstance().reData.get(position).result;
	    
	    actionBar = getSupportActionBar();
	    actionBar.setTitle("");
		actionBar.setIcon(R.drawable.btn_back);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
	    
	    gallery = (Gallery)findViewById(R.id.wheelBar);
	    mAdapter = new RecodedDetailWheelAdapter(this);
	    gallery.setAdapter(mAdapter);
	    fm = getSupportFragmentManager();
	    FragmentTransaction ft = fm.beginTransaction();
	    ft.add(R.id.fragmentCon, new RecodedDetailFragment()).commit();
	    
	    gallery.setSelection(position);
	    gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				oldPosition = position;
				FragmentTransaction ft = fm.beginTransaction();
				ft.replace(R.id.fragmentCon, new RecodedDetailFragment())
				.commit();
//				if (oldPosition < position) {
//					oldPosition = position;
//					FragmentTransaction ft = fm.beginTransaction();
//					ft.setCustomAnimations(R.anim.set2_anim, R.anim.set_anim);
//					ft.replace(R.id.fragmentCon, new RecodedDetailFragment())
//							.commit();
//				}
//				else if (oldPosition > position){
//					oldPosition = position;
//					FragmentTransaction ft = fm.beginTransaction();
//					ft.setCustomAnimations(R.anim.set2_2_anim, R.anim.set2_1_anim);
//					ft.replace(R.id.fragmentCon, new RecodedDetailFragment())
//							.commit();
//				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.recoded, menu);
//		MenuItem item = menu.findItem(R.id.menuBefore);
//		item.setCheckable(true);
//		item = menu.findItem(R.id.menuBefore);
//		item.setCheckable(true);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
