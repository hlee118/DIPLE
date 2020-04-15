package com.diple.diple.recoded;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.diple.diple.severitem.Photo;
import com.diple.diple.severitem.Record;
import com.diple.diple.severitem.RecordServerResult;

public class RecordedPreviewAdapter extends BaseAdapter {

	
	private static RecordedPreviewAdapter instance;
	
	public static RecordedPreviewAdapter getInstance(){
		if(instance ==null){
			instance = new RecordedPreviewAdapter();
		}
		return instance;
	}
	
	Context mContext;
	public ArrayList<RecordServerResult> reData = new ArrayList<RecordServerResult>();
	public static final int TYPE_ALL = 0;
	public static final int TYPE_TEMA = 1;
	public static final int TYPE_COMMENT = 2;
	int type = 1;

	private RecordedPreviewAdapter() {}
	public void setContext(Context context){
		mContext = context;
	}
	public void setType(int otype){
		type= otype;
		notifyDataSetChanged();
	}
	public int getType(){
		return type;
	}
	
	public String[] date;
	public void setData(RecordServerResult data){
		String temp;
		
		int j=0;
		if (data.result.size() != 0 & data.result != null) {
			reData.add(new RecordServerResult());
			reData.get(j).result = new ArrayList<Record>();
			reData.get(j).result.add(data.result.get(0));
			for (int i = 0; i < data.result.size()-1; i++) {
				temp = data.result.get(i).info.regdate;
				String[] values1 = temp.split("T");
				
				temp = data.result.get(i + 1).info.regdate;
				String[] values_1 = temp.split("T");
				
				if (values1[0].equals(values_1[0])) {
					reData.get(j).result.add(data.result.get(i + 1));
				} else {
					reData.add(new RecordServerResult());
					j++;
					reData.get(j).result = new ArrayList<Record>();
					reData.get(j).result.add(data.result.get(i + 1));
				}
			}
			
			date = new String[j+1];
			for(int i = 0 ; i< date.length;i++){
				String[] temp1 =  reData.get(i).result.get(0).info.regdate.split("T");
				date[i] = temp1[0];
			}
			notifyDataSetChanged();
		}
	}
	
	@Override
	public int getCount() {
		return reData.size();
	}

	@Override
	public Object getItem(int position) {
		return reData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
			RecordedPreviewItemFrameLayout view;
			if (convertView == null) {
				view = new RecordedPreviewItemFrameLayout(mContext,
						reData.get(position));
			} else {
				view = (RecordedPreviewItemFrameLayout) convertView;
			}
			return view;
	}
	
	public ArrayList<String> getDateList(){
		ArrayList<String> dateList = new ArrayList<String>(Arrays.asList(date));
		return dateList;
	}

}
