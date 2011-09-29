package com.lockerz.service.user.models;

import java.sql.Timestamp;

public class UserLookupModelImpl extends ModelImpl {
	
	private long id = -1;
	private int podId = -1;
	private String email = null;
	private String normalizedEmail = null;
	private Timestamp created = null;
	private Timestamp modified = null;
	
	public long getId() {
		// return here
		return id;
	}
	
	public void setId(long id) {
		// set here
		this.id = id;
	}
	
	public int getPodId() {
		// return here
		return podId;
	}
	
	public void setPodId(int podId) {
		// set here
		this.podId = podId;
	}
	
	public String getEmail() {
		// return here
		return email;
	}
	
	public void setEmail(String email) {
		// set here
		this.email = email;
	}
	
	public String getNormalizedEmail() {
		// return here
		return normalizedEmail;
	}
	
	public void setNormalizedEmail(String normalizedEmail) {
		// set here
		this.normalizedEmail = normalizedEmail;
	}
	
	public Timestamp getCreated() {
		// return here
		return created;
	}
	
	public void setCreated(Timestamp created) {
		// set here
		this.created = created;
	}
	
	public Timestamp getModified() {
		// return here
		return modified;
	}
	
	public void setModified(Timestamp modified) {
		// set here
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "UserLookupModelImpl [id=" + id + ", podId=" + podId
				+ ", email=" + email + ", normalizedEmail=" + normalizedEmail
				+ ", created=" + created + ", modified=" + modified + "]";
	}
}
