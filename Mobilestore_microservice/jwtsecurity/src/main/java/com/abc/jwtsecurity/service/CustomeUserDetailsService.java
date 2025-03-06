package com.abc.jwtsecurity.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abc.jwtsecurity.entity.RoleEntity;
import com.abc.jwtsecurity.entity.UserEntity;
import com.abc.jwtsecurity.repository.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

		Optional<UserEntity> optionalUserEntity =  userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		
		if(optionalUserEntity.isEmpty()) {
			throw new UsernameNotFoundException("User Not Existing");
		}
		
		UserEntity userEntity = optionalUserEntity.get();
		
//		userEntity.getRoles();
		
		List<RoleEntity> userRoles = userEntity.getRoles();
		
		List<GrantedAuthority> authorities = userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		User user = new User(usernameOrEmail, userEntity.getPassword(),authorities);
		
		
		return user;
	}

}
