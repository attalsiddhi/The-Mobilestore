package com.abc.mobileservice.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.mobileservice.entity.Mobile;
import com.abc.mobileservice.service.MobileService;

@RestController
@RequestMapping("/mobile")
public class MobileController {
	
	private static final Logger logger = LoggerFactory.getLogger(MobileController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MobileService mobileService;
	
	@PostMapping("/save")
	public ResponseEntity<Mobile> addMobile(@RequestBody Mobile mobile) {
		mobileService.saveMobile(mobile);
		ResponseEntity<Mobile> responseEntity = new ResponseEntity<>(mobile, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/{mobileId}")
	public ResponseEntity<Mobile> fetchMoblieDetails(@PathVariable int mobileId) {
		
		logger.info("MobileController: fetchAllMobileDetails() Started.");
		
		System.out.println("Mobile Service Running on Port"+env.getProperty("local.server.port"));
		
		Mobile mobile = mobileService.getMobileById(mobileId);
		
		logger.info("MobileController: getMobileDetails() Ended with status code: "+HttpStatus.OK);
	
		ResponseEntity<Mobile> responseEntity = new ResponseEntity<>(mobile, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/all")
//	public List<Mobile> fethAllMobiles(){
//		List<Mobile> mobiles = mobileService.getAllMobiles();
//		ResponseEntity<List<Mobile>> responseEntity = new ResponseEntity<>(mobiles, HttpStatus.OK);
//		return (List<Mobile>) responseEntity;
//	}
	public ResponseEntity<List<Mobile>> fethAllMobiles(){
		
		logger.info("MobileController: fetchAllMobiles Started.");
		
		List<Mobile> mobiles = mobileService.getAllMobiles();
		ResponseEntity<List<Mobile>> responseEntity = new ResponseEntity<>(mobiles, HttpStatus.OK);
		
		logger.info("MobileController: fetchAllMobiles Ended with status code: "+HttpStatus.OK);
		
		return responseEntity;
	}
}
