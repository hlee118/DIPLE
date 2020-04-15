package com.diple.diple.subview;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.calender.CalenderMainActivity;
import com.diple.diple.choosethebest.ChooseTheBestActivity;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.diple.diple.recoded.RecordedPreViewActivity;
import com.diple.diple.setting.SettingActivity;
import com.diple.diple.severitem.ZzimResult;
import com.diple.diple.severitem.userServerData;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SlidingMenuFragment extends Fragment {

	public static final int REQUEST_CODE_PROFILE_CHANGE_ACTIVITY = 0;
	public EditText editTextCouplePageMemo;
	LinearLayout memoLayout;
	public ImageView imgMyProfile;
	public TextView textMyName;
	public TextView textDateName;
	public TextView textDateDays;
	int year = 2013;
	int month = 7; // 8월
	int day = 27;
	userServerData data1;
	public ImageLoader loader;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.sling_menu_fragment, container,
				false);
		NetworkModel.getInstance().user(getActivity(),
				new OnNetworkResult<userServerData>() {

					@Override
					public void onSuccess(userServerData result) {
						data1 = result;
						loader.displayImage(data1.result.imgUrl, imgMyProfile);
						textMyName.setText(data1.result.coupleName);
					}

					@Override
					public void onFail(int code) {
					}
				});
		loader = ImageLoader.getInstance();
		imgMyProfile = (ImageView) v.findViewById(R.id.imgMyProfile);
		textMyName = (TextView) v.findViewById(R.id.textMyName);
		textDateName = (TextView) v.findViewById(R.id.textDateName);
		textDateDays = (TextView) v.findViewById(R.id.textDateDays);
		textDateDays.setText("" + getDateDays(year, month, day));
		editTextCouplePageMemo = (EditText) v
				.findViewById(R.id.editTextCouplePageMemo);
		memoLayout = (LinearLayout) v.findViewById(R.id.couplepageMemoLayout);
		// loader.displayImage(UserData.getInstance().data.result.get(0).imgUrl,
		// imgMyProfile);
		// textMyName.setText(UserData.getInstance().data.result.get(0).coupleName);
		// textMyName.setText(UserData.getInstance().data.result.get(0).coupleName);
		final ImageView imgWriteMemo = (ImageView) v
				.findViewById(R.id.imgWriteMemo);
		imgWriteMemo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				imgWriteMemo.setVisibility(View.GONE);
				editTextCouplePageMemo.setText(data1.result.comment);
				memoLayout.setVisibility(View.VISIBLE);
			}
		});

		editTextCouplePageMemo
				.setOnFocusChangeListener(new OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if (!hasFocus) {
							imgWriteMemo.setVisibility(View.VISIBLE);
							NetworkModel
									.getInstance()
									.userUpd(
											getActivity(),
											data1.result.coupleName,
											editTextCouplePageMemo.getText()
													.toString(),
											null,
											new OnNetworkResult<userServerData>() {

												@Override
												public void onSuccess(
														userServerData result) {
													data1 = result;
													loader.displayImage(
															data1.result.imgUrl,
															imgMyProfile);
													textMyName
															.setText(data1.result.coupleName);
													editTextCouplePageMemo
															.setText(data1.result.comment);
													Toast.makeText(
															getActivity(),
															"변경 완료",
															Toast.LENGTH_SHORT)
															.show();
												}

												@Override
												public void onFail(int code) {
													// TODO Auto-generated
													// method stub

												}
											});
							memoLayout.setVisibility(View.GONE);
						}
					}
				});

		Button btn = (Button) v.findViewById(R.id.btnMemoClose);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				imgWriteMemo.setVisibility(View.VISIBLE);
				NetworkModel.getInstance().userUpd(getActivity(),
						data1.result.coupleName,
						editTextCouplePageMemo.getText().toString(), null,
						new OnNetworkResult<userServerData>() {

							@Override
							public void onSuccess(userServerData result) {
								data1 = result;
								loader.displayImage(data1.result.imgUrl,
										imgMyProfile);
								textMyName.setText(data1.result.coupleName);
								editTextCouplePageMemo
										.setText(data1.result.comment);
								Toast.makeText(getActivity(), "변경 완료",
										Toast.LENGTH_SHORT).show();
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(getActivity(), "변경 실패",
										Toast.LENGTH_SHORT).show();
							}
						});
				memoLayout.setVisibility(View.GONE);
			}
		});

		btn = (Button) v.findViewById(R.id.btnExitCouplePage);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((SlidingFragmentActivity) getActivity()).toggle();
			}
		});

		imgMyProfile.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(),
						ProfileChangeActivity.class);
				i.putExtra(ProfileChangeActivity.PARAM_NICKNAME, data1);
				i.putExtra(ProfileChangeActivity.RESULT_DATENAME, textDateName
						.getText().toString());
				i.putExtra(ProfileChangeActivity.RESULT_DATEDAYS_YEAR, year);
				i.putExtra(ProfileChangeActivity.RESULT_DATEDAYS_MONTH, month);
				i.putExtra(ProfileChangeActivity.RESULT_DATEDAYS_DAY, day);
				startActivityForResult(i, REQUEST_CODE_PROFILE_CHANGE_ACTIVITY);
			}
		});

		btn = (Button) v.findViewById(R.id.btnRecoded);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(),
						RecordedPreViewActivity.class);
				startActivity(i);
			}
		});

		// 찜목록 이동 버튼
		btn = (Button) v.findViewById(R.id.btnChooseTheBest);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent((getActivity()),
						ChooseTheBestActivity.class);
				startActivity(i);
			}
		});
		// 찜목록 이동 버튼 끝

		btn = (Button) v.findViewById(R.id.btnCalendar);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), CalenderMainActivity.class);
				startActivity(i);
			}
		});

		btn = (Button) v.findViewById(R.id.btnSetting);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SettingActivity.class);
				startActivity(i);
			}
		});
		return v;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_PROFILE_CHANGE_ACTIVITY
				&& resultCode == Activity.RESULT_OK) {
			data1 = data
					.getParcelableExtra(ProfileChangeActivity.RESULT_NICKNAME);
			if (data1 != null) {
				loader.displayImage(data1.result.imgUrl, imgMyProfile);
				textMyName.setText(this.data1.result.coupleName);
				editTextCouplePageMemo.setText(this.data1.result.comment);
			}
			textDateName.setText(data
					.getStringExtra(ProfileChangeActivity.RESULT_DATENAME));
			year = data.getIntExtra(ProfileChangeActivity.RESULT_DATEDAYS_YEAR,
					year);
			month = data.getIntExtra(
					ProfileChangeActivity.RESULT_DATEDAYS_MONTH, month);
			day = data.getIntExtra(ProfileChangeActivity.RESULT_DATEDAYS_DAY,
					day);
			textDateDays.setText("" + getDateDays(year, month, day));
			/*
			 * 변경된 결과를 적용
			 */
		}
	}

	private int getDateDays(int year, int month, int day) {
		Calendar startDateDay = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		startDateDay.set(year, month, day);

		long difference = (today.getTimeInMillis() - startDateDay
				.getTimeInMillis()) / 1000;
		return (int) (difference / (24 * 60 * 60));
	}

	@Override
	public void onResume() {
		// loader.displayImage(UserData.getInstance().data.result.get(0).imgUrl,
		// imgMyProfile);
		// textMyName.setText(UserData.getInstance().data.result.get(0).coupleName);
		super.onResume();
	}
}
