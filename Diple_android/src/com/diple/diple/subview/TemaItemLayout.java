package com.diple.diple.subview;

import com.diple.diple.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TemaItemLayout extends FrameLayout {

	public TemaItemLayout(Context context) {
		super(context);
		init();
	}
	int temaNum;
	ImageView temaImage;
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.tema_grid_item_layout, this);
		temaImage = (ImageView)findViewById(R.id.imageTemaItem);
		temaImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.setOnTemaClick(temaNum);
			}
		});
	}
	
	public void setdata(Drawable i,int tema){
		temaImage.setImageDrawable(i);
		temaNum=tema;
	}
	
	public interface setTemaClick{
		public void setOnTemaClick(int temaNum);
	}

	setTemaClick mListener;
	
	public void setTemaClick(setTemaClick listener){
		mListener=listener;
	}
}
