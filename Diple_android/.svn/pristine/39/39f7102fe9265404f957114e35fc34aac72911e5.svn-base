package com.diple.diple;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Toast;

import com.diple.diple.loginsignup.LoginMainpageFragment;
import com.diple.diple.setting.LockActivity;
import com.diple.diple.subview.GoogleMapFragment;
import com.diple.diple.subview.SlidingMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

	ActionBar actionBar;
	private boolean isLogin = false;
	LoginMainpageFragment loginPageFragment = new LoginMainpageFragment();
	GoogleMapFragment gm;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.sliding_menu_frame);
		SlidingMenuFragment mindSliding = new SlidingMenuFragment();
		
		// 잠금 화면 등장시키는 화면
		if(LockActivity.getInstance().isPwExist){
			Intent lockIntent = new Intent(MainActivity.this, LockActivity.class);
			startActivity(lockIntent);
		}
		// 잠금 화면 끝
		
		actionBar = getSupportActionBar();
		actionBar.setTitle("");
		actionBar.setIcon(R.drawable.ham_bar);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		
		gm = new GoogleMapFragment();
/*여기서 부터  Sliding Menu 구현하기 위한 소스*/
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, gm).commit();
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.menu_container, mindSliding).commit();
		}
		SlidingMenu sm = getSlidingMenu();
		sm.setSlidingEnabled(false);
//		sm.setShadowWidthRes(R.dimen.shadow_width);
//		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//		sm.setMode(SlidingMenu.RIGHT);
//		sm.setShadowWidth(60);
//		sm.setShadowDrawable(R.drawable.shadow);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		
/*여기까지  Sliding Menu 구현하기 위한 소스*/
		
		// 여기서부터 login을 하도록 하는 fragment 첨부 분기점!
//		if(!isLogin){
//			getSupportFragmentManager().beginTransaction().add(R.id.container, loginPageFragment).commit();
//		}
		// 여기까지 login을 하도록 하는 fragment 첨부
		
		
	}
	Handler backKey = new Handler();
	Runnable backKeyPress = new Runnable() {
		public void run() {
			isCheckedBackKey = false;
		}
	};
	boolean isCheckedBackKey = false; 
	@Override
	public void onBackPressed() {
		if(gm.isOptionCheckBack){
			gm.searchAdapterDeleteAll();
			gm.setTemaVisiility();
			gm.drawer.close();
			gm.isOptionCheckBack = false;
		}
		else if(!isCheckedBackKey){
			gm.searchAdapterDeleteAll();
			gm.setTemaVisiility();
			isCheckedBackKey = true;
			Toast.makeText(this, "한번더 누르시면 종료 됩니다.", Toast.LENGTH_SHORT).show();
			backKey.postDelayed(backKeyPress, 2000);
		}else if(isCheckedBackKey){
			super.onBackPressed();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} 
		else if (id == android.R.id.home) {
			toggle();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void switchMenuOne() {
//		Fragment f = new MenuOneFragment(); //여기서 선택하여 넘어감
//		getSupportFragmentManager().beginTransaction()
//				.replace(R.id.container, f).addToBackStack(null).commit();
		hideSlidingMenu();
	}
	
	
	private void hideSlidingMenu() {
		mHandler.postDelayed(hidenMune, 50);
	}
	
	Handler mHandler = new Handler();
	
	Runnable hidenMune = new Runnable() {
		
		@Override
		public void run() {
			getSlidingMenu().showContent();
		}
	};
	
	// login이 되었을 때 실행되는 method
	public void succeedLogin(){
		isLogin = true;
		getSupportFragmentManager().beginTransaction().remove(loginPageFragment).commit();
	}
}
