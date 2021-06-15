package com.UserProfileService.Ram.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserProfileService.Ram.entity.UserProfileEntity;
import com.UserProfileService.Ram.exception.UserExceptions;
import com.UserProfileService.Ram.service.UserServiceImpl;

@RestController
@RequestMapping("/user-service")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ProController {
	private static final Logger LOGGER = LogManager.getLogger(ProController.class);
	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("/getDefaultUser")
	public String getUserData() {
		return "Ram Karlapudi";
	}

	@GetMapping("/getusers")
	public ArrayList<UserProfileEntity> getusers() {

		return userServiceImpl.getAllUsers();

	}

	@GetMapping("/getusersbyname/{name}")
	public UserProfileEntity getUserbyName(@PathVariable("name") String name) {
		System.out.println("calling getUserbyName " + name);
		UserProfileEntity userList = userServiceImpl.getUserBYname(name);
		return userList;

	}

	@PostMapping("/uploadProfile")
	public UserProfileEntity uploadEntity(@RequestBody UserProfileEntity userProfileEntity) {
		LOGGER.info("Calling UserProfileUpload from Controller "+ userProfileEntity.toString() );
		UserProfileEntity user = userServiceImpl.uploadUser(userProfileEntity);
		return user;	
	}

	@PostMapping("/Rigester")
	public UserProfileEntity userRegistration(@RequestBody UserProfileEntity userProfileEntity) {
		if (userProfileEntity != null) {
			userProfileEntity.setVerified("NO");
			return userServiceImpl.registerUser(userProfileEntity);
		} else {
			throw new UserExceptions("User Data is Empty to Register ");
		}

	}

	@DeleteMapping("/deleteById/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		userServiceImpl.deleteUser(id);
	}

}
