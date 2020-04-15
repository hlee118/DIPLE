package com.diple.diple.subview;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.calender.SingleCalenderFragment;
import com.diple.diple.checkin.CheckableListViewActivity;
import com.diple.diple.checkinitem.Poi;
import com.diple.diple.checkinitem.TMapPOIResult;
import com.diple.diple.location_marker.MarkerLocationViewActivity;
import com.diple.diple.location_marker.MarkerResult;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.diple.diple.recoded.RecordedPreviewAdapter;
import com.diple.diple.severitem.RecordServerResult;
import com.diple.diple.severitem.userServerData;
import com.diple.diple.subview.TemaViewAdapter.setADTemaClick;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class GoogleMapFragment extends Fragment implements
		GoogleMap.OnCameraChangeListener, GoogleMap.OnMapClickListener,
		GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener,
		GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerDragListener {

	private static final String TAG = "DemoActivity";
	public static final String SAVED_STATE_ACTION_BAR_HIDDEN = "saved_state_action_bar_hidden";

	public GoogleMapFragment() {
	}

	GoogleMap mMap;
	LocationManager mLM;
	GridView temaGridView;
	TemaViewAdapter temaAdapter;
	LatLng latLng;
	FrameLayout markerEmpty; // 숫자 넣을 마커 framelayout

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		mLM = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);
	}

	LocationListener mListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			switch (status) {
			case LocationProvider.AVAILABLE:
				Toast.makeText(getActivity(), "provider available",
						Toast.LENGTH_SHORT).show();
				break;
			case LocationProvider.OUT_OF_SERVICE:
				Toast.makeText(getActivity(), "provider out of service",
						Toast.LENGTH_SHORT).show();
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				Toast.makeText(getActivity(),
						"provider temporarily unavailable", Toast.LENGTH_SHORT)
						.show();
				break;
			}
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLocationChanged(Location location) {
			if (mMap != null) {
				// ....

				latLng = new LatLng(location.getLatitude(),
						location.getLongitude());
				CameraPosition.Builder builder = new CameraPosition.Builder();
				builder.target(latLng);
				builder.zoom(15.5f);
				CameraPosition position = builder.build();
				// CameraUpdate update =
				// CameraUpdateFactory.newLatLngZoom(latLng, 15.5f);
				CameraUpdate update = CameraUpdateFactory
						.newCameraPosition(position);

				mMap.animateCamera(update);
			}
		}
	};

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
		MenuItem item = menu.findItem(R.id.menuTema);
		item = menu.findItem(R.id.menuSerch);
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String text) {
				NetworkModel.getInstance().getPOISearch(getActivity(), text, 1,
						"A", 0, 0, new OnNetworkResult<TMapPOIResult>() {

							@Override
							public void onSuccess(TMapPOIResult result) {

								searchAdapter.deleteAll();
								searchAdapter.addAll(result);
								
							}

							@Override
							public void onFail(int code) {
								// TODO Auto-generated method stub
								Toast.makeText(getActivity(),
										"MAP 서버가 불안정 합니다.", Toast.LENGTH_SHORT)
										.show();
							}
						});
				return true;
			}

			@Override
			public boolean onQueryTextChange(String text) {

				return false;
			}
		});
		// SearchableInfo info = null;
		// SearchManager sm =
		// (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
		// List<SearchableInfo> infos = sm.getSearchablesInGlobalSearch();
		// for (SearchableInfo in : infos) {
		// if (in.getSuggestAuthority().startsWith("application")) {
		// info = in;
		// }
		// }
		// if (info != null) {
		// searchView.setSearchableInfo(info);
		// }

	};

	boolean isCheckedTemaFlag = false;

	public void setTemaVisiility() {
		if (isCheckedTemaFlag) {
			temaLayout.setVisibility(View.GONE);
			isCheckedTemaFlag = false;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.menuTema && !isCheckedTemaFlag) {
			mHandler.post(getTema);
			temaLayout.setVisibility(View.VISIBLE);
			searchAdapter.deleteAll();
			isOptionCheckBack = true;
			isCheckedTemaFlag = true;
		} else if (id == R.id.menuTema && isCheckedTemaFlag) {
			temaLayout.setVisibility(View.GONE);
			isCheckedTemaFlag = false;
			isOptionCheckBack = true;
		} else if (id == R.id.menuSerch && isCheckedTemaFlag) {
			temaLayout.setVisibility(View.GONE);
			isOptionCheckBack = true;
			isCheckedTemaFlag = false;
		}
		return super.onOptionsItemSelected(item);
	}

	boolean isFirstExecute = true;

	@Override
	public void onStart() {
		super.onStart();
		
		
	}

	@Override
	public void onStop() {
		super.onStop();
		mLM.removeUpdates(mListener);
	}

	@Override
	public void onResume() {
		super.onResume();
		((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("");
		setupMapIfNeeded();
	}

	private void setupMapIfNeeded() {
		if (mMap == null) {
			// mMap = ((SupportMapFragment)
			// ((ActionBarActivity)getActivity())
			// .getSupportFragmentManager().findFragmentById(
			// R.id.fragment1)).getMap();
			mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(
					R.id.fragment1)).getMap();
			if (mMap != null) {
				setupMap();
			}
		}
	}

	private void setupMap() {
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		// mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		mMap.setMyLocationEnabled(true);
		mMap.getUiSettings().setCompassEnabled(true);
		mMap.setOnCameraChangeListener(this);
		mMap.setOnMapClickListener(this);
		mMap.setOnMarkerClickListener(this);
		mMap.setOnInfoWindowClickListener(this);
		mMap.setInfoWindowAdapter(new MyInfoWindow(getActivity()));
		mMap.setOnMarkerDragListener(this);
	}

	class MyInfoWindow implements InfoWindowAdapter {

		View infoView;
		TextView titleView;
		TextView dataView;

		public MyInfoWindow(Context context) {
			View v = LayoutInflater.from(context).inflate(
					R.layout.info_window_view, null);
			titleView = (TextView) v.findViewById(R.id.textMyName);
			dataView = (TextView) v.findViewById(R.id.textView2);
			infoView = v;
		}

		@Override
		public View getInfoContents(Marker marker) {
			String text = mDataResolver.get(marker);
			titleView.setText(marker.getTitle());
			dataView.setText(text);
			return infoView;
		}

		@Override
		public View getInfoWindow(Marker marker) {
			return null;
		}

	}

	FrameLayout temaLayout;
	ListView searchList;
	SearchAdapter searchAdapter;
	ListView TemaBestList;
	TemaBestAdapter temaBestAdapter;
	public SlidingDrawer drawer;
	Button drawerBtn;
	public boolean isOptionCheckBack = true;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.googlemap_fragment,
				container, false);
		setupMapIfNeeded();
		mHandler.post(setLo);
	
		
		drawer = (SlidingDrawer)rootView.findViewById(R.id.slidingDrawer1);
		drawerBtn = (Button)rootView.findViewById(R.id.handle);
		
		markerEmpty = (FrameLayout)rootView.findViewById(R.id.marker_number_framelayout);
		temaBestAdapter = new TemaBestAdapter(getActivity());
		TemaBestList = (ListView)rootView.findViewById(R.id.listView1);
		TemaBestList.setAdapter(temaBestAdapter);
		searchList = (ListView) rootView.findViewById(R.id.listSearch);
		searchAdapter = new SearchAdapter(getActivity());
		searchList.setAdapter(searchAdapter);

		searchList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Poi i = searchAdapter.getItem(position);
				latLng = new LatLng(Float.parseFloat(i.frontLat), Float
						.parseFloat(i.frontLon));
				CameraPosition.Builder builder = new CameraPosition.Builder();
				builder.target(latLng);
				builder.zoom(15.5f);
				CameraPosition position1 = builder.build();
				CameraUpdate update = CameraUpdateFactory
						.newCameraPosition(position1);

				mMap.moveCamera(update);
				searchAdapter.deleteAll();
			}
		});
		temaGridView = (GridView) rootView.findViewById(R.id.temaGridView);
		temaAdapter = new TemaViewAdapter(getActivity());
		temaGridView.setAdapter(temaAdapter);
		temaLayout = (FrameLayout) rootView.findViewById(R.id.temaLayout);
		temaAdapter.setADTemaClick(new setADTemaClick() {
			
			@Override
			public void setOnTemaClick(int temaNum) {
				mMap.clear();
				MarkerManager.getInstance().getMarkerOfTema(temaNum, mMap, mMarkerResolver, mDataResolver, markerEmpty);
//				 temaLayout.setVisibility(View.GONE);
//				 isCheckedTemaFlag = false;
			}
		});
		TemaBestList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				latLng = new LatLng(temaBestAdapter.data.get(position).latitude, temaBestAdapter.data.get(position).longitude);
				CameraPosition.Builder builder = new CameraPosition.Builder();
				builder.target(latLng);
				builder.zoom(15.5f);
				CameraPosition position1 = builder.build();
				CameraUpdate update = CameraUpdateFactory
						.newCameraPosition(position1);

				mMap.moveCamera(update);
				drawer.close();
			}
		});
		Button btn = (Button) rootView.findViewById(R.id.btnCheckIn);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(),
						CheckableListViewActivity.class);
				i.putExtra("latlng", latLng);
				startActivity(i);
			}
		});
		
		// slidingdrawer 버튼 변경하는 코드
		drawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				drawerBtn.setBackgroundResource(R.drawable.underbar_main_clicked);
				setTemaVisiility();

			}
		});
		
		drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				drawerBtn.setBackgroundResource(R.drawable.underbar_main);
				isCheckedTemaFlag = true;
			}
		});
		
		
		
		return rootView;
	}

	Handler mHandler = new Handler();
	Runnable setLo = new Runnable() {
		
		@Override
		public void run() {
			String provider = LocationManager.NETWORK_PROVIDER;
			if (!mLM.isProviderEnabled(provider)) {
				if (isFirstExecute) {
					isFirstExecute = false;
					Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(i);
				} else {
					Toast.makeText(getActivity(), "set network provider enable!!!",
							Toast.LENGTH_SHORT).show();
					getActivity().finish();
				}
				return;
			}
			Location location = mLM.getLastKnownLocation(provider);
			if (location != null) {
				mListener.onLocationChanged(location);
			}
			mLM.requestSingleUpdate(provider, mListener, null);
			NetworkModel.getInstance().getMarker(getActivity(), latLng.latitude,
					latLng.longitude, new OnNetworkResult<MarkerResult>() {

						@Override
						public void onSuccess(MarkerResult result) {
							if(result == null){
								mHandler.post(getTema);
							};
							MarkerManager.getInstance().add(result);
							MarkerManager.getInstance().getMarkerOfTema(9,mMap,mMarkerResolver,mDataResolver,markerEmpty);
							temaBestAdapter.addAll(result);
						}

						@Override
						public void onFail(int code) {
							Toast.makeText(getActivity(), "벳지 다운 실패",
									Toast.LENGTH_SHORT).show();

						}
					});
		}
	};
	Runnable getTema = new Runnable() {
		
		@Override
		public void run() {
			NetworkModel.getInstance().getMarker(getActivity(), latLng.latitude,
					latLng.longitude, new OnNetworkResult<MarkerResult>() {

						@Override
						public void onSuccess(MarkerResult result) {
							MarkerManager.getInstance().add(result);
							MarkerManager.getInstance().getMarkerOfTema(9,mMap,mMarkerResolver,mDataResolver,markerEmpty);
							temaBestAdapter.addAll(result);
						}

						@Override
						public void onFail(int code) {
							Toast.makeText(getActivity(), "벳지 다운 실패",
									Toast.LENGTH_SHORT).show();
						}
					});
		}
	};
	
	@Override
	public void onCameraChange(CameraPosition position) {

	}

	@Override
	public void onMapLongClick(LatLng latLng) {

	}

	HashMap<String, Marker> mMarkerResolver = new HashMap<String, Marker>();
	HashMap<Marker, String> mDataResolver = new HashMap<Marker, String>();


	@Override
	public void onMapClick(LatLng latLng) {
		setTemaVisiility();
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		String text = mDataResolver.get(marker);
		return false;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		Intent i = new Intent(getActivity(), MarkerLocationViewActivity.class);
		i.putExtra("loca", marker.getSnippet());
		startActivity(i);
	}

	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub

	}

	public void searchAdapterDeleteAll() {
		searchAdapter.deleteAll();
	}

}
