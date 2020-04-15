package com.diple.diple.recoded;

import com.diple.diple.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class RecodedWheelFrameLayout extends FrameLayout {

	public RecodedWheelFrameLayout(Context context, String string) {
		super(context);
		init(string);
	}
	
	TextView text;
	public void init(String string){
		LayoutInflater.from(getContext()).inflate(R.layout.recorded_gallary_layout, this);
		text =(TextView)findViewById(R.id.textView1);
		text.setText(string);
	}
}
