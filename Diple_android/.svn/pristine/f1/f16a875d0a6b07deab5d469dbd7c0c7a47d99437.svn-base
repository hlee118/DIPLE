package com.diple.diple.loginsignup;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;

public class SendingPhoneNumFragment extends Fragment {

	EditText contactText;
	EditText myPhonText;
	String ohterShaNumber;
	String myShaNumber;
	private static final int CONTACT_PICKER_RESULT = 1001;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.send_phonenumber_layout,
				container, false);
		Button btn = (Button) rootView.findViewById(R.id.btnContacts);
		contactText = (EditText) rootView.findViewById(R.id.contactText);
		myPhonText = (EditText)rootView.findViewById(R.id.editMyPhon);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
				startActivityForResult(i, CONTACT_PICKER_RESULT);
			}
		});

		btn = (Button) rootView.findViewById(R.id.btnCompleteSignup);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * 원래는 상대방에게 앱 링크를 전송하고, 상대방 쪽에서 가입이 오기를 기다려야 함
				 */
			
				String myPhonNume = myPhonText.getText().toString();
				MessageDigest md;
				try {
					md = MessageDigest.getInstance("SHA-256");
					md.update(contactText.getText().toString().getBytes("UTF-8"));
					byte[] digest = md.digest();
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < digest.length; i++) {
						sb.append(Integer.toHexString(0xFF & digest[i]));
					}
					ohterShaNumber = sb.toString();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MessageDigest md1;
				try {
					md1 = MessageDigest.getInstance("SHA-256");
					md1.update(myPhonNume.getBytes("UTF-8"));
					byte[] digest = md1.digest();
					StringBuilder sb1 = new StringBuilder();
					for (int i = 0; i < digest.length; i++) {
						sb1.append(Integer.toHexString(0xFF & digest[i]));
					}
					myShaNumber = sb1.toString();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (((LoginSignupActivity) getActivity()).whatSingup == LoginSignupActivity.FACEBOOK_SINGUP) {
					NetworkModel.getInstance().getFacebookLogin(getActivity(),
							((LoginSignupActivity) getActivity()).id,
							((LoginSignupActivity) getActivity()).accessToken,
							myShaNumber, ohterShaNumber,PropertyManager.getInstance().getRegistrationId() ,
							new OnNetworkResult<ResultServerItem>() {

								@Override
								public void onSuccess(ResultServerItem result) {
								
									if (result.resultCode != -1) {
										PropertyManager.getInstance().setUserId(((LoginSignupActivity) getActivity()).id);
										PropertyManager.getInstance().setPassword(((LoginSignupActivity) getActivity()).accessToken);
										PropertyManager.getInstance().setWhatSingup(((LoginSignupActivity) getActivity()).whatSingup);
										if(PropertyManager.getInstance().getMatching().equals("1")){
											((LoginSignupActivity) getActivity()).onLoginSuccess();
										}else if(result.result.equals("123")){
											PropertyManager.getInstance().setMatching("1");
											((LoginSignupActivity) getActivity()).onLoginSuccess();
										}else{
											getActivity().finish();
										}
									}
									
								}
								@Override
								public void onFail(int code) {
									Toast.makeText(getActivity(), "페북 로그인 실패",
											Toast.LENGTH_SHORT).show();
								}
							});
				} else if (((LoginSignupActivity) getActivity()).whatSingup == LoginSignupActivity.EMAIL_SINGUP) {

				}

			}
		});
		
		return rootView;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		;
		getActivity();
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case CONTACT_PICKER_RESULT:
				Cursor cursor = null;
				String phoneNumber = "";
				try {
					Uri result = data.getData();

					// get the contact id from the Uri
					String id = result.getLastPathSegment();

					// query for everything phonenum
					cursor = getActivity().getContentResolver().query(
							Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?",
							new String[] { id }, null);

					int phoneNumberIdx = cursor.getColumnIndex(Phone.DATA);

					// let's just get the first phonenum
					if (cursor.moveToFirst()) {
						phoneNumber = cursor.getString(phoneNumberIdx);
					}
				} catch (Exception e) {
				} finally {
					if (cursor != null) {
						cursor.close();
					}
					String[] temp = phoneNumber.split("-");
					contactText.setText(temp[0]+temp[1]+temp[2]);
					if (phoneNumber.length() == 0) {
						Toast.makeText(getActivity(),
								"No phonenum found for contact.",
								Toast.LENGTH_SHORT).show();
					}

				}

				break;
			}
		}
	}
}
