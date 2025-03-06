package com.abc.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension.JpaRepositoryRegistrationAotProcessor;

import com.abc.mobilestore.entity.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Integer> {

}
