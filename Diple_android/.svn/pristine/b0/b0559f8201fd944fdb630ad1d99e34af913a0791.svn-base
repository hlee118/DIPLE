package com.diple.diple.severitem;


import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable{
	public String originalImg;
	public String thumbnailImg;
	
	public Photo(Parcel source) {
		originalImg = source.readString();
		thumbnailImg = source.readString();	
	}
	@Override
	public int describeContents() {
		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(originalImg);
		dest.writeString(thumbnailImg);
	}
	
	public static Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {

		@Override
		public Photo createFromParcel(Parcel source) {
			
			return new Photo(source);
		}

		@Override
		public Photo[] newArray(int size) {
			return new Photo[size];
		}
	};
}
