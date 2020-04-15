package com.diple.diple.loginsignup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.diple.diple.MainActivity;
import com.diple.diple.R;

public class LoginMainpageFragment extends Fragment{

	TextView textView;
	public static final int REQUEST_CODE_MAIN_PAGE = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.login_mainpage_layout, container, false);
		
		// 뒤에 나오는 화면을 클릭할 수 없도록 하는 코드
		textView = (TextView)rootView.findViewById(R.id.textAfterLogin);
		textView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// nothing but intercept click
			}
		});
//		textView = (TextView)rootView.findViewById(R.id.textNothing);
//		textView.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// nothing but intercept click
//			}
//		});
		// 여기까지 뒤에 나오는 화면을 클릭할 수 없도록 하는 코드
		
		// 로그인 이루어지게 하는 activity 실행
		Button btn = (Button)rootView.findViewById(R.id.btnGoLoginPage);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), LoginSignupActivity.class);
				startActivityForResult(i,REQUEST_CODE_MAIN_PAGE);
			}
		});
		return rootView;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_MAIN_PAGE && resultCode == Activity.RESULT_OK) {
			((MainActivity)getActivity()).succeedLogin();
		}
	}
}
