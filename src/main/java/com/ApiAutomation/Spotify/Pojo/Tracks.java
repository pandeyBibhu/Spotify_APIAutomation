package com.ApiAutomation.Spotify.Pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracks {

	private int limit;
	private int offset;
	private int total;
	private String href;
	private String next;
	private String previous;
	private List<Object> items;

	public int getLimit() {
		return limit;	
	}

	public int getOffset() {
		return offset;
	}

	public int getTotal() {
		return total;
	}

	public String getHref() {
		return href;
	}

	public String getNext() {
		return next;
	}

	public String getPrevious() {
		return previous;
	}

	public List<Object> getItems() {
		return items;
	}

}
