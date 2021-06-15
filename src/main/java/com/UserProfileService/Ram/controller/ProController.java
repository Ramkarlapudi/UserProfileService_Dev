package com.UserProfileService.Ram.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserProfileService.Ram.dto.UserProfileDTO;
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

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/getDefaultUser")
	public String getUserData() {
		return "Ram Karlapudi";
	}

	@GetMapping("/getusers")
	public ResponseEntity<ArrayList<UserProfileDTO>> getusers() {

		ArrayList<UserProfileEntity> userList = userServiceImpl.getAllUsers();
		ArrayList<UserProfileDTO> userDtoList = (ArrayList<UserProfileDTO>) userList.stream()
				.map(list -> modelMapper.map(list, UserProfileDTO.class)).collect(Collectors.toList());

		return ResponseEntity.ok().body(userDtoList);

	}

	@GetMapping("/getusersbyname/{name}")
	public UserProfileDTO getUserbyName(@PathVariable("name") String name) {
		LOGGER.info("Calling getUserbyName from Controller - User Name ::  " + name);
		UserProfileEntity userEntity = userServiceImpl.getUserBYname(name);
		UserProfileDTO userProfileDTOResponse = modelMapper.map(userEntity, UserProfileDTO.class);
		return userProfileDTOResponse;

	}

	@PostMapping("/uploadProfile")
	public UserProfileDTO uploadEntity(@RequestBody UserProfileDTO userProfileDTO) {
		LOGGER.info("Calling UserProfileUpload from Controller " + userProfileDTO.toString());
		UserProfileEntity userProfileEntity = modelMapper.map(userProfileDTO, UserProfileEntity.class);
		UserProfileEntity user = userServiceImpl.uploadUser(userProfileEntity);
		UserProfileDTO userProfileDTOResponse = modelMapper.map(user, UserProfileDTO.class);
		return userProfileDTOResponse;
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
