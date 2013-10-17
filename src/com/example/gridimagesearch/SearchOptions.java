package com.example.gridimagesearch;

import java.io.Serializable;

public class SearchOptions implements Serializable {

	private static final long serialVersionUID = 6450477959391681605L;
	
	private String imageSize;
	private String colorFilter;
	private String imageType;
	private String siteFilter;
	
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
	
	public String getImageSize() {
		return this.imageSize;
	}
	
	public void setColorFilter(String colorFilter) {
		this.colorFilter = colorFilter;
	}
	
	public String getColorFilter() {
		return this.colorFilter;
	}
	
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public String getImageType() {
		return this.imageType;
	}
	
	public void setSiteFilter(String siteFilter) {
		this.siteFilter = siteFilter;
	}
	
	public String getSiteFilter() {
		return this.siteFilter;
	}
	
	@Override
	public String toString() {
		return "{ imageSize: " + getImageSize() + ", colorFilter: " + getColorFilter() + ", imageType: " + getImageType() + ", siteFilter: " + getSiteFilter() + " }";
	}

}
