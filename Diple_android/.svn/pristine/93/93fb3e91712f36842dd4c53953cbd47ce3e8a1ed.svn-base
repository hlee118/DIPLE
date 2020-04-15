package com.diple.diple.recoded;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.TextView;

import com.diple.diple.R;
import com.diple.diple.photodetail.PhotoDetailActivity;
import com.diple.diple.severitem.Record;

public class RecodedDetailItemFrameLayout extends FrameLayout {
	Record data;
	Context mContext;
	public RecodedDetailItemFrameLayout(Context context, Record record) {
		super(context);
		data=record;
		mContext = context;
		init();
	}
	public static final String INTENT_PARAM_PHOTO= "intentparamphoto";
	RecodedDetailGallayAdapter mAdapter;
	Gallery gallery;
	TextView nameText;
	TextView timeText;
	TextView detailComment;
	TextView detailMoreInfo;
	
	public void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.recoded_detail_item_layout, this);
		gallery = (Gallery)findViewById(R.id.gallery1);
		gallery.setAnimationDuration(500);
		mAdapter = new RecodedDetailGallayAdapter(mContext, data.photo);
		gallery.setAdapter(mAdapter);
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i =new Intent(mContext, PhotoDetailActivity.class);
				i.putExtra(INTENT_PARAM_PHOTO, data.photo );
				i.putExtra("i", position );
				mContext.startActivity(i);
			}
		});
		nameText = (TextView)findViewById(R.id.textRecordedDetailName);
		timeText = (TextView)findViewById(R.id.textRecordedDetailTime);
		nameText.setText(data.info.placeName);
		timeText.setText(data.info.regdate.split("T")[1].split("Z")[0]);
		detailComment = (TextView)findViewById(R.id.textRecordedDetailComment);
		detailComment.setText(data.info.comment);
		detailMoreInfo = (TextView)findViewById(R.id.textRecordedDetailMoreInfo);
		detailMoreInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(detailComment.getMaxLines() == 2){
					detailComment.setMaxLines(100);
					detailMoreInfo.setText(R.string.recorded_detail_moreinfo_opened);
				} else {
					detailComment.setMaxLines(2);
					detailMoreInfo.setText(R.string.recorded_detail_moreinfo_closed);
				}
			}
		});
	}
}
