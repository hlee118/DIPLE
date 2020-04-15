package com.diple.diple.loginsignup;

import java.io.IOException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.diple.diple.MainActivity;
import com.diple.diple.R;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class LoginSignupActivity extends ActionBarActivity {
	String id;
	String accessToken;
	int whatSingup =-1;
	public static final int FACEBOOK_SINGUP = 1;
	public static final int EMAIL_SINGUP = 2;
	
	FragmentManager fm;
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();
        
        fm = getSupportFragmentManager();
        
        if (savedInstanceState == null) {
        	fm.beginTransaction().add(R.id.container, new SplashFragment()).commit();
        }
       
		if (checkPlayServices()) {
			 String regId = PropertyManager.getInstance().getRegistrationId(); //getRegistrationId();
			if (regId.equals("")) {
				registerInBackground();
				switch(PropertyManager.getInstance().getWhatSingup()){
				case LoginSignupActivity.FACEBOOK_SINGUP:
					NetworkModel.getInstance().getFacebookLogin(LoginSignupActivity.this, PropertyManager.getInstance().getUserId(), PropertyManager.getInstance().getPassword(), null, null, PropertyManager.getInstance().getRegistrationId() , new OnNetworkResult<ResultServerItem>() {
						
						@Override
						public void onSuccess(ResultServerItem result) {
							
							String a = PropertyManager.getInstance().getMatching();
							if(a.equals("1")){
								onLoginSuccess();
							}else if(result.result != null){
								PropertyManager.getInstance().setMatching("1");
								onLoginSuccess();
							}else{
								finish();
							}
						}
						
						@Override
						public void onFail(int code) {
							Toast.makeText(LoginSignupActivity.this, "페북 로그인 실패",Toast.LENGTH_SHORT).show();
						}
					});
					break;
				case LoginSignupActivity.EMAIL_SINGUP:
					break;
				default :
					setLoginSignupFragment();
					break;
				}
			} else {
				switch(PropertyManager.getInstance().getWhatSingup()){
				case LoginSignupActivity.FACEBOOK_SINGUP:
					NetworkModel.getInstance().getFacebookLogin(LoginSignupActivity.this, PropertyManager.getInstance().getUserId(), PropertyManager.getInstance().getPassword(), null, null, regId , new OnNetworkResult<ResultServerItem>() {
						
						@Override
						public void onSuccess(ResultServerItem result) {

							if(PropertyManager.getInstance().getMatching().equals("1")){
								onLoginSuccess();
							}else if(result.result != null){
								PropertyManager.getInstance().setMatching("1");
							}else{
								finish();
							}
						}
						
						@Override
						public void onFail(int code) {
							Toast.makeText(LoginSignupActivity.this, "페북 로그인 실패",Toast.LENGTH_SHORT).show();
						}
					});
					break;
				case LoginSignupActivity.EMAIL_SINGUP:
					break;
				case 0 :
					setLoginSignupFragment();
					break;
				}
			}
		} else {
			Toast.makeText(this, "no gcm service", Toast.LENGTH_SHORT).show();
			finish();
		}
        
    }

	GoogleCloudMessaging gcm;
	
	private void registerInBackground() {
	    new AsyncTask<Void,Integer,String>() {
	        @Override
	        protected String doInBackground(Void... params) {
	            String msg = "";
	            
	            try {
	                if (gcm == null) {
	                    gcm = GoogleCloudMessaging.getInstance(LoginSignupActivity.this);
	                }
	                String regid = gcm.register("802709220765");
	                PropertyManager.getInstance().storeRegistrationId(regid);
	            } catch (IOException ex) {
	                msg = "Error :" + ex.getMessage();
	                // If there is an error, don't just keep trying to register.
	                // Require the user to click a button again, or perform
	                // exponential back-off.
	            }
	            return msg;
	        }

	        @Override
	        protected void onPostExecute(String msg) {
	        	if(msg.equals("")){
	        		
	        	}
	        }
	    }.execute(null, null, null);
	}	
	
	private boolean checkPlayServices() {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, this,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	            Toast.makeText(this, "checkPlayServices", Toast.LENGTH_SHORT).show();
	            switch(PropertyManager.getInstance().getWhatSingup()){
				case LoginSignupActivity.FACEBOOK_SINGUP:
					NetworkModel.getInstance().getFacebookLogin(LoginSignupActivity.this, PropertyManager.getInstance().getUserId(), PropertyManager.getInstance().getPassword(), null, null, PropertyManager.getInstance().getRegistrationId() , new OnNetworkResult<ResultServerItem>() {
						
						@Override
						public void onSuccess(ResultServerItem result) {

							if(PropertyManager.getInstance().getMatching().equals("1")){
								onLoginSuccess();
							}else if(result.result != null){
								PropertyManager.getInstance().setMatching("1");
							}else{
								finish();
							}
						}
						
						@Override
						public void onFail(int code) {
							Toast.makeText(LoginSignupActivity.this, "페북 로그인 실패",Toast.LENGTH_SHORT).show();
						}
					});
					break;
				case LoginSignupActivity.EMAIL_SINGUP:
					break;
				default :
					setLoginSignupFragment();
					break;
				}
	        } else {
	        	Toast.makeText(this, "매칭전 사용이 불가합니다.", Toast.LENGTH_SHORT).show();
	            finish();
	        }
	        return false;
	    }
	    return true;
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    


    public void setLoginSignupFragment(){
    	fm.beginTransaction().replace(R.id.container, new LoginSignupFragment()).commit();
    }

    public void setSendingPhoneNumFragment(){
    	fm.beginTransaction().replace(R.id.container, new SendingPhoneNumFragment())
    		.addToBackStack("backstackSendingPhoneNum").commit();
    }
    
    public void setSignupEmailFragment(){
    	fm.beginTransaction().replace(R.id.container, new SignupEmailFragment())
    		.addToBackStack("backstackSignupEmail").commit();
    }

    public void onLoginSuccess(){
    	Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
    }
}
