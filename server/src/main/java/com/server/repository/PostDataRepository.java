package com.server.repository;

public class PostDataRepository {
	
	private String post;
	private String lat;
	private String lon;
	private String postName;
	private String fullAddrName;
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	public String getFullAddrName() {
		return fullAddrName;
	}
	public void setFullAddrName(String fullAddrName) {
		this.fullAddrName = fullAddrName;
	}
	

}
