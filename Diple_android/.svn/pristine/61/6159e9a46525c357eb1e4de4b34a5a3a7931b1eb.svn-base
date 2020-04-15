package com.diple.diple.checkin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.checkinitem.MyhereLocation;
import com.diple.diple.checkinitem.TMapPOIResult;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.google.android.gms.maps.model.LatLng;

public class CheckableListViewActivity extends Activity {

	ListView checkableList;

	// 더미 어뎁터
	CheckInListSearchAdapter mAdapter;
	LatLng latLng;
	ImageView imgCloseCheckInPage;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkable_list_view);
		Intent i = getIntent();
		latLng = i.getExtras().getParcelable("latlng");
		mAdapter = new CheckInListSearchAdapter(this);
		checkableList = (ListView) findViewById(R.id.listCheckable);
		
		imgCloseCheckInPage = (ImageView)findViewById(R.id.imgCloseCheckInPage);
		imgCloseCheckInPage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		// NetworkModel.getInstance().getHereLocation(this, latLng.longitude,
		// latLng.latitude, new OnNetworkResult<MyhereLocation>() {
		//
		// @Override
		// public void onSuccess(MyhereLocation result) {
		// // mAdapter.addAll(result);
		//
		// NetworkModel.getInstance().getPOISearch(
		// CheckableListViewActivity.this,
		// result.addressInfo.legalDong, 1, "R",
		// latLng.longitude, latLng.latitude,
		// new OnNetworkResult<TMapPOIResult>() {
		//
		// @Override
		// public void onSuccess(TMapPOIResult result) {
		// mAdapter.addAll(result);
		// }
		//
		// @Override
		// public void onFail(int code) {
		// Toast.makeText(
		// CheckableListViewActivity.this,
		// "결과없음", Toast.LENGTH_SHORT)
		// .show();
		// }
		// });
		// }
		//
		// @Override
		// public void onFail(int code) {
		// // Toast.makeText(CheckableListViewActivity.this, "결과없음",
		// // Toast.LENGTH_SHORT).show();
		// }
		// });

		checkableList.setAdapter(mAdapter);

		checkableList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(CheckableListViewActivity.this,
						CheckinPopupViewActivity.class);
				if (position == 0) {
					i.putExtra("latlng", latLng);
					i.putExtra("name", "");
					i.putExtra("addr", "");
					startActivity(i);
					finish();
				} else {
					LatLng latLng2 = new LatLng(Double.valueOf(mAdapter.mList
							.get(position).latitude), Double
							.valueOf(mAdapter.mList.get(position).longitude));
					// latLng.latitude =
					// (double)Double.valueOf(mAdapter.mList.get(position).frontLat);
					i.putExtra("latlng", latLng2);
					i.putExtra("name", mAdapter.mList.get(position).name);
					i.putExtra("addr", mAdapter.mList.get(position).addess);
					i.putExtra("placeId", mAdapter.mList.get(position).placeId);
					startActivity(i);
					finish();
				}
			}

		});

		// TODO Auto-generated method stub
	}
}
