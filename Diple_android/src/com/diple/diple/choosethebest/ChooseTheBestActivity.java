package com.diple.diple.choosethebest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.choosethebest.ChooseTheBestAdapter.setDelAListener;
import com.diple.diple.location_marker.MarkerLocationViewActivity;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.diple.diple.severitem.ZzimResult;

public class ChooseTheBestActivity extends ActionBarActivity {
	
	
	
	ActionBar actionBar;
	ListView listView;
	ChooseTheBestAdapter chooseTheBestAdapter;
	ZzimResult data;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.choosethebest_layout);
	   
	    actionBar = getSupportActionBar();
	    actionBar.setTitle("");
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.button_selector_backbtn);
	    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
	    
	    listView = (ListView)findViewById(R.id.chooseTheBestList);
	    chooseTheBestAdapter = new ChooseTheBestAdapter(ChooseTheBestActivity.this);
	    listView.setAdapter(chooseTheBestAdapter);
	    
	    
	    
	    listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				Intent i = new Intent(ChooseTheBestActivity.this, MarkerLocationViewActivity.class);
				i.putExtra("loca", data.result.get(position).placeId);
				startActivity(i);
			}
		});
	    NetworkModel.getInstance().zzim(this, new OnNetworkResult<ZzimResult>() {

			@Override
			public void onSuccess(ZzimResult result) {
				data = result;
				chooseTheBestAdapter.newAdd(result);
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(ChooseTheBestActivity.this, "찜 불러오기 실패", Toast.LENGTH_SHORT).show();
			}
		});
	    chooseTheBestAdapter.setDelAListener(new setDelAListener() {
			
			@Override
			public void setDel() {
				NetworkModel.getInstance().zzim(ChooseTheBestActivity.this, new OnNetworkResult<ZzimResult>() {

					@Override
					public void onSuccess(ZzimResult result) {
						data = result;
						chooseTheBestAdapter.newAdd(result);
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(ChooseTheBestActivity.this, "찜 불러오기 실패", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.main, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
