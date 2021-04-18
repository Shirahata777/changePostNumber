package com.server.repository;


public class Return {

	private String post;

	private String searchWord;
	private String resultFullNameAddr;
	private String lat;
	private String lon;
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	public String getResultFullNameAddr() {
		return resultFullNameAddr;
	}
	public void setResultFullNameAddr(String resultFullNameAddr) {
		this.resultFullNameAddr = resultFullNameAddr;
	}
	
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
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


}
