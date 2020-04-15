package com.diple.diple.loginsignup;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.location_marker.MarkerLocationViewActivity;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.FacebookRequestError;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class LoginSignupFragment extends Fragment {

	LoginButton loginButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.login_signup_layout,
				container, false);

		Button btn = (Button) rootView.findViewById(R.id.btnFacebook);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Session.openActiveSession(getActivity(), LoginSignupFragment.this, true, new Session.StatusCallback() {
					
					@Override
					public void call(final Session session, SessionState state, Exception exception) {
						if(session.isOpened()){
							
							FaceData.getInstance().setSession(session);
							String token = session.getAccessToken();
							Request.newMeRequest(session, new GraphUserCallback() {
								
								@Override
								public void onCompleted(GraphUser user, Response response) {
								
									((LoginSignupActivity) getActivity()).id = user.getId();
									((LoginSignupActivity) getActivity()).accessToken = session.getActiveSession().getAccessToken();
									((LoginSignupActivity) getActivity()).whatSingup = LoginSignupActivity.FACEBOOK_SINGUP;
									((LoginSignupActivity) getActivity()).setSendingPhoneNumFragment();
								}
							}).executeAsync();
						}
					}
				});
				
			}
		});

		btn = (Button) rootView.findViewById(R.id.btnSignupEmail);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((LoginSignupActivity) getActivity()).setSignupEmailFragment();
			}
		});

		btn = (Button) rootView.findViewById(R.id.btnLogin);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = ((FragmentActivity)getActivity()).getSupportFragmentManager();
				LoginDialogFragment dialog = new LoginDialogFragment();
				dialog.show(fm, "dialog");
//				((LoginSignupActivity) getActivity()).setLoginFragment();
			}
		});

		return rootView;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Session.getActiveSession().onActivityResult(getActivity(), requestCode,
				resultCode, data);
		if (Session.getActiveSession().isOpened()) {
			/*
			 * 여기에서 서버에다 해야할 일 해야함
			 */
			// Session.getActiveSession().getAccessToken()
			// id

			// final Session session = Session.getActiveSession();
			// String token = session.getAccessToken();
			// Request.newMeRequest(session, new GraphUserCallback() {
			//
			// @Override
			// public void onCompleted(GraphUser user, Response response) {
			// // TODO Auto-generated method stub
			//
			//
			// NetworkModel.getInstance().getFacebookLogin(getActivity(),
			// user.getId(), session.getAccessToken(), new
			// OnNetworkResult<FaceBookLoginItem>() {
			//
			// @Override
			// public void onSuccess(FaceBookLoginItem result) {
			// if(result.resultCode != -1){
			// ((LoginSignupActivity)getActivity()).setSendingPhoneNumFragment();
			// }
			// }
			//
			// @Override
			// public void onFail(int code) {
			// // TODO Auto-generated method stub
			// Toast.makeText(getActivity(), "facebook 로그인 실패",
			// Toast.LENGTH_SHORT).show();
			// getActivity().finish();
			// }
			// });
			// }
			// }).executeAsync();
			//
			// }
			super.onActivityResult(requestCode, resultCode, data);
		}

	}
	
}
