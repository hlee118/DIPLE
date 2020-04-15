package com.diple.diple.setting;

import com.diple.diple.R;
import com.diple.diple.loginsignup.LoginSignupFragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class SettingActivity extends ActionBarActivity {
	
	ActionBar actionBar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting_view);
	    
	    actionBar = getSupportActionBar();
	    actionBar.setTitle("");
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.button_selector_backbtn);
	    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
	    
	    
	    if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SettingMainFragment()).commit();
        }
	}
	
	public void setLockSettingFragment(){
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.container, new LockSettingFragment());
		ft.addToBackStack("");
		ft.commit();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
