package com.lockerz.service.user.services;

import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class ServiceImpl implements Service {
	
	// need this
	public enum Nodes { LOCATOR, POD1, POD2, POD3, POD4, POD5, POD6 };
	
	// need these
	protected HibernateTemplate[] pods;
}
