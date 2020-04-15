package com.diple.diple.networkmodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import android.content.Context;
import android.widget.Toast;

import com.diple.diple.checkin.CheckinItem;
import com.diple.diple.checkinitem.MyhereLocation;
import com.diple.diple.checkinitem.TMapPOIResult;
import com.diple.diple.location_marker.MarkerResult;
import com.diple.diple.loginsignup.ResultServerItem;
import com.diple.diple.severitem.LikeNumResulte;
import com.diple.diple.severitem.LocationResult;
import com.diple.diple.severitem.RecordServerResult;
import com.diple.diple.severitem.ZzimResult;
import com.diple.diple.severitem.userServerData;
import com.diple.diple.shortestroute.CarRouteInfo;
import com.diple.diple.shortestroute.Geometry;
import com.diple.diple.shortestroute.GeometryDeserializer;
import com.diple.diple.shortestroute.PedestrainRouteInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class NetworkModel {

	AsyncHttpClient client;
	String result;
	private static NetworkModel instance;

	private NetworkModel() {
		client = new AsyncHttpClient();
		client.setCookieStore(new PersistentCookieStore(MyApplication
				.getContext()));
		client.setTimeout(30000);
	}

	static public NetworkModel getInstance() {
		if (instance == null) {
			instance = new NetworkModel();
		}
		return instance;
	}

	public interface OnNetworkResult<T> {
		public void onSuccess(T result);

		public void onFail(int code);
	}

	public void getFacebookLogin(final Context context, String ID,
			String accessToken, String myPhoneNum, String otherPhoneNum,
			String regId, final OnNetworkResult<ResultServerItem> listener) {
		String url = "http://54.187.154.204:8000/fbLogin";

		RequestParams params = new RequestParams();
		params.put("userId", "" + ID);
		params.put("accessToken", "" + accessToken);
		params.put("myPhoneNumber", "" + myPhoneNum);
		params.put("otherPhoneNumber", "" + otherPhoneNum);
		params.put("regId", "" + regId);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				Gson gson = new Gson();
				ResultServerItem info = gson.fromJson(result,
						ResultServerItem.class);
				listener.onSuccess(info);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});

	}

	public void getCheckIn(final Context context, double latitude,
			double longitude, String placeName, String placeId, String address,
			String storePhone, String comment, int badgeType,
			ArrayList<CheckinItem> myFile,
			final OnNetworkResult<ResultServerItem> listener) {
		String url = "http://54.187.154.204:8000/checkin";

		RequestParams params = new RequestParams();
		params.put("latitude", "" + latitude);
		params.put("longitude", "" + longitude);
		params.put("placeName", "" + placeName);
		params.put("address", "" + address);
		params.put("storePhone", "" + storePhone);
		params.put("placeId", "" + placeId);
		params.put("comment", "" + comment);
		params.put("badgeType", "" + badgeType);
		try {
			for (int i = 0; i <= myFile.size() - 2; i++) {
				File file = new File(myFile.get(i).photoFile.getAbsolutePath());
				params.put("myFile", file);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		// params.put("myfile", "" + myfile);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				Gson gson = new Gson();
				ResultServerItem info = gson.fromJson(result,
						ResultServerItem.class);
				listener.onSuccess(info);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});

	}

	public void getMarker(final Context context, double latitude,
			double longitude, final OnNetworkResult<MarkerResult> listener) {
		String url = "http://54.187.154.204:8000/marker";

		RequestParams params = new RequestParams();
		params.put("latitude", "" + latitude);
		params.put("longitude", "" + longitude);

		// params.put("myfile", "" + myfile);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				Gson gson = new Gson();
				MarkerResult info = gson.fromJson(result, MarkerResult.class);
				listener.onSuccess(info);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});

	}

	public void getLocation(final Context context, String placeId,
			final OnNetworkResult<LocationResult> listener) {
		String url = "http://54.187.154.204:8000/location";

		RequestParams params = new RequestParams();
		params.put("placeId", placeId);

		// params.put("myfile", "" + myfile);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				if (result != null) {
					Gson gson = new Gson();
					LocationResult info = gson.fromJson(result,
							LocationResult.class);
					listener.onSuccess(info);
				} else {
					Toast.makeText(context, "서버 자료 없음", Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});

	}

	public void getRecord(final Context context,
			final OnNetworkResult<RecordServerResult> listener) {
		String url = "http://54.187.154.204:8000/record";

		RequestParams params = new RequestParams();

		// params.put("myfile", "" + myfile);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				if (result != null) {
					Gson gson = new Gson();
					RecordServerResult info = gson.fromJson(result,
							RecordServerResult.class);
					listener.onSuccess(info);
				} else {
					Toast.makeText(context, "서버 자료 없음", Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});

	}

	public void zzimReg(final Context context, String placeId,
			final OnNetworkResult<String> listener) {
		String url = "http://54.187.154.204:8000/zzimReg";

		RequestParams params = new RequestParams();
		params.put("placeId", "" + placeId);

		// params.put("myfile", "" + myfile);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				if (result != null) {
					listener.onSuccess(result);
				} else {
					Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
	}

	public void zzimDel(final Context context, String placeId,
			final OnNetworkResult<String> listener) {
		String url = "http://54.187.154.204:8000/zzimDel";

		RequestParams params = new RequestParams();
		params.put("placeId", "" + placeId);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				if (result != null) {

					listener.onSuccess(result);
				} else {
					Toast.makeText(context, "Empty Data", Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
	}

	public void zzim(final Context context,
			final OnNetworkResult<ZzimResult> listener) {
		String url = "http://54.187.154.204:8000/zzim";

		RequestParams params = new RequestParams();

		// params.put("myfile", "" + myfile);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				if (result != null) {
					Gson gson = new Gson();
					ZzimResult info = gson.fromJson(result, ZzimResult.class);
					listener.onSuccess(info);
				} else {
					Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
	}

	public void user(final Context context,
			final OnNetworkResult<userServerData> listener) {
		String url = "http://54.187.154.204:8000/user";

		RequestParams params = new RequestParams();

		// params.put("myfile", "" + myfile);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				if (result != null) {
					Gson gson = new Gson();
					userServerData info = gson.fromJson(result,
							userServerData.class);
					listener.onSuccess(info);
				} else {
					Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
	}

	public void userUpd(final Context context, String coupleName,
			String message, File myfile,
			final OnNetworkResult<userServerData> listener) {
		String url = "http://54.187.154.204:8000/userUpd";

		RequestParams params = new RequestParams();

		if (myfile != null) {
			try {
				File file = new File(myfile.getAbsolutePath());
				params.put("myFile", file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			params.put("myFile", "");
		}
		params.put("coupleName", "" + coupleName);
		params.put("message", "" + message);

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				if (result != null) {
					Gson gson = new Gson();
					userServerData info = gson.fromJson(result,
							userServerData.class);
					listener.onSuccess(info);
				} else {
					Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
	}
	
	public void commentLike(final Context context, int commentId,
			final OnNetworkResult<LikeNumResulte> listener) {
		String url = "http://54.187.154.204:8000/commentLike";

		RequestParams params = new RequestParams();

		params.put("commentId", ""+commentId);
		

		client.post(context, url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String result = new String(responseBody);
				if (result != null) {
					Gson gson = new Gson();
					LikeNumResulte info = gson.fromJson(result,
							LikeNumResulte.class);
					listener.onSuccess(info);

				} else {
					Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
	}

	public void getCarRouting(Context context, double startLat,
			double startLon, double endLat, double endLon,
			final OnNetworkResult<CarRouteInfo> listener) {
		String url = "https://apis.skplanetx.com/tmap/routes?callback=&bizAppId=&version=1";

		Header[] headers = new Header[2];
		headers[0] = new BasicHeader("Accept", "application/json");
		headers[1] = new BasicHeader("appKey",
				"3b8f6d11-dcf8-3b5e-90c3-8d9c743337a5");

		RequestParams params = new RequestParams();
		params.put("endX", "" + endLon);
		params.put("endY", "" + endLat);
		params.put("startX", "" + startLon);
		params.put("startY", "" + startLat);
		params.put("resCoordType", "WGS84GEO");
		params.put("reqCoordType", "WGS84GEO");
		client.post(context, url, headers, params, null,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String result = new String(responseBody);
						Gson gson = new GsonBuilder().registerTypeAdapter(
								Geometry.class, new GeometryDeserializer())
								.create();
						CarRouteInfo info = gson.fromJson(result,
								CarRouteInfo.class);
						listener.onSuccess(info);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						listener.onFail(statusCode);
					}
				});
	}

	public void getPedestrainRouting(Context context, double startLat,
			double startLon, double endLat, double endLon, String startName,
			String endName, final OnNetworkResult<PedestrainRouteInfo> listener) {
		String url = "https://apis.skplanetx.com/tmap/routes/pedestrian?callback=&bizAppId=&version=1";

		Header[] headers = new Header[2];
		headers[0] = new BasicHeader("Accept", "application/json");
		headers[1] = new BasicHeader("appKey",
				"3b8f6d11-dcf8-3b5e-90c3-8d9c743337a5");

		RequestParams params = new RequestParams();
		params.put("startX", "" + startLon);
		params.put("startY", "" + startLat);
		params.put("endX", "" + endLon);
		params.put("endY", "" + endLat);
		params.put("startName", startName);
		params.put("endName", endName);
		params.put("resCoordType", "WGS84GEO");
		params.put("reqCoordType", "WGS84GEO");
		client.post(context, url, headers, params, null,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String result = new String(responseBody);
						Gson gson = new GsonBuilder().registerTypeAdapter(
								Geometry.class, new GeometryDeserializer())
								.create();
						PedestrainRouteInfo info = gson.fromJson(result,
								PedestrainRouteInfo.class);
						listener.onSuccess(info);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						listener.onFail(statusCode);
					}
				});
	}

	public void getPOISearch(Context context, String searchKeyword, int radius,
			String searchtypCd, double centerLon, double centerLat,
			final OnNetworkResult<TMapPOIResult> listener) {
		Header[] headers = new Header[2];
		headers[0] = new BasicHeader("Accept", "application/json");
		headers[1] = new BasicHeader("appKey",
				"3b8f6d11-dcf8-3b5e-90c3-8d9c743337a5");
		String url = "https://apis.skplanetx.com/tmap/pois";
		RequestParams params = new RequestParams();
		params.put("centerLat", "" + centerLat);
		params.put("centerLon", "" + centerLon);
		params.put("areaLMCode", "");
		params.put("count", "");
		params.put("multiPoint", "");
		params.put("bizAppId", "");
		params.put("searchType", "");
		params.put("version", "" + 1);

		params.put("page", "");
		params.put("reqCoordType", "WGS84GEO");
		params.put("searchKeyword", "" + searchKeyword);
		params.put("areaLLCode", "");
		params.put("callback", "");
		params.put("searchtypCd", searchtypCd);
		params.put("radius", "" + radius);
		params.put("resCoordType", "WGS84GEO");

		client.get(context, url, headers, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String result = new String(responseBody);
						Gson gson = new Gson();
						// Gson gson = new
						// GsonBuilder().serializeNulls().create();
						TMapPOIResult info = gson.fromJson(result,
								TMapPOIResult.class);
						listener.onSuccess(info);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						listener.onFail(statusCode);
					}
				});

	}

	public void getHereLocation(Context context, double longitude,
			double latitude, final OnNetworkResult<MyhereLocation> listener) {

		Header[] headers = new Header[2];
		headers[0] = new BasicHeader("Accept", "application/json");
		headers[1] = new BasicHeader("appKey",
				"3b8f6d11-dcf8-3b5e-90c3-8d9c743337a5");

		String url = "https://apis.skplanetx.com/tmap/geo/reversegeocoding";

		RequestParams params = new RequestParams();
		params.put("lon", "" + longitude);
		params.put("callback", "");
		params.put("coordType", "WGS84GEO");
		params.put("bizAppId", "");
		params.put("addressType", "");
		params.put("lat", "" + latitude);
		params.put("version", "1");

		client.get(context, url, headers, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String result = new String(responseBody);
						Gson gson = new Gson();
						// Gson gson = new
						// GsonBuilder().serializeNulls().create();
						MyhereLocation info = gson.fromJson(result,
								MyhereLocation.class);
						listener.onSuccess(info);

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						listener.onFail(statusCode);
					}
				});
	}
}
