package com.ApiAutomation.Spotify.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

	private String href;
	private String id;
	private String type;
	private String uri;

	@JsonProperty("display_name")
	private String displayName;

	@JsonProperty("external_urls")
	private ExternalUrls externalUrls;

	public String getHref() {
		return href;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getUri() {
		return uri;
	}

	public String getDisplayName() {
		return displayName;
	}

	public ExternalUrls getExternalUrls() {
		return externalUrls;
	}

}
