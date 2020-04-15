package com.diple.diple.recoded;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.diple.diple.R;
import com.diple.diple.severitem.Info;
import com.diple.diple.severitem.Photo;
import com.diple.diple.severitem.RecordServerResult;
import com.nostra13.universalimageloader.core.ImageLoader;

public class RecordedPreviewItemFrameLayout extends FrameLayout {
	RecordServerResult data;;
	ImageLoader loader;

	public RecordedPreviewItemFrameLayout(Context context,
			RecordServerResult recordServerResult) {
		super(context);
		loader = ImageLoader.getInstance();
		this.data = recordServerResult;
		init();

	}

	TextView recordedDate;
	ImageView image1;
	ImageView image2;
	ImageView image3;
	FrameLayout imgPhotoFrameLayout1;
	FrameLayout imgPhotoFrameLayout2;
	FrameLayout imgPhotoFrameLayout3;
	ArrayList<Photo> photo = new ArrayList<Photo>();
	ArrayList<Info> info = new ArrayList<Info>();

	private void init() {
		LayoutInflater.from(getContext()).inflate(
				R.layout.recorded_preview_item, this);
		recordedDate = (TextView) findViewById(R.id.recordedDate);
		if (data != null) {
			String[] arr = data.result.get(0).info.regdate.split("T");
			String[] arr2 = arr[0].split("-");
			recordedDate.setText(arr2[0] + ". " + arr2[1] + ". " + arr2[2]);
		}
		image1 = (ImageView) findViewById(R.id.imagePhoto1);
		image2 = (ImageView) findViewById(R.id.imagePhoto2);
		image3 = (ImageView) findViewById(R.id.imagePhoto3);
		imgPhotoFrameLayout1 = (FrameLayout)findViewById(R.id.imgPhotoFrameLayout1);
		imgPhotoFrameLayout2 = (FrameLayout)findViewById(R.id.imgPhotoFrameLayout2);
		imgPhotoFrameLayout3 = (FrameLayout)findViewById(R.id.imgPhotoFrameLayout3);
		

		int type = RecordedPreviewAdapter.getInstance().getType();
		if (type == RecordedPreviewAdapter.TYPE_ALL) {
			if (this.data != null) {
				for (int i = 0; i < data.result.size(); i++) {
					for (int j = 0; j < data.result.get(i).photo.size(); j++) {
						photo.add(data.result.get(i).photo.get(j));
					}
				}
			}
			if (photo.size() == 1) {
				loader.displayImage(photo.get(0).thumbnailImg, image1);
			} else if (photo.size() == 2) {
				loader.displayImage(photo.get(0).thumbnailImg, image1);
				loader.displayImage(photo.get(1).thumbnailImg, image2);
			} else {
				loader.displayImage(photo.get(0).thumbnailImg, image1);
				loader.displayImage(photo.get(1).thumbnailImg, image2);
				loader.displayImage(photo.get(2).thumbnailImg, image3);
			}
		} else if (type == RecordedPreviewAdapter.TYPE_TEMA) {
			if (this.data != null) {
				for (int i = 0; i < data.result.size(); i++) {
					info.add(data.result.get(i).info);
				}
			}
			if (info.size() == 1) {
				image2.setMaxHeight(80);
				image2.setMaxWidth(80);
				switch (info.get(0).badgeId1) {
				case 0:
					image2.setImageResource(R.drawable.recorded_theme_food);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_food));
					break;
				case 1:
					image2.setImageResource(R.drawable.recorded_theme_movie);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_movie));
					break;
				case 2:
					image2.setImageResource(R.drawable.recorded_theme_cafe);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_cafe));
					break;
				case 3:
					image2.setImageResource(R.drawable.recorded_theme_pup);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_pup));
					break;
				case 4:
					image2.setImageResource(R.drawable.recorded_theme_art);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_art));
					break;
				case 5:
					image2.setImageResource(R.drawable.recorded_theme_shopping);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_shopping));
					break;
				case 6:
					image2.setImageResource(R.drawable.recorded_theme_outsider);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_outsider));
					break;
				}
//				image2.setBackgroundColor(getResources().getColor(R.color.blue));
//				image1.setVisibility(View.GONE);
//				image3.setVisibility(View.GONE);
				imgPhotoFrameLayout1.setVisibility(View.GONE);
				imgPhotoFrameLayout3.setVisibility(View.GONE);
			} else if (info.size() == 2) {
				switch (info.get(0).badgeId1) {
				case 0:
					image1.setImageResource(R.drawable.recorded_theme_food);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_food));
					break;
				case 1:
					image1.setImageResource(R.drawable.recorded_theme_movie);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_movie));
					break;
				case 2:
					image1.setImageResource(R.drawable.recorded_theme_cafe);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_cafe));
					break;
				case 3:
					image1.setImageResource(R.drawable.recorded_theme_pup);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_pup));
					break;
				case 4:
					image1.setImageResource(R.drawable.recorded_theme_art);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_art));
					break;
				case 5:
					image1.setImageResource(R.drawable.recorded_theme_shopping);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_shopping));
					break;
				case 6:
					image1.setImageResource(R.drawable.recorded_theme_outsider);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_outsider));
					break;
				}

				switch (info.get(1).badgeId1) {
				case 0:
					image2.setImageResource(R.drawable.recorded_theme_food);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_food));
					break;
				case 1:
					image2.setImageResource(R.drawable.recorded_theme_movie);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_movie));
					break;
				case 2:
					image2.setImageResource(R.drawable.recorded_theme_cafe);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_cafe));
					break;
				case 3:
					image2.setImageResource(R.drawable.recorded_theme_pup);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_pup));
					break;
				case 4:
					image2.setImageResource(R.drawable.recorded_theme_art);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_art));
					break;
				case 5:
					image2.setImageResource(R.drawable.recorded_theme_shopping);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_shopping));
					break;
				case 6:
					image2.setImageResource(R.drawable.recorded_theme_outsider);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_outsider));
					break;
				}
//				image1.setBackgroundColor(getResources()
//						.getColor(R.color.green));
//				image2.setBackgroundColor(getResources().getColor(R.color.blue));
				imgPhotoFrameLayout3.setVisibility(View.GONE);
			} else if (info.size() >= 3) {
				switch (info.get(0).badgeId1) {
				case 0:
					image1.setImageResource(R.drawable.recorded_theme_food);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_food));
					break;
				case 1:
					image1.setImageResource(R.drawable.recorded_theme_movie);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_movie));
					break;
				case 2:
					image1.setImageResource(R.drawable.recorded_theme_cafe);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_cafe));
					break;
				case 3:
					image1.setImageResource(R.drawable.recorded_theme_pup);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_pup));
					break;
				case 4:
					image1.setImageResource(R.drawable.recorded_theme_art);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_art));
					break;
				case 5:
					image1.setImageResource(R.drawable.recorded_theme_shopping);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_shopping));
					break;
				case 6:
					image1.setImageResource(R.drawable.recorded_theme_outsider);
					imgPhotoFrameLayout1.setBackgroundColor(getResources().getColor(R.color.recorded_theme_outsider));
					break;
				}

				switch (info.get(1).badgeId1) {
				case 0:
					image2.setImageResource(R.drawable.recorded_theme_food);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_food));
					break;
				case 1:
					image2.setImageResource(R.drawable.recorded_theme_movie);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_movie));
					break;
				case 2:
					image2.setImageResource(R.drawable.recorded_theme_cafe);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_cafe));
					break;
				case 3:
					image2.setImageResource(R.drawable.recorded_theme_pup);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_pup));
					break;
				case 4:
					image2.setImageResource(R.drawable.recorded_theme_art);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_art));
					break;
				case 5:
					image2.setImageResource(R.drawable.recorded_theme_shopping);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_shopping));
					break;
				case 6:
					image2.setImageResource(R.drawable.recorded_theme_outsider);
					imgPhotoFrameLayout2.setBackgroundColor(getResources().getColor(R.color.recorded_theme_outsider));
					break;
				}
				switch (info.get(2).badgeId1) {
				case 0:
					image3.setImageResource(R.drawable.recorded_theme_food);
					imgPhotoFrameLayout3.setBackgroundColor(getResources().getColor(R.color.recorded_theme_food));
					break;
				case 1:
					image3.setImageResource(R.drawable.recorded_theme_movie);
					imgPhotoFrameLayout3.setBackgroundColor(getResources().getColor(R.color.recorded_theme_movie));
					break;
				case 2:
					image3.setImageResource(R.drawable.recorded_theme_cafe);
					imgPhotoFrameLayout3.setBackgroundColor(getResources().getColor(R.color.recorded_theme_cafe));
					break;
				case 3:
					image3.setImageResource(R.drawable.recorded_theme_pup);
					imgPhotoFrameLayout3.setBackgroundColor(getResources().getColor(R.color.recorded_theme_pup));
					break;
				case 4:
					image3.setImageResource(R.drawable.recorded_theme_art);
					imgPhotoFrameLayout3.setBackgroundColor(getResources().getColor(R.color.recorded_theme_art));
					break;
				case 5:
					image3.setImageResource(R.drawable.recorded_theme_shopping);
					imgPhotoFrameLayout3.setBackgroundColor(getResources().getColor(R.color.recorded_theme_shopping));
					break;
				case 6:
					image3.setImageResource(R.drawable.recorded_theme_outsider);
					imgPhotoFrameLayout3.setBackgroundColor(getResources().getColor(R.color.recorded_theme_outsider));
					break;
				}
//				image1.setBackgroundColor(getResources()
//						.getColor(R.color.green));
//				image2.setBackgroundColor(getResources().getColor(R.color.blue));
//				image3.setBackgroundColor(getResources().getColor(
//						R.color.orange));
			}

		}

	}
}
