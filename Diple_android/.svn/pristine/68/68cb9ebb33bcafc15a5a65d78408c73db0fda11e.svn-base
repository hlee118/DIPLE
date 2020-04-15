package com.diple.diple.location_marker;

import java.util.Collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.location_marker.MarkerLocationAdapter.StaggerdListener;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.diple.diple.severitem.LocationResult;
import com.google.android.gms.maps.model.LatLng;
import com.kakao.KakaoLink;
import com.kakao.KakaoParameterException;
import com.kakao.KakaoTalkLinkMessageBuilder;

public class MarkerLocationViewActivity extends ActionBarActivity {

	ActionBar actionBar;
	MarkerLocationAdapter mAdapter;
	ListView mainList;
	EditText locationEditText;
	LatLng markerLatLng;
	LocationResult data;
	KakaoLink kakaoLink;

	// String[] items = { "페이스북", "카카오톡" };
	// ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marker_location_contant_view);
		Intent i = getIntent();
		try {
			kakaoLink = KakaoLink.getKakaoLink();

		} catch (KakaoParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actionBar = getSupportActionBar();
		actionBar.setTitle("");
		actionBar.setIcon(R.drawable.btn_back);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.actionbar_background));
		mainList = (ListView) findViewById(R.id.listComentView);

		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_spinner_item, items);
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		NetworkModel.getInstance().getLocation(this, i.getStringExtra("loca"),
				new OnNetworkResult<LocationResult>() {

					@Override
					public void onSuccess(LocationResult result) {
						data = result;
						mAdapter = new MarkerLocationAdapter(
								MarkerLocationViewActivity.this,
								getSupportFragmentManager(), result);
						mainList.setAdapter(mAdapter);
						mAdapter.setOnStartStagger(new StaggerdListener() {
							@Override
							public void startStaggerd() {
								Intent i = new Intent(
										MarkerLocationViewActivity.this,
										StaggerdImageViewMain.class);
								i.putExtra(
										StaggerdImageViewMain.STAGGERED_PHOTO_LIST,
										data.photo);
								startActivity(i);
							}
						});
						// actionBar.setListNavigationCallbacks(adapter,
						// new OnNavigationListener() {
						//
						// @Override
						// public boolean onNavigationItemSelected(
						// int position, long arg1) {
						// List<String> permission =
						// FaceData.getInstance().getSession()
						// .getPermissions();
						// if (position == 0) {
						// if (!isSubsetOf(PERMISSIONS, permission)) {
						// FaceData.getInstance().getSession().requestNewPublishPermissions(new
						// Session.NewPermissionsRequest(
						// MarkerLocationViewActivity.this,
						// PERMISSIONS));
						// return false;
						// }
						// Request.Callback callback = new Request.Callback() {
						// public void onCompleted(
						// Response response) {
						// JSONObject graphResponse = response
						// .getGraphObject()
						// .getInnerJSONObject();
						// String postId = null;
						// try {
						// postId = graphResponse
						// .getString("id");
						// } catch (JSONException e) {
						//
						// }
						// FacebookRequestError error = response
						// .getError();
						// if (error != null) {
						// Toast.makeText(
						// MarkerLocationViewActivity.this,
						// error.getErrorMessage(),
						// Toast.LENGTH_SHORT)
						// .show();
						// } else {
						// Toast.makeText(
						// MarkerLocationViewActivity.this,
						// postId,
						// Toast.LENGTH_LONG)
						// .show();
						// }
						// }
						// };
						//
						// Bundle postParams = new Bundle();
						// postParams.putString("message",
						// "facebook test message");
						// postParams
						// .putString("name",
						// "Education Test for Android");
						// postParams.putString("caption",
						// "Test facebook capture.");
						// postParams
						// .putString(
						// "description",
						// data.comment.get(0).comment);
						// // postParams
						// // .putString("link",
						// // "https://developers.facebook.com/android");
						// postParams
						// .putString(
						// "picture",
						// data.photo.get(0).thumbnailImg);
						//
						// Request request = new Request(
						// FaceData.getInstance()
						// .getSession(),
						// "me/feed", postParams,
						// HttpMethod.POST, FaceData
						// .getInstance()
						// .getCallback());
						// request.executeAsync();
						// // RequestAsyncTask task = new
						// // RequestAsyncTask(
						// // request);
						// // task.execute();
						//
						// } else {
						// KakaoTalkLinkMessageBuilder
						// kakaoTalkLinkMessageBuilder = kakaoLink
						// .createKakaoTalkLinkMessageBuilder();
						// try {
						// kakaoTalkLinkMessageBuilder.addText(data.locationInfo
						// .get(0).placeName
						// + "\n"
						// + data.comment.get(0).comment);
						// kakaoTalkLinkMessageBuilder.addImage(
						// data.photo.get(0).thumbnailImg,
						// 100, 100);
						// kakaoLink.sendMessage(
						// kakaoTalkLinkMessageBuilder
						// .build(),
						// MarkerLocationViewActivity.this);
						// } catch (KakaoParameterException e) {
						// // TODO Auto-generated catch
						// // block
						// e.printStackTrace();
						// }
						// }
						// return false;
						// }
						// });
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(MarkerLocationViewActivity.this,
								"상세정보 오류", Toast.LENGTH_SHORT).show();
					}
				});

		// actionBar.setCustomView(R.layout.custom_title);
		// actionBar.setDisplayShowCustomEnabled(true);
		// actionBar.setDisplayShowTitleEnabled(true);
		// actionBar.setTitle("");
		// actionBar.setDisplayHomeAsUpEnabled(true);
		// actionBar.setDisplayShowHomeEnabled(true);
		// actionBar.setHomeButtonEnabled(true);

		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_spinner_item, items);
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// actionBar.setListNavigationCallbacks(adapter, new
		// OnNavigationListener() {
		//
		// @Override
		// public boolean onNavigationItemSelected(int position, long id) {
		// Toast.makeText(MainActivity.this, "selected : " + items[position],
		// Toast.LENGTH_SHORT).show();
		// return true;
		// }
		// });

	}

	ShareActionProvider provider;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.maker_contant_menu, menu);
		MenuItem item = menu.findItem(R.id.makercontant);
		item.setCheckable(true);

		provider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
		// provider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);

		return true;
	}

	// public Intent shareIntent () {
	// Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
	// shareIntent.setType("text/plain");
	// shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
	// "Content to share");
	// PackageManager pm = getPackageManager();
	// List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent,
	// 0);
	// for (final ResolveInfo app : activityList) {
	// if ((app.activityInfo.name).contains("facebook")) {
	// final ActivityInfo activity = app.activityInfo;
	// final ComponentName name = new
	// ComponentName(activity.applicationInfo.packageName, activity.name);
	// shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
	// shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
	// Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
	// shareIntent.setComponent(name);
	// startActivity(shareIntent);
	// break;
	// }else if ((app.activityInfo.name).contains("kakaotalk")) {
	// final ActivityInfo activity = app.activityInfo;
	// final ComponentName name = new
	// ComponentName(activity.applicationInfo.packageName, activity.name);
	// shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
	// shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
	// Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
	// shareIntent.setComponent(name);
	// startActivity(shareIntent);
	// break;
	// }
	// }
	// return shareIntent;
	// }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == android.R.id.home) {
			finish();
			return true;
		} else if (id == R.id.makercontant) {
			KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = kakaoLink
					.createKakaoTalkLinkMessageBuilder();
			try {
				kakaoTalkLinkMessageBuilder.addText("데이트를 신청합니다.!!!\n"
						+ "장소 : " + data.locationInfo.get(0).placeName);
				if (data.photo.size() != 0) {
					kakaoTalkLinkMessageBuilder.addImage(
							data.photo.get(0).thumbnailImg, 200, 200);
				}
				kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder.build(),
						MarkerLocationViewActivity.this);
			} catch (KakaoParameterException e) {
				// TODO Auto-generated catch
				// block
				e.printStackTrace();
			}
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onNewIntent(Intent intent) {
		NetworkModel.getInstance().getLocation(this, intent.getStringExtra("loca"), new OnNetworkResult<LocationResult>() {
			
			@Override
			public void onSuccess(LocationResult result) {
				data = result;
				mAdapter.add(result);
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
		super.onNewIntent(intent);
	}
}
