package com.diple.diple.loginsignup;

import java.util.Arrays;
import java.util.List;

import com.facebook.Request.Callback;
import com.facebook.Session;

public class FaceData {
	private static FaceData instance;
	private FaceData(){}
	
	public static FaceData getInstance(){
		if(instance ==null){
			instance = new FaceData();
		}
		return instance;
	}
	
	
	Session mSession;
	Callback mCallback;
	public void setSession(Session session){
		mSession = session;
	}
	public Session getSession(){
		return mSession;
	}
	public void setCallback(Callback callback){
		mCallback=callback;
	}
	public Callback getCallback(){
		return mCallback;
	}
}
