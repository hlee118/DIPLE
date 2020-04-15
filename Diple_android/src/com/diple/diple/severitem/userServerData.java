package com.diple.diple.severitem;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class userServerData implements Parcelable{
	public ResultUser result;

	public userServerData(Parcel source) {
		result = source.readParcelable(getClass().getClassLoader());
	}
	@Override
	public int describeContents() {
		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable( result, flags);
	}
	
	public static Parcelable.Creator<userServerData> CREATOR = new Parcelable.Creator<userServerData>() {

		@Override
		public userServerData createFromParcel(Parcel source) {
			
			return new userServerData(source);
		}

		@Override
		public userServerData[] newArray(int size) {
			return new userServerData[size];
		}
	};
}

