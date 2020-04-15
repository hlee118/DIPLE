package com.diple.diple.location_marker;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.diple.diple.R;
import com.diple.diple.photodetail.PhotoDetailActivity;
import com.diple.diple.recoded.RecodedDetailItemFrameLayout;
import com.diple.diple.severitem.Photo;
import com.etsy.android.grid.StaggeredGridView;

public class StaggerdImageViewMain extends ActionBarActivity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {

	
	public static final String STAGGERED_PHOTO_LIST = "staggered_photo_list";
	  private StaggeredGridView mGridView;
	  private boolean mHasRequestedMore;

	  
	  //나중에 서버 나오면 지우기
	  StaggeredAdapter mAdapter;
	  private ArrayList<String> mData;
	  ArrayList<Photo> item;
	  ActionBar actionBar;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.marker_saggerd_layout);
	   
	    actionBar = getSupportActionBar();
	    actionBar.setTitle("");
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.btn_back);
	    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
	    
	    Intent i = getIntent();
	    item = i.getParcelableArrayListExtra(STAGGERED_PHOTO_LIST);
	    
	    mGridView = (StaggeredGridView)findViewById(R.id.grid_view);
	    mAdapter = new StaggeredAdapter(this, item);


        if (mData == null) {
            mData = SampleData.generateSampleData();
        }

//        for (String data : mData) {
//            mAdapter.add(data);
//        }

        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);
	    
	}

	@Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
        Log.d("asdf", "onScroll firstVisibleItem:" + firstVisibleItem +
                            " visibleItemCount:" + visibleItemCount +
                            " totalItemCount:" + totalItemCount);
        // our handling
        if (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                Log.d("asdf", "onScroll lastInScreen - so load more");
                mHasRequestedMore = true;
//                onLoadMoreItems();
            }
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
    	Intent i =new Intent(this, PhotoDetailActivity.class);
		i.putExtra(RecodedDetailItemFrameLayout.INTENT_PARAM_PHOTO, item );
		i.putExtra("i", position );
		startActivity(i);
    }

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home ){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}

class SampleData {

    public static final int SAMPLE_DATA_ITEM_COUNT = 30;

    public static ArrayList<String> generateSampleData() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            data.add("SAMPLE #");
        }

        return data;
    }

}
