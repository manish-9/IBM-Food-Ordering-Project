package com.foodproject.users;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsersController {

	@ApiModelProperty(notes="autowired service for this api")
	@Autowired
	UserService service;

//	To initiate user email verification during user sign-up...
	@ApiOperation(value = "creating a new user", notes = "Hit this URL for creating an new User", response = List.class)
	@PostMapping("/signup")
	public String signUp(@RequestBody Users user) {
		return service.signUp(user);
	}
	
//	To login into a user account using users email and password...
	@ApiOperation(value = "Logging into the User account", notes = "Hit this URL for logging into the User account")
	@RequestMapping("/signin/{email}/{password}")
	public String signInWithEmail(@PathVariable String email, @PathVariable String password) {
		
		return service.signIn(email, password);
	
	}

//	To retrieve user data from database using users ID...
	@ApiOperation(value = "Find the User by their id", notes = "Hit this URL for getting the User by their ID")
	@RequestMapping("/{id}")
	Optional<Users> getDetailsByUserId(@PathVariable Integer id) {
		return service.getDetailsByUserId(id);
	}
	
	
//	To retrieve user data by users email...
	@ApiOperation(value = "Find the User by their email", notes = "Hit this URL for getting the User by their Email")
	@RequestMapping("/getByEmail/{email}")
	Optional<Users> getUserDetailsByEmail(@PathVariable String email) {
		
		return service.getUserDetailsByEmail(email);
	}


//	To insert new users data into database after email verification...
	@ApiOperation(value = "for saving the User", notes = "Hit this URL for saving the User")
	@PostMapping("/save")
	String saveUser(@RequestBody Users user) {
		return service.save(user);
	}

	
//	To delete a user through users ID...	
	@ApiOperation(value = "for deleting a User", notes = "Hit this URL for deleting a User")
	@DeleteMapping("/delete/{id}")
	String deleteUser(@PathVariable Integer id) {
		return service.deleteUserById(id);
	}

}
