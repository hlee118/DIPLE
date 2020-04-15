package com.diple.diple.recoded;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.diple.diple.R;

public class RecodedDetailFragment extends Fragment {
	

	ListView detailList;
	RecodedDetailAdapter detailAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.recorded_detail_layout, container, false);
		
		
	    detailList = (ListView)v.findViewById(R.id.recordedDetailList);

	    detailAdapter = new RecodedDetailAdapter(getActivity(),((RecodedDetailActivity)getActivity()).oldPosition);
	    detailList.setAdapter(detailAdapter);
		
		return v;
	}
	

}
