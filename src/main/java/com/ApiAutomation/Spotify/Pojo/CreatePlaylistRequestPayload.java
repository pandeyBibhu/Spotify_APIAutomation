package com.ApiAutomation.Spotify.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePlaylistRequestPayload {

	private  String name;
	private  String description;
	
	@JsonProperty("public")
	private  boolean _public;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setIsPublic(boolean isPublic) {
		this._public = isPublic;
	}

	public boolean IsPublic() {
		return _public;
	}
}
