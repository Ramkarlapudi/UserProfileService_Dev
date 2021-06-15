package com.UserProfileService.Ram.service;

import java.util.ArrayList;
import java.util.Optional;

import com.UserProfileService.Ram.entity.UserProfileEntity;

public interface UserService {
	
	public  ArrayList<UserProfileEntity> getAllUsers();
	
    public Optional<UserProfileEntity>  getUserById(int id );	

}
