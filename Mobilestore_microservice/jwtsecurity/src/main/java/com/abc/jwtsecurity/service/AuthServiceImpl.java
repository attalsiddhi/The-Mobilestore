package com.abc.jwtsecurity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abc.jwtsecurity.entity.RoleEntity;
import com.abc.jwtsecurity.entity.UserEntity;
import com.abc.jwtsecurity.repository.RoleRepository;
import com.abc.jwtsecurity.repository.UserRepository;
import com.abc.jwtsecurity.util.JWTTokenUtil;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserEntity register(UserEntity userEntity) {

		// TODO: Check username and email already existing or not. If it is existing throw some exception
		
		// Before saving userEntity encode the password
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		
		List<RoleEntity> userRoles = new ArrayList<>();
		
		List<RoleEntity> roles = userEntity.getRoles();
		
//		userEntity.setRoles(roles);
		
		roles.forEach(r -> {
			Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(r.getId());
			if(optionalRoleEntity.isEmpty()) {
				
			}
			RoleEntity roleEntity = optionalRoleEntity.get();
			userRoles.add(roleEntity);
		});
		
		userEntity.setRoles(userRoles);
		
		userRepository.save(userEntity);
		
		return userEntity;
	}

	@Override
	public String login(String usernameOREmail, String password) {
		
		// write a Logic To validate user with password
		Authentication authentication =  authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usernameOREmail, password));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// If login is success, generate JWT token and return it
		String token = jwtTokenUtil.generateToken(authentication);
		
		return token;
	}

}
