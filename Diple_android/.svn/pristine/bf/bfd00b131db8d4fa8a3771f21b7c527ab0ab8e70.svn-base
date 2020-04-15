package com.diple.diple.subview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.diple.diple.R;
import com.diple.diple.networkmodel.NetworkModel;
import com.diple.diple.networkmodel.NetworkModel.OnNetworkResult;
import com.diple.diple.severitem.ZzimResult;
import com.diple.diple.severitem.userServerData;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileChangeActivity extends Activity {
	userServerData data;
	ImageView imgMyProfile;
	EditText editMyNickname;
	EditText editAnniversaryName;
	EditText editAnniversaryDate;
	Intent resultIntent;
	Bitmap bm;
	Uri uri;
	Uri uriChangImage;
	File mSavedFile;
	ImageLoader loader;
	int year, month, day;
	boolean isChanged = false;
	public static final int REQUEST_CODE_CROP = 0;
	public static final String PARAM_NICKNAME = "param_nickname";
	public static final String RESULT_NICKNAME = "result_nickname";
	public static final String RESULT_IMGRESID = "result_imgresid";
	public static final String RESULT_DATENAME = "result_datename";
	public static final String RESULT_DATEDAYS_YEAR = "result_datedays_year";
	public static final String RESULT_DATEDAYS_MONTH = "result_datedays_month";
	public static final String RESULT_DATEDAYS_DAY = "result_datedays_day";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_change_layout);
		resultIntent = new Intent();
		Intent i = getIntent();
		data = i.getParcelableExtra(PARAM_NICKNAME);
		loader = ImageLoader.getInstance();
		imgMyProfile = (ImageView) findViewById(R.id.imgEditMyProfile);
		loader.displayImage(data.result.imgUrl, imgMyProfile);
		imgMyProfile.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK,
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				photoPickerIntent.setType("image/*");
				photoPickerIntent.putExtra("crop", "true");
				photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						getTempUri());
				photoPickerIntent.putExtra("outputFormat",
						Bitmap.CompressFormat.JPEG.toString());
				startActivityForResult(photoPickerIntent, REQUEST_CODE_CROP);
				// Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
				// imageIntent.setType("image/*");
				// startActivityForResult(imageIntent, 0);
			}
		});

		editMyNickname = (EditText) findViewById(R.id.editMyNickname);
		editMyNickname.setText(data.result.coupleName);
		editAnniversaryName = (EditText) findViewById(R.id.editAnniversaryName);
		editAnniversaryName.setText(i.getCharSequenceExtra(RESULT_DATENAME));
		editAnniversaryDate = (EditText) findViewById(R.id.editAnniversaryDate);
		year = i.getIntExtra(RESULT_DATEDAYS_YEAR, 2000);
		month = i.getIntExtra(RESULT_DATEDAYS_MONTH, 0) + 1;
		day = i.getIntExtra(RESULT_DATEDAYS_DAY, 1);
		editAnniversaryDate.setText("" + year + "." + month + "." + day);

		Button btn = (Button) findViewById(R.id.btnChangeMyProfile);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * 프로필 변겅 사항 처리
				 */

				NetworkModel.getInstance().userUpd(ProfileChangeActivity.this,
						editMyNickname.getText().toString(),
						data.result.comment, mSavedFile,
						new OnNetworkResult<userServerData>() {

							@Override
							public void onSuccess(userServerData result) {
								data = result;
								checkDateCorrect(editAnniversaryDate.getText()
										.toString());
								resultIntent.putExtra(RESULT_NICKNAME, result);
								resultIntent.putExtra(RESULT_DATENAME,
										editAnniversaryName.getText()
												.toString());
								resultIntent.putExtra(RESULT_DATEDAYS_YEAR,
										year);
								resultIntent.putExtra(RESULT_DATEDAYS_MONTH,
										month);
								resultIntent.putExtra(RESULT_DATEDAYS_DAY, day);

								setResult(Activity.RESULT_OK, resultIntent);
								if (mSavedFile != null) {
									mSavedFile.delete();
									mSavedFile = null;
								}
								finish();
								Toast.makeText(ProfileChangeActivity.this,
										"변경 완료", Toast.LENGTH_SHORT).show();
							}

							@Override
							public void onFail(int code) {
								// TODO Auto-generated method stub

							}
						});

			}
		});

		btn = (Button) findViewById(R.id.btnRotation);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isChanged) {
					Matrix matrix = new Matrix();
					matrix.postRotate(90);
					bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
							bm.getHeight(), matrix, true);
					imgMyProfile.setImageBitmap(bm);
				} else {
					Toast.makeText(ProfileChangeActivity.this,
							"이미지를 변경해야 가능합니다.", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),
				"temp_diple_image" + System.currentTimeMillis() % 100000);
		return Uri.fromFile(mSavedFile);
	}

	@Override
	public void onBackPressed() {
		if (mSavedFile != null) {
			mSavedFile.delete();
			mSavedFile = null;
		}
		super.onBackPressed();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_CROP && resultCode == RESULT_OK) {
			BitmapFactory.Options op = new BitmapFactory.Options();
			op.inSampleSize = 3;
			bm = BitmapFactory.decodeFile(mSavedFile.getAbsolutePath(), op);
			imgMyProfile.setImageBitmap(bm);
			isChanged = true;
			uri = Uri.fromFile(mSavedFile);
		}
		// if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
		// uri = data.getData();
		// setImage(uri);
		// PropertyManager.getInstance().setProfilePath(uri.toString());
		// }
	}

	public void setImage(Uri uri) {
		long id = ContentUris.parseId(uri);
		bm = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(),
				id, MediaStore.Images.Thumbnails.MINI_KIND, null);
		imgMyProfile.setImageBitmap(bm);
	}

	private boolean checkDateCorrect(String date) {
		String[] temp = date.split("\\.");
		if (temp.length != 3) {
			Toast.makeText(ProfileChangeActivity.this, "날짜 입력 형식이 잘못되었습니다.",
					Toast.LENGTH_SHORT).show();
			return false;
		} else {
			year = Integer.parseInt(temp[0]);
			month = Integer.parseInt(temp[1]) - 1;
			day = Integer.parseInt(temp[2]);
			return true;
			// Toast.makeText(ProfileChangeActivity.this, temp[0] + "." +
			// (Integer.parseInt(temp[1])-1) + "." + temp[2],
			// Toast.LENGTH_SHORT).show();
		}
	}
}
