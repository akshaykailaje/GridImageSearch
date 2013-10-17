package com.example.gridimagesearch;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ImageDisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		
		ImageResult imageResult = (ImageResult) getIntent().getSerializableExtra("imageResult");
		SmartImageView sivImage = (SmartImageView) findViewById(R.id.sivImage);
		sivImage.setImageUrl(imageResult.getFullUrl());
		TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvTitle.setText(imageResult.getTitle());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}

}
