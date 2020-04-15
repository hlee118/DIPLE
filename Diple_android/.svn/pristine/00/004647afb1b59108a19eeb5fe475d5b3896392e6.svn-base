package com.diple.diple.setting;

import com.diple.diple.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LockActivity extends Activity {
	
	public boolean isPwExist = false;
	public boolean isLocked = false;
	
	private int[] clickableNumber = {0,1,2,3,4,5,6,7,8,9};
	int[] password = {clickableNumber[1],clickableNumber[2],clickableNumber[3],clickableNumber[4]};
	private int[] comparePw = {-1,-1,-1,-1};
	
	ImageView[] imgPwInput;
	
	private static LockActivity lockActivity;
	public static LockActivity getInstance(){
		if(lockActivity == null){
			lockActivity = new LockActivity();
		}
		return lockActivity;
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting_lock_view);
	    
	    imgPwInput = new ImageView[]{
	    		(ImageView)findViewById(R.id.imgPwInput1),
	    		(ImageView)findViewById(R.id.imgPwInput2),
	    		(ImageView)findViewById(R.id.imgPwInput3),
	    		(ImageView)findViewById(R.id.imgPwInput4)
	    };
	
	    Button btn = (Button)findViewById(R.id.btnNum0);
	    btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
	}

}
