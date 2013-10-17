package com.example.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.util.Log;

public class ImageResult implements Serializable {
	
	private static final long serialVersionUID = -5741948511937249770L;
	private String fullUrl;
	private String thumbnailUrl;
	private String title;
	
	private ImageResult(JSONObject jsonObject) {
		try {
			this.fullUrl = jsonObject.getString("url");
			this.thumbnailUrl = jsonObject.getString("tbUrl");
			this.title = Uri.decode(jsonObject.getString("title"));
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbnailUrl = null;
		}
	}
	
	public String getFullUrl() {
		return this.fullUrl;
	}
	
	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public String toString() {
		
		return "{ fullUrl="+this.fullUrl+", thumbnailUrl=" + this.thumbnailUrl +" }\\n";
	}
	
	public static List<ImageResult> fromJSONArray(JSONArray imageJSONResults) {
		
		if (imageJSONResults == null) {
			return new ArrayList<ImageResult>();
		}
		
		List<ImageResult> imageResults = new ArrayList<ImageResult>();
		for(int i = 0; i < imageJSONResults.length(); i++) {
			try {
				imageResults.add(new ImageResult(imageJSONResults.getJSONObject(i)));
			} catch(JSONException e) {
				Log.d("DEBUG", "Error in ImageResult: "+ e.getMessage());
				e.printStackTrace();
			}
		}
		
		return imageResults;
	}

}
