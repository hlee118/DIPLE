package com.diple.diple.loginsignup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.diple.diple.R;

public class SignupEmailFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.signup_email_layout, container, false);
		Button btn = (Button)rootView.findViewById(R.id.btnSignupEmail);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((LoginSignupActivity)getActivity()).setSendingPhoneNumFragment();
			}
		});
		
		return rootView;
	}
}
