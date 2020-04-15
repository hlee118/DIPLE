package com.diple.diple.loginsignup;

import com.diple.diple.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginDialogFragment extends DialogFragment {
	
	EditText idText;
	EditText passwordText;
	String result;
	TextView text;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCancelable(true);
		setStyle(DialogFragment.STYLE_NO_TITLE, 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.login_layout, container, false);
		idText = (EditText) rootView.findViewById(R.id.editTextID);
		passwordText = (EditText) rootView.findViewById(R.id.editTextPassword);
		text = (TextView)rootView.findViewById(R.id.textView1);
		Button btn = (Button) rootView.findViewById(R.id.btnLogin);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String com = idText.getText().toString();
				((LoginSignupActivity) getActivity()).onLoginSuccess();

			}
		});
		return rootView;
	}
}
