package com.example.gridimagesearch;

import java.io.Serializable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class AdvancedSearchOptionsActivity extends Activity {

	//private Spinner spImageSize;
	private Spinner spColorFilter;
	// private Spinner spImageType;
	public static final String DEFAULT_VALUE = "All";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advanced_search_options);
		setupViews();
		Serializable options = getIntent().getSerializableExtra("options");
		SearchOptions searchOptions = null;
		if (options == null) {
			// set Default values
			searchOptions = new SearchOptions();
			searchOptions.setImageSize(DEFAULT_VALUE);
			searchOptions.setColorFilter(DEFAULT_VALUE);
			searchOptions.setImageType(DEFAULT_VALUE);
			searchOptions.setSiteFilter("");
		} else {
			searchOptions = (SearchOptions) options;
		}
		
		setSavedOptions(searchOptions);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.advanced_search_options, menu);
		return true;
	}
	
	public void onOptionsSave(View v) {
		
		SearchOptions options = new SearchOptions();
		Spinner spImageSize = (Spinner) findViewById(R.id.spImageSize);
		if (spImageSize.getSelectedItem() != null) {
			options.setImageSize(spImageSize.getSelectedItem().toString());
		} else {
			options.setImageSize(DEFAULT_VALUE);
		}
		
		if (spColorFilter.getSelectedItem() != null) {
			options.setColorFilter(spColorFilter.getSelectedItem().toString());
		} else {
			options.setColorFilter(DEFAULT_VALUE);
		}
		Spinner spImageType = (Spinner) findViewById(R.id.spImageType);
		if (spImageType.getSelectedItem() != null) {
			options.setImageType(spImageType.getSelectedItem().toString());
		} else {
			options.setImageType(DEFAULT_VALUE);
		}
		
		EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
		options.setSiteFilter(etSiteFilter.getText().toString());
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra("options", options);
		setResult(RESULT_OK, returnIntent);
		finish();
		
	}
	
	private void setSavedOptions(SearchOptions options) {
		Spinner spImageSize = (Spinner) findViewById(R.id.spImageSize);
		setSpinnerToValue(spImageSize, options.getImageSize());
		setSpinnerToValue(spColorFilter, options.getColorFilter());
		Spinner spImageType = (Spinner) findViewById(R.id.spImageType);
		setSpinnerToValue(spImageType, options.getImageType());
		EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
		etSiteFilter.setText(options.getSiteFilter());
	}
	
	private void setSpinnerToValue(Spinner spinner, String value) {
		SpinnerAdapter adapter = spinner.getAdapter();
		
		for(int i = 0; i < adapter.getCount(); i++) {
			if (adapter.getItem(i).equals(value)) {
				spinner.setSelection(i);
				break;
			}
		}
	}
	
	private void setupViews() {
		
		spColorFilter = (Spinner) findViewById(R.id.spColorFilter);
		
			
	}

}
