package com.foodproject.users;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements UsersInterface {

	@Autowired
	UsersRepository userRepo;

	// =========================================================

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String signUp(Users user) {
		
		if (getUserEmail(user.getEmail())) {
			return "email exists";
		} else {
			// Invoking 2FA micro-service to save the details in db from 2FA micro-service...
			
			String url = "http://localhost:8081/fa/auth";
			return restTemplate.postForObject(url, user, String.class);

		}
	}

	@Override
	public boolean getUserEmail(String email) {
		
		int e = userRepo.getUserByEmail(email);
		if (e >= 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String signIn(String email, String userPassword) {
		
		return (userRepo.getUserByCred(email, userPassword) > 0
				? "Login Successful!" : "Login Failed!" );
		
//				? "{\"response\":\"Login Successfull!\"}" : "{\"response\":\"Login Failed!\"}");
				
	}

	@Override
	public Optional<Users> getDetailsByUserId(Integer id) {
		return userRepo.findById(id);
	}

	@Override
	public String save(Users user) {

		return (userRepo.save(user) != null ? "User Sign-up Successfull !" : "Something went wrong while saving your data! Please try again...");
			
	}
	
	

	// =========================================================
	
	
//	To get details of user by users email...
	public Optional<Users> getUserDetailsByEmail(String email) {
		
		return userRepo.findByEmail(email);
	}

//	To delete a users detail if the user exists in Database...
	public String deleteUserById(Integer id) {
		
		if(userRepo.existsById(id) == true) {
			userRepo.deleteById(id);
			return "User Has been removed successfully!"; 
		}
		else {
			return "User doesn't exist!";
		}
	}

}
