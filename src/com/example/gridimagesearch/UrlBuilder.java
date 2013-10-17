package com.example.gridimagesearch;

import android.net.Uri;

public class UrlBuilder {

	private static final String REQUEST_URL = "https://ajax.googleapis.com/ajax/services/search/images";
	public static final int DEFAULT_RESULT_SIZE = 8;
	private static final String VERSION_KEY = "v";
	private static final String QUERY_KEY = "q";
	private static final String RESULT_SIZE_KEY = "rsz";
	private static final String COLOR_FILTER_KEY = "imgcolor";
	private static final String IMAGE_SIZE_KEY = "imgsz";
	private static final String IMAGE_TYPE_KEY = "imgtype";
	private static final String SITE_FILTER_KEY = "as_sitesearch";
	private static final String INDEX_KEY = "start";
	
	private static final String VERSION_VALUE = "1.0";
	
	
	public static String buildUrl(String query, SearchOptions options) {
		return buildUrl(query, options, 1);
	}
	
	public static String buildUrl(String query, SearchOptions options, int pageNumber) {
		StringBuilder builder = new StringBuilder(REQUEST_URL);
		builder.append("?" + VERSION_KEY + "=" + VERSION_VALUE);
		builder.append("&" + RESULT_SIZE_KEY + "=" + DEFAULT_RESULT_SIZE);
		builder.append("&" + QUERY_KEY + "=" + Uri.encode(query));
		int index = 0;
		if (pageNumber > 1) {
			index = ((pageNumber - 1) * DEFAULT_RESULT_SIZE) - 1;
		}
		
		builder.append("&" + INDEX_KEY + "="+index);
		
		if (options == null) {
			return builder.toString();
		}
		
		if (options.getImageSize() != null && !options.getImageSize().equals(AdvancedSearchOptionsActivity.DEFAULT_VALUE)) {
			builder.append("&" + IMAGE_SIZE_KEY + "=" + options.getImageSize().toLowerCase());
		}
		
		if (options.getColorFilter() != null && !options.getColorFilter().equals(AdvancedSearchOptionsActivity.DEFAULT_VALUE)) {
			builder.append("&" + COLOR_FILTER_KEY + "=" + options.getColorFilter().toLowerCase());
		}
		
		if (options.getImageType() != null && !options.getImageType().equals(AdvancedSearchOptionsActivity.DEFAULT_VALUE)) {
			builder.append("&" + IMAGE_TYPE_KEY + "=" + options.getImageType().toLowerCase());
		}
		
		if (options.getSiteFilter() != null && !options.getSiteFilter().isEmpty()) {
			builder.append("&").append(SITE_FILTER_KEY).append("=").append(options.getSiteFilter().toLowerCase());
		}
		
		return builder.toString();
		
		
	}
}
