package com.diple.diple.loginsignup;

import android.content.Context;
import android.content.SharedPreferences;

import com.diple.diple.networkmodel.MyApplication;

public class PropertyManager {

	private static PropertyManager instance;

	public static PropertyManager getInstance() {
		if (instance == null) {
			instance = new PropertyManager();
		}
		return instance;
	}

	private static final String PREF_NAME = "my_prefs.xml";
	SharedPreferences mPrefs;
	SharedPreferences.Editor mEditor;

	private PropertyManager() {
		mPrefs = MyApplication.getContext().getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		mEditor = mPrefs.edit();
	}

	private static final String FIELD_USER_ID = "userId";
	private String userId;

	public String getUserId() {
		if (userId == null) {
			userId = mPrefs.getString(FIELD_USER_ID, "");
		}
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		mEditor.putString(FIELD_USER_ID, userId);
		mEditor.commit();
	}

	private static final String FIELD_USER_PASSWORD = "password";
	private String userPassword;

	public String getPassword() {
		if (userPassword == null) {
			userPassword = mPrefs.getString(FIELD_USER_PASSWORD, "");
		}
		return userPassword;
	}

	public void setPassword(String passwd) {
		userPassword = passwd;
		mEditor.putString(FIELD_USER_PASSWORD, passwd);
		mEditor.commit();
	}
	
	private static final String FIELD_PROFILE_PATH = "profilePath";
	private String profilePath;
	
	public String getProfilePath() {
		if (profilePath == null) {
			profilePath = mPrefs.getString(FIELD_PROFILE_PATH, "");
		}
		return profilePath;
	}
	
	public void setProfilePath(String path) {
		profilePath = path;
		mEditor.putString(FIELD_PROFILE_PATH, path);
		mEditor.commit();
	}
	
	private static final String FIELD_USER_WHAT = "whatSingup";
	private int whatSingup;
	
	public int getWhatSingup() {
		if (whatSingup == 0) {
			whatSingup = mPrefs.getInt(FIELD_USER_WHAT, 0);
		}
		return whatSingup;
	}

	public void setWhatSingup(int what) {
		this.whatSingup = what;
		mEditor.putInt(FIELD_USER_WHAT, whatSingup);
		mEditor.commit();
	}
	
	private static final String FIELD_REGISTID = "registid";
	private String registid;
	
	public String getRegistrationId() {
		if (registid == null) {
			registid = mPrefs.getString(registid, "");
		}
		return registid;
	}
	public void storeRegistrationId(String regid){
		registid = regid;
		mEditor.putString(FIELD_REGISTID, regid);
		mEditor.commit();
	}
	
	private static final String FIELD_MATCHING = "matchinguser";
	private String matching;
	
	public String getMatching() {
		if (matching == null) {
			matching = mPrefs.getString(matching, "-1");
		}
		return matching;
	}
	public void setMatching(String key1){
		matching = key1;
		mEditor.putString(matching, key1);
		mEditor.commit();
	}
}
