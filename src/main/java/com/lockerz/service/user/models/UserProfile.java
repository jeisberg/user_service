package com.lockerz.service.user.models;

import java.util.Map;

public class UserProfile {

	private long id;
	private String token;

	public UserProfile() {
		super();
	}
	
	public UserProfile(Map<String, Object> map) {
		// sanity check
		if(map.get("id") != null) {
			// cast here
			this.id = new Long((Integer) map.get("id"));
		}
		// sanity check
		if(map.get("token") != null) {
			// cast here
			this.token = (String) map.get("token");
		}
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", token=" + token + "]";
	}
}
