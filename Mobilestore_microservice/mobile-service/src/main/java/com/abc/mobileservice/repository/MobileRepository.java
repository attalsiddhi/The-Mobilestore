package com.abc.mobileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension.JpaRepositoryRegistrationAotProcessor;

import com.abc.mobileservice.entity.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Integer> {

}
