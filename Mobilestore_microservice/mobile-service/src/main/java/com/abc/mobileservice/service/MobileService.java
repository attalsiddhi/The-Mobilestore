package com.abc.mobileservice.service;

import java.util.List;

import com.abc.mobileservice.entity.Mobile;

public interface MobileService {
	
	Mobile saveMobile(Mobile mobile);
	
	Mobile getMobileById(int mobileId);
	
	List<Mobile> getAllMobiles();
}
