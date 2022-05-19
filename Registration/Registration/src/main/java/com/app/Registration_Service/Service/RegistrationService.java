package com.app.Registration_Service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Registration_Service.Entity.User;
import com.app.Registration_Service.Repository.RegistrationRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registerRepo;
	
	//save user data
	public User saveUser(User user)
	{
		return registerRepo.save(user);
	}
	
	//User is already exist or not
	public User fetchUserByUsername(String username)
	{
		return registerRepo.findByUsername(username);
	}
	
	
	public User fetchUserByUsername(String username,String password)
	{
		return registerRepo.findByUsernameAndPassword(username, password);
	}
	
}
