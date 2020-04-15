package com.diple.diple.recoded;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.diple.diple.R;
import com.diple.diple.severitem.RecordServerResult;

public class RecordedPreviewCommentFragment extends Fragment {
	
	
	RecordedPreviewAdapter mAdapter;
	ListView reList;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.recorded_preview_all_fragment, container, false);
		reList = (ListView)v.findViewById(R.id.recordedPreviewList);

		reList.setAdapter(RecordedPreviewAdapter.getInstance());
		RecordedPreviewAdapter.getInstance().setType(RecordedPreviewAdapter.TYPE_COMMENT);
		reList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getActivity(), RecodedDetailActivity.class);
				i.putExtra("put", position);
				startActivity(i);
			}
		});
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onStart() {
		RecordedPreviewAdapter.getInstance().setType(RecordedPreviewAdapter.TYPE_COMMENT);
		super.onStart();
	}
}
