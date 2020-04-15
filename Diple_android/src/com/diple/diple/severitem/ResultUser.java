package com.diple.diple.severitem;

import android.os.Parcel;
import android.os.Parcelable;

public class ResultUser implements Parcelable{
	public int userNo;
	public String userId;
	public String password;
	public String imgUrl;
	public int otherNo;
	public String myPhoneNumber;
	public String otherPhoneNumber;
	public String coupleName;
	public String comment;
	public String regdate;
	public String regId;
	
	
	public ResultUser(Parcel source) {
		userNo = source.readInt();
		userId = source.readString();
		password = source.readString();
		imgUrl =source.readString();
		otherNo =source.readInt();
		myPhoneNumber =source.readString();
		otherPhoneNumber =source.readString();
		coupleName =source.readString();
		regdate =source.readString();
		regId =source.readString();
		comment =source.readString();
	}
	@Override
	public int describeContents() {
		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(userNo);
		dest.writeString(userId);
		dest.writeString(password);
		dest.writeString(imgUrl);
		dest.writeInt(otherNo);
		dest.writeString(myPhoneNumber);
		dest.writeString(otherPhoneNumber);
		dest.writeString(coupleName);
		dest.writeString(regdate);
		dest.writeString(regId);
		dest.writeString(comment);
		
	}
	
	public static Parcelable.Creator<ResultUser> CREATOR = new Parcelable.Creator<ResultUser>() {

		@Override
		public ResultUser createFromParcel(Parcel source) {
			
			return new ResultUser(source);
		}

		@Override
		public ResultUser[] newArray(int size) {
			return new ResultUser[size];
		}
	};
}
