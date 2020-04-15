package com.diple.diple.checkin;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.diple.diple.R;
import com.diple.diple.location_marker.MarkerLocationViewActivity;
import com.diple.diple.loginsignup.ResultServerItem;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.google.android.gms.maps.model.LatLng;

public class CheckinPopupViewActivity extends Activity {
	public static final int REQUEST_CODE_CROP = 0;
	
	GridView imageGrid;
	EditText commentText;
	EditText nameText;
	CheckinPopupAdapter mAdapter;
	File mSavedFile;
	LatLng latlng;
	String name;
	String addr;
	String telno;
	String placeId;
	int what =0;
	int nowTogle;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.checkin_btn_click_view);
	    
	    Intent i = getIntent();
	    latlng = i.getParcelableExtra("latlng");
	    name = i.getStringExtra("name");
	    addr = i.getStringExtra("addr");
	    placeId = i.getStringExtra("placeId");
	    what = i.getIntExtra("id", 0);
	    
	    nameText = (EditText)findViewById(R.id.editPlaceName);
	    commentText = (EditText)findViewById(R.id.editComentText);
	    imageGrid = (GridView)findViewById(R.id.gridimageSelecter);
	    mAdapter = new CheckinPopupAdapter(this);
	    imageGrid.setAdapter(mAdapter);
	    
	    if(placeId == null){
	    	nameText.setVisibility(View.VISIBLE);
	    }
	    imageGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position == mAdapter.mList.size()-1){
					Intent photoPickerIntent = new Intent(
							Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					photoPickerIntent.setType("image/*");
					photoPickerIntent.putExtra("crop", "true");
					photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
					photoPickerIntent.putExtra("outputFormat",
							Bitmap.CompressFormat.JPEG.toString());				
					startActivityForResult(photoPickerIntent, REQUEST_CODE_CROP);
				}else{
					mAdapter.del(position);
				}
			}
		});
	    
	    final ToggleButton toggleBtn[] = {
	    		(ToggleButton)findViewById(R.id.btnCheckInFood),
	    		(ToggleButton)findViewById(R.id.btnCheckInMovie),
	    		(ToggleButton)findViewById(R.id.btnCheckInCafe),
	    		(ToggleButton)findViewById(R.id.btnCheckInPup),
	    		(ToggleButton)findViewById(R.id.btnCheckInArt),
	    		(ToggleButton)findViewById(R.id.btnCheckInShopping),
	    		(ToggleButton)findViewById(R.id.btnCheckInOutsider),
	    		(ToggleButton)findViewById(R.id.btnCheckInDiscount)
	    };
	    
	    toggleBtn[0].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0; i<toggleBtn.length; i++){
					if(i == 0){
						nowTogle =0;
						continue;
					} else if(toggleBtn[i].isChecked()){
						toggleBtn[i].setChecked(false);
					}
				}
			}
		});
	    
	    toggleBtn[1].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0; i<toggleBtn.length; i++){
					if(i == 1){
						nowTogle =1;
						continue;
					} else if(toggleBtn[i].isChecked()){
						toggleBtn[i].setChecked(false);
					}
				}
			}
		});
	    
	    toggleBtn[2].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0; i<toggleBtn.length; i++){
					if(i == 2){
						nowTogle =2;
						continue;
					} else if(toggleBtn[i].isChecked()){
						toggleBtn[i].setChecked(false);
					}
				}
			}
		});
	    
	    toggleBtn[3].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0; i<toggleBtn.length; i++){
					if(i == 3){
						nowTogle =3;
						continue;
					} else if(toggleBtn[i].isChecked()){
						toggleBtn[i].setChecked(false);
					}
				}
			}
		});
	    
	    toggleBtn[4].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0; i<toggleBtn.length; i++){
					if(i == 4){
						nowTogle =5;
						continue;
					} else if(toggleBtn[i].isChecked()){
						toggleBtn[i].setChecked(false);
					}
				}
			}
		});
	    
	    toggleBtn[5].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0; i<toggleBtn.length; i++){
					if(i == 5){
						nowTogle =6;
						continue;
					} else if(toggleBtn[i].isChecked()){
						toggleBtn[i].setChecked(false);
					}
				}
			}
		});
	    
	    toggleBtn[6].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0; i<toggleBtn.length; i++){
					if(i == 6){
						nowTogle =7;
						continue;
					} else if(toggleBtn[i].isChecked()){
						toggleBtn[i].setChecked(false);
					}
				}
			}
		});
	    
	    toggleBtn[7].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0; i<toggleBtn.length; i++){
					if(i == 7){
						nowTogle =8;
						continue;
					} else if(toggleBtn[i].isChecked()){
						toggleBtn[i].setChecked(false);
					}
				}
			}
		});
	    
	    
	    
	    Button btn = (Button)findViewById(R.id.btnComplete);
	    btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String rName;
				if(placeId == null){
					rName = nameText.getText().toString();
				}else{
					rName = name;
				}
				NetworkModel.getInstance().getCheckIn(CheckinPopupViewActivity.this, latlng.latitude, latlng.longitude, rName, placeId, addr, telno, commentText.getText().toString(), nowTogle , mAdapter.mList, new OnNetworkResult<ResultServerItem>() {

					@Override
					public void onSuccess(ResultServerItem result) {
						Toast.makeText(CheckinPopupViewActivity.this, "체크인 완료", Toast.LENGTH_SHORT).show();
						if(result.resultCode != -1){
							if(what == 1){
								Intent i = new Intent(CheckinPopupViewActivity.this, MarkerLocationViewActivity.class);
								i.putExtra("loca", placeId);
								startActivity(i);
							}
							finish();
						}
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(CheckinPopupViewActivity.this, "체크인 실패", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	   
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_CROP && resultCode == RESULT_OK) {
			CheckinItem item = new CheckinItem();
			BitmapFactory.Options op = new BitmapFactory.Options();
			op.inSampleSize = 5;
			op.inBitmap = BitmapFactory.decodeFile(mSavedFile.getAbsolutePath(), op);
			item.photoFile = mSavedFile;
			item.photo = op.inBitmap;
			item.btnR = CheckinPopupItemLayout.BTN_MINUS;
			mAdapter.add(item);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),"temp_" + System.currentTimeMillis()/1000);
		return Uri.fromFile(mSavedFile);
	}
}