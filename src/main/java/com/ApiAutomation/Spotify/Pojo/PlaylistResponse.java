package com.ApiAutomation.Spotify.Pojo;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
	public class PlaylistResponse {

	    private boolean collaborative;
	    private String description;

	    @JsonProperty("external_urls")
	    private ExternalUrls externalUrls;

	    private Followers followers;
	    private String href;
	    private String id;
	    private List<Object> images;

	    @JsonProperty("primary_color")
	    private String primaryColor;

	    private String name;
	    private String type;
	    private String uri;
	    private Owner owner;

	    @JsonProperty("public")
	    private boolean _public;

	    @JsonProperty("snapshot_id")
	    private String snapshotId;

	    private Tracks tracks;

	    // Getters
	    public boolean isCollaborative() {
	        return collaborative;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public ExternalUrls getExternalUrls() {
	        return externalUrls;
	    }

	    public Followers getFollowers() {
	        return followers;
	    }

	    public String getHref() {
	        return href;
	    }

	    public String getId() {
	        return id;
	    }

	    public List<Object> getImages() {
	        return images;
	    }

	    public String getPrimaryColor() {
	        return primaryColor;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getType() {
	        return type;
	    }

	    public String getUri() {
	        return uri;
	    }

	    public Owner getOwner() {
	        return owner;
	    }

	    public boolean isPublic() {
	        return _public;
	    }

	    public String getSnapshotId() {
	        return snapshotId;
	    }

	    public Tracks getTracks() {
	        return tracks;
	    }
	
}