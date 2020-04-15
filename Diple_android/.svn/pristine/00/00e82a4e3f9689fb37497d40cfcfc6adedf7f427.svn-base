package com.diple.diple.checkinitem;

public class Poi {
	
	public String id;
	public String name;
	public String telNo;
	public String frontLat;
	public String frontLon;
	public String noorLat;
	public String noorLon;
	public String upperAddrName;
	public String middleAddrName;
	public String lowerAddrName;
	public String detailAddrName;
	public String firstNo;
	public String secondNo;
	public String radius;
	public String bizName;
	public String upperBizName;
	public String middleBizName;
	public String lowerBizName;
	public String detailBizName;
	public String rpFlag;
	public String parkFlag;
	public String detailInfoFlag;
	public String desc;
	
	public String getName(){
		return name;
	}
	public String getAddr(){
		return upperAddrName +" "+ middleAddrName +" "+lowerAddrName;
	}
	public int getCategory(){
		if(middleBizName.equals("음식점")){
			return 0;
		}else if(middleBizName.equals("극장")){
			return 1;
		}else if(middleBizName.equals("카페")){
			return 2;
		}else if(middleBizName.equals("술집")){
			return 4;
		}else if(middleBizName.equals("예술")){
			return 4;
		}else if(middleBizName.equals("쇼핑")){
			return 5;
		}else if(upperBizName.equals("생활")){
			return 6;
		}else{
			return 6;
		}
	}
}


