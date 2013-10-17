package com.example.gridimagesearch;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class ImageSearchActivity extends Activity {

	private static final int SETTINGS_REQUEST_CODE = 1;
	private EditText etSearch;
	private GridView gvImages;
	private Button btnSearch;
	private List<ImageResult> imageResults = new ArrayList<ImageResult>();
	private ImageResultArrayAdapter imageAdapter;
	private SearchOptions options;
	private AsyncHttpClient client = new AsyncHttpClient();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        setupViews();
        imageAdapter = new ImageResultArrayAdapter(this, imageResults);
        gvImages.setAdapter(imageAdapter);
        options = null;
        gvImages.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
					ImageResult clickedImageResult = imageResults.get(position);
					
					Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
					i.putExtra("imageResult", clickedImageResult);
					startActivity(i);
			}
        	
		});
        
        gvImages.setOnScrollListener(new EndlessScrollListener() {
			
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				Log.d("DEBUG", "page="+page+", total="+totalItemsCount+", actual="+imageResults.size());
				Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();
				loadImageResults(page);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_search, menu);
        return true;
    }
    
    private void setupViews() {
    	etSearch = (EditText) findViewById(R.id.etSearch);
    	gvImages = (GridView) findViewById(R.id.gvImages);
    	btnSearch = (Button) findViewById(R.id.btnSearch);
    }
    
    private void loadImageResults(int page) {
    	String url = UrlBuilder.buildUrl(etSearch.getText().toString().trim(), options, page);
    	Log.d("DEBUG", "url="+url);
    	client.get( url, 
    				new JsonHttpResponseHandler() {
    		
    		@Override
    		public void onSuccess(JSONObject response) {
    			JSONArray imageJsonResults = null;
    			try {
    				if (response.isNull("responseData") || response.getJSONObject("responseData").isNull("results")) {
    					Toast.makeText(getApplicationContext(), "No more results to load", Toast.LENGTH_LONG).show();
    					return;
    				}
    				imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
    				//imageResults.clear();
    				imageResults.addAll(ImageResult.fromJSONArray(imageJsonResults));
    				imageAdapter.notifyDataSetChanged();
    			} catch(JSONException e) {
    				Log.d("DEBUG", "Error: " + e.getMessage());
    				Toast.makeText(getApplicationContext(), "Error retrieving search results", Toast.LENGTH_LONG).show();
    			}
    		}
    		
    		@Override
    		public void onFailure(Throwable e, JSONObject errorResponse) {
    			Toast.makeText(getApplicationContext(), "Error while requesting results", Toast.LENGTH_LONG).show();
    		}
    		
    	});
    }
    
    public void onImageSearch(View v) {
    	String query = etSearch.getText().toString();
    	if (query.trim().isEmpty()) {
    		Toast.makeText(getApplicationContext(), "Please enter search query", Toast.LENGTH_LONG);
    		return;
    	}
    	Toast.makeText(this, "Search text entered: " + query, Toast.LENGTH_SHORT).show();
    	imageResults.clear();
    	loadImageResults(1);
    }
    
    public void onSettingsAction(MenuItem mi) {
    	Intent i = new Intent(getApplicationContext(), AdvancedSearchOptionsActivity.class);
    	i.putExtra("options", this.options);
    	
    	startActivityForResult(i, SETTINGS_REQUEST_CODE);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == SETTINGS_REQUEST_CODE) {
    		if (resultCode == RESULT_OK) {
    			options = (SearchOptions) data.getSerializableExtra("options");
    			Log.d("DEBUG", "intent data = " + options);
    		}
    	}
    }
    
}
