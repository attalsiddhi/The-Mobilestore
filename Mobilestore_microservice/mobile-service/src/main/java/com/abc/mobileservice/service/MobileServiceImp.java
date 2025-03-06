package com.abc.mobileservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.abc.mobileservice.entity.Mobile;
import com.abc.mobileservice.exception.ResourceNotFoundException;
import com.abc.mobileservice.repository.MobileRepository;

@Service
public class MobileServiceImp implements MobileService {
	
	private static final Logger logger = LoggerFactory.getLogger(MobileServiceImp.class);
	
	@Autowired
	private MobileRepository mobileRepository;

	@Override
	public Mobile saveMobile(Mobile mobile) {
		mobileRepository.save(mobile);
		return mobile;
	}

	@Override
	public Mobile getMobileById(int mobileId) {
		
		logger.info("MobileServiceImpl: getMobileById() Started.");
		
		Optional<Mobile> optionalMobile = mobileRepository.findById(mobileId);
		
		if(optionalMobile.isEmpty()) {
			logger.info("Mobile not fount with id: "+ mobileId);
			throw new ResourceNotFoundException("Mobile not existing with id"+mobileId);
		}
		Mobile mobile = optionalMobile.get();
		
		logger.info("MobileServiceImpl: getMobileById() Ended with status code: "+HttpStatus.OK);
		
		return mobile;
	}

	@Override
	public List<Mobile> getAllMobiles() {
		
		logger.info("MobileServiceImpl: getAllMobiles() Started.");
		
		List<Mobile> mobiles = mobileRepository.findAll();
		
		logger.info("MobileServiceImpl: getAllMobiles() Ended with status code: "+HttpStatus.OK);
		
		return mobiles;
	}
}
