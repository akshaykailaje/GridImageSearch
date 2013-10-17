package com.example.gridimagesearch;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultArrayAdapter(Context context, List<ImageResult> imageResults) {
		super(context, R.layout.item_image_result, imageResults);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageResult = getItem(position);
		SmartImageView sivImage = null;
		
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			sivImage = (SmartImageView) inflator.inflate(R.layout.item_image_result, parent, false);
		} else {
			sivImage = (SmartImageView) convertView;
			sivImage.setImageResource(android.R.color.transparent);
		}
		
		sivImage.setImageUrl(imageResult.getThumbnailUrl());
		return sivImage;
	}

}
