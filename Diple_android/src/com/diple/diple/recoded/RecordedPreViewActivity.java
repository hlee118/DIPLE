package com.diple.diple.recoded;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.diple.diple.severitem.RecordServerResult;

public class RecordedPreViewActivity extends ActionBarActivity {
	
	public static final String KEY_CURRENT_TAB = "currentTab";
	
	ActionBar actionBar;
	ViewPager pager;
	FragmentTabHost tabHost;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    NetworkModel.getInstance().getRecord(RecordedPreViewActivity.this, new OnNetworkResult<RecordServerResult>() {
			
			@Override
			public void onSuccess(RecordServerResult result) {
					RecordedPreviewAdapter.getInstance().reData.clear();
					RecordedPreviewAdapter.getInstance().setData(result);
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				Toast.makeText(RecordedPreViewActivity.this , "레코드 결과 없음", Toast.LENGTH_SHORT).show();
			}
		});
	    setContentView(R.layout.recorded_preview_layout);
		RecordedPreviewAdapter.getInstance().setContext(this);
	    actionBar = getSupportActionBar();
	    actionBar.setTitle("");
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.btn_back);
	    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
	    
	    tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		ImageView tabIndicatorViewAll = new ImageView(this);
		tabIndicatorViewAll.setImageResource(R.drawable.recorded_tab_setector_all);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(tabIndicatorViewAll), RecordedPreviewTemaFragmnet.class, null);
		ImageView tabIndicatorViewPic = new ImageView(this);
		tabIndicatorViewPic.setImageResource(R.drawable.recorded_tab_setector_pic);
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(tabIndicatorViewPic), RecordedPreviewAllFragment.class, null);
		ImageView tabIndicatorViewComment = new ImageView(this);
		tabIndicatorViewComment.setImageResource(R.drawable.recorded_tab_setector_comment);
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(tabIndicatorViewComment), RecordedPreviewCommentFragment.class, null);
		ImageView tabIndicatorViewCalendar = new ImageView(this);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals("tab1")) {
					
				} else if (tabId.equals("tab2")) {
					
				} else if (tabId.equals("tab3")) {
					
				} else if (tabId.equals("tab4")) {
					
				}
			}
		});
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home ){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
