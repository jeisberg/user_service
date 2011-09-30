package com.lockerz.service.user.models;

import java.util.Date;
import java.util.Map;
import java.sql.Timestamp;

public class UserModelImpl implements Model {

	private long id;
	private String username;
	private String password;
	private int status;
	private Date created;
	private Timestamp modified;
	
	public UserModelImpl() {
		super();
	}
	
	public UserModelImpl(Map<String, Object> map) {
		// sanity check
		if(map.get("id") != null) {
			// set here
			this.id = ((Integer) map.get("id")).intValue();
		}
		// sanity check
		if(map.get("username") != null) {
			// set here
			this.username = (String) map.get("username");
		}
		// sanity check
		if(map.get("password") != null) {
			// set here
			this.username = (String) map.get("password");
		}
		// sanity check
		if(map.get("status") != null) {
			// set here
			this.status = ((Integer) map.get("status")).intValue();
		}
		// sanity check
		if(map.get("created") != null) {
			// set here
			this.created = new Date(((Long) map.get("created")).longValue());
		}
		// sanity check
		if(map.get("modified") != null) {
			// set here
			this.modified = new Timestamp(((Long) map.get("created")).longValue());
		}
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Timestamp getModified() {
		return modified;
	}
	
	public void setModified(Timestamp modified) {
		this.modified = modified;
	}
	
	@Override
	public String toString() {
		return "UserModelImpl [id=" + id + ", username=" + username
				+ ", password=" + password + ", status=" + status
				+ ", created=" + created + ", modified=" + modified + "]";
	}
}