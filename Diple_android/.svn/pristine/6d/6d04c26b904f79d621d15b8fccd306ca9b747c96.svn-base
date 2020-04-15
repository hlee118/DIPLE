package com.diple.diple.shortestroute;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.networkmodel.NetworkModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class ShortestRouteActivity extends ActionBarActivity implements
		GoogleMap.OnCameraChangeListener, GoogleMap.OnMapClickListener,
		GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener,
		GoogleMap.OnMarkerDragListener{
	
	public static final String LOCATION_LONGTITUDE = "location_longtitude";
	public static final String LOCATION_LATITUDE = "location_latitude";
	
	ActionBar actionBar;
	GoogleMap mMap;
	LocationManager mLM;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shortestroute_view);
		mLM = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		actionBar = getSupportActionBar();
	    actionBar.setTitle("");
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.btn_back);
	    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
	    
		setupMapIfNeeded();
		
		Intent shortestRouteIntent = getIntent();
		final float endLat = (Float)shortestRouteIntent.getExtras().get(LOCATION_LATITUDE);
		final float endLon = (Float)shortestRouteIntent.getExtras().get(LOCATION_LONGTITUDE);
		
		
		Button btn = (Button)findViewById(R.id.btnPedestrianRoute);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawPedestrainRoute(endLat, endLon);
			}
		});
		
		btn = (Button)findViewById(R.id.btnCarRoute);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawCarRoute(endLat, endLon);
			}
		});
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home ){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	LocationListener mListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status,
				Bundle extras) {
			// TODO Auto-generated method stub

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

				LatLng latLng = new LatLng(location.getLatitude(),
						location.getLongitude());
				CameraPosition.Builder builder = new CameraPosition.Builder();
				builder.target(latLng);
				builder.zoom(13.5f);
//				builder.bearing(10.5f);
//				builder.tilt(45);
				CameraPosition position = builder.build();
				// CameraUpdate update =
				// CameraUpdateFactory.newLatLngZoom(latLng, 15.5f);
				CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
				mMap.moveCamera(update);
			}

			mLM.removeUpdates(mListener);
		}
	};

	@Override
	public void onStart() {
		super.onStart();
		String provider = LocationManager.NETWORK_PROVIDER;
		Location location = mLM.getLastKnownLocation(provider);
		if (location != null) {
			mListener.onLocationChanged(location);
		}
		mLM.requestLocationUpdates(provider, 0, 0, mListener);
	}

	@Override
	public void onStop() {
		super.onStop();
		mLM.removeUpdates(mListener);
	}

	@Override
	public void onResume() {
		super.onResume();
		setupMapIfNeeded();
	}

	private void setupMapIfNeeded() {
		if (mMap == null) {
			mMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment1)).getMap();
			if (mMap != null) {
				setupMap();
			}
		}
	}

	private void setupMap() {
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.setMyLocationEnabled(true);
		mMap.getUiSettings().setCompassEnabled(true);
		mMap.setOnCameraChangeListener(this);
		mMap.setOnMapClickListener(this);
		mMap.setOnMarkerClickListener(this);
		mMap.setOnMarkerDragListener(this);
	}
	
	@Override
	public void onCameraChange(CameraPosition position) {

	}

	@Override
	public void onMapLongClick(LatLng latLng) {

	}

	HashMap<String,Marker> mMarkerResolver = new HashMap<String,Marker>();
	HashMap<Marker,String> mDataResolver = new HashMap<Marker,String>();		
	
	int mId = 1;
	
	@Override
	public void onMapClick(LatLng latLng) {
//		MarkerOptions options = new MarkerOptions();
//		options.position(latLng);
//		options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
//		options.anchor(0.5f, 0.5f);
//		options.title("my marker " + mId);
//		options.snippet("description");
//		options.draggable(false);
//		Marker marker = mMap.addMarker(options);
//		String text = "my marker " + mId;
//		mMarkerResolver.put(text, marker);
//		mDataResolver.put(marker, text);
//		mId++;
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		String text = mDataResolver.get(marker);
		Toast.makeText(this, "data : " + text, Toast.LENGTH_SHORT).show();
		return false;
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
	
	private void drawPedestrainRoute(final double endLat, final double endLon){
		if(mMap.getMyLocation() == null){
			Toast.makeText(this, R.string.shortestroute_location_notfound, Toast.LENGTH_SHORT).show();
			return;
		}
		NetworkModel.getInstance().getPedestrainRouting(this, mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude(), endLat, endLon, "가가", "나나", new NetworkModel.OnNetworkResult<PedestrainRouteInfo>() {

			@Override
			public void onSuccess(PedestrainRouteInfo result) {
				PolylineOptions options = new PolylineOptions();
				
				for (PedestrainFeature feature : result.features) {
					if (feature.geometry.type.equals("LineString")) {
						double[] coord = feature.geometry.coordinates;
						for (int i = 0 ; i < coord.length; i+=2) {
							options.add(new LatLng(coord[i+1],coord[i]));
						}
					}
				}
				mMap.clear();
				options.color(Color.RED);
				options.width(10);
				options.geodesic(false);
				mMap.addPolyline(options);
				drawFirstMarker();
				drawLastMarker(endLat, endLon);
			}

			@Override
			public void onFail(int code) {
				
			}
			
		});
		
	}
	
	private void drawCarRoute(final double endLat, final double endLon){
		if(mMap.getMyLocation() == null){
			Toast.makeText(this, R.string.shortestroute_location_notfound, Toast.LENGTH_SHORT).show();
			return;
		}
		NetworkModel.getInstance().getCarRouting(this, mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude(), endLat, endLon, new NetworkModel.OnNetworkResult<CarRouteInfo>() {

			@Override
			public void onSuccess(CarRouteInfo result) {
				PolylineOptions options = new PolylineOptions();
				
				for (CarFeature feature : result.features) {
					if (feature.geometry.type.equals("LineString")) {
						double[] coord = feature.geometry.coordinates;
						for (int i = 0 ; i < coord.length; i+=2) {
							options.add(new LatLng(coord[i+1],coord[i]));
						}
					}
				}
				mMap.clear();
				options.color(Color.RED);
				options.width(10);
				options.geodesic(false);
				mMap.addPolyline(options);
				drawFirstMarker();
				drawLastMarker(endLat, endLon);
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	private void drawLastMarker(double endLat, double endLon){
		MarkerOptions options = new MarkerOptions();
		options.position(new LatLng(endLat, endLon));
		options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shortestroute_marker_arrival));
//		options.anchor(0.5f, 0.5f);
//		options.title("my marker " + mId);
//		options.snippet("description");
		options.draggable(false);
		Marker marker = mMap.addMarker(options);
//		String text = "my marker " + mId;
//		mMarkerResolver.put(text, marker);
//		mDataResolver.put(marker, text);
//		mId++;
	}
	
	private void drawFirstMarker(){
		MarkerOptions options = new MarkerOptions();
		options.position(new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude()));
		options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shortestroute_marker_start));
		options.draggable(false);
		Marker marker = mMap.addMarker(options);
	}
}
