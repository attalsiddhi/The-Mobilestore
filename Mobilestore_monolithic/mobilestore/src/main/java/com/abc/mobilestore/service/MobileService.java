package com.abc.mobilestore.service;

import java.util.List;

//import org.springframework.stereotype.Service;

import com.abc.mobilestore.entity.Mobile;

//@Service
public interface MobileService {
	
	Mobile saveMobile(Mobile mobile);
	
	Mobile getMobileById(int mobileId);
	
	List<Mobile> getAllMobiles();
	
	Mobile updateMobile(Mobile mobile);
	
	void deleteMobile(int mobileId);
}
