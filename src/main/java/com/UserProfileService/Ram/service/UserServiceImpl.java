package com.UserProfileService.Ram.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.UserProfileService.Ram.entity.UserProfileEntity;
import com.UserProfileService.Ram.exception.UploadProfileException;
import com.UserProfileService.Ram.exception.UserExceptions;
import com.UserProfileService.Ram.repository.UserProfileRepo;

@Component
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
	@Autowired
	private UserProfileRepo userProfileRepo;

	@Override
	public ArrayList<UserProfileEntity> getAllUsers() {
		LOGGER.info("--------------> Entering  getAllUsers");
		ArrayList<UserProfileEntity> usersList = (ArrayList<UserProfileEntity>) userProfileRepo.findAll();
		LOGGER.info("--------------> Exiting  getAllUsers " + usersList.size());
		return usersList;
	}

	@Override
	public Optional<UserProfileEntity> getUserById(int id) {
		Optional<UserProfileEntity> user = userProfileRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserExceptions("User id not available in DB please check again " + id);
		}
		return user; 

	}

	public UserProfileEntity getUserBYname(String userName) {
		System.out.println("calling getUserbyName from service " + userName);
		UserProfileEntity userEntity = userProfileRepo.findByUserName(userName);
		// System.out.println("calling getUserbyName from service "+
		// username.toString());

		if (userEntity == null) {
			throw new com.UserProfileService.Ram.exception.UserExceptions("User Not found");
		}
		return userEntity;

	}

	public UserProfileEntity uploadUser(UserProfileEntity userProfileEntity) {

		LOGGER.info("--------------> Entering  uploadUser");
		if (userProfileEntity.getUsername() == null || userProfileEntity.getUsername() == ""
				|| userProfileEntity.getEmail() == null || userProfileEntity.getEmail() == "") {
			throw new UploadProfileException("UserName/Email is Mandatory ");
		}
		Optional<UserProfileEntity> byuser = userProfileRepo.findById(userProfileEntity.getUserid());
		UserProfileEntity savedUser = null;
		if (byuser.isPresent()) {
			UserProfileEntity userEntity = byuser.get();
			userEntity.setFirstname(userProfileEntity.getFirstname());
			userEntity.setLastname(userProfileEntity.getLastname());
			// userEntity.setPasscode(userProfileEntity.getPasscode());
			userEntity.setPhone(userProfileEntity.getPhone());
			userEntity.setEmail(userProfileEntity.getEmail());
			userEntity.setUsername(userProfileEntity.getUsername());
			userEntity.setCountry(userProfileEntity.getCountry());
			userEntity.setState(userProfileEntity.getState());
			userEntity.setAddress(userProfileEntity.getAddress());
			// userEntity.setPasscode(userProfileEntity.getPasscode());
			userEntity.setPincode(userProfileEntity.getPincode());
			savedUser = userProfileRepo.save(userEntity);
			return savedUser;
		} else {
			savedUser = userProfileRepo.save(userProfileEntity);
		}

		LOGGER.info("--------------> Exiting  uploadUser");
		return savedUser;

	}

	public UserProfileEntity registerUser(UserProfileEntity userProfileEntity) {
		return userProfileRepo.save(userProfileEntity);
	}

	public void deleteUser(int Id) {
		Optional<UserProfileEntity> byuserId = userProfileRepo.findById(Id);
		if (byuserId.isPresent()) {
			userProfileRepo.deleteById(Id);
		} else {
			throw new UserExceptions("User Not Found in DB to delete");
		}

	}

}
