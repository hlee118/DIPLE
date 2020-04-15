package com.diple.diple.calender;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.diple.diple.recoded.RecordedPreViewActivity;
import com.diple.diple.recoded.RecordedPreviewAdapter;
import com.diple.diple.severitem.RecordServerResult;

public class CalenderMainActivity extends ActionBarActivity {

	ActionBar actionBar;
	FragmentManager fm;
	LinearLayout layout;

	MultyCalenderFragment mc;
	SingleCalenderFragment sc;

	Handler myhand = new Handler();
	ArrayList<Bitmap> list = new ArrayList<Bitmap>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NetworkModel.getInstance().getRecord(CalenderMainActivity.this, new OnNetworkResult<RecordServerResult>() {
			
			@Override
			public void onSuccess(RecordServerResult result) {
					RecordedPreviewAdapter.getInstance().reData.clear();
					RecordedPreviewAdapter.getInstance().setData(result);
					myhand.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							sc.setCapture();
						}
					},100);
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				Toast.makeText(CalenderMainActivity.this , "레코드 결과 없음", Toast.LENGTH_SHORT).show();
			}
		});
		actionBar = getSupportActionBar();
		actionBar.setTitle("");
		actionBar.setIcon(R.drawable.calender_all);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.actionbar_background));
		
		
		mc = new MultyCalenderFragment();
		sc = new SingleCalenderFragment();
		this.layout = sc.layout;
		fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.container, sc);
		ft.commit();
		
	}

	boolean isfullBackStack = false;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home && !isfullBackStack) {
			// myhand.post(capture);
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.container, mc);
			ft.addToBackStack("backstack");
			ft.commit();
			isfullBackStack = true;
//			Calendar calendar = Calendar.getInstance();
//			CalendarManager.getInstance().getCalendarData(
//					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
			return true;
		}
		if (id == android.R.id.home && isfullBackStack) {
			fm.popBackStack();
			isfullBackStack = false;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (fm.getBackStackEntryCount() > 0) {
			fm.popBackStack();
			isfullBackStack = false;
		} else {
			super.onBackPressed();
		}
	}
	

}
