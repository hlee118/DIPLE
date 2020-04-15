package com.diple.diple.location_marker;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.severitem.Comment;
import com.diple.diple.severitem.LikeNumResulte;

public class PhotoPagerComentLayout extends FrameLayout {

	public PhotoPagerComentLayout(Context context) {
		super(context);
		init();
	}
	
	TextView nameText;
	TextView comentText;
	TextView likeText;
	ImageView comentUserView;
	boolean isChecked = false;
	GestureDetector mDetector;
	Comment data;
	public void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.marker_location_coment_layout, this);
		nameText = (TextView)findViewById(R.id.textName);
		comentText = (TextView)findViewById(R.id.textComent);
		comentUserView = (ImageView)findViewById(R.id.imageComentUser);
		likeText = (TextView)findViewById(R.id.textLikeView);
		
		mDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDown(MotionEvent e) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				if(!isChecked){
					NetworkModel.getInstance().commentLike(getContext(), data.commentId, new NetworkModel.OnNetworkResult<LikeNumResulte>(){

						@Override
						public void onSuccess(LikeNumResulte result) {
							likeText.setText(""+result.result.likeNum);
							Toast.makeText(getContext(), "좋아요 완료", Toast.LENGTH_SHORT).show();
							isChecked = true;
						}

						@Override
						public void onFail(int code) {
							
						}
					
					
					});
				}else{
					Toast.makeText(getContext(), "이미 좋아요 하셨습니다.", Toast.LENGTH_SHORT).show();
				}
				return super.onSingleTapUp(e);
			}
		});
		//좋아요 버튼 누르면 여기다 기술
		Button btn = (Button)findViewById(R.id.btnLike);
		btn.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return mDetector.onTouchEvent(event);
			}
		});
	}
	
	public void setData(Comment item){
		data=item;
		nameText.setText(item.coupleName + "님의 말 :"); 
		comentText.setText(item.comment);
		likeText.setText(""+item.likeNum);
		comentUserView.setImageResource(0);
	}

}
