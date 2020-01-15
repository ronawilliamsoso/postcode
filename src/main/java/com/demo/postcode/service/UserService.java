package com.demo.postcode.service;

import com.demo.postcode.common.Status;
import com.demo.postcode.model.User;
import com.demo.postcode.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

	private final  UserRepository userRepository;

	private final List<String> allPostcodeService = List.of("40223","40224","40225");

	public int addUser(User user){
		if(checkIfPostCodeExist(user.getPostcode())){
			userRepository.save(user);
			return Status.SUCCESS;
		}else{
			return Status.FAIL;
		}
	}

	/**
	 * @author wei wang
	 * when update an users postcode,userId is not null,user should be existing,new postcode should be exist too
	 * */

	public String updateUser(User user){
		if(user.getUserId()!=null){
			if(userRepository.existsById(user.getUserId())==true){
				if(checkIfPostCodeExist(user.getPostcode())){
					User updateUser = User.builder().userId(user.getUserId()).postcode(user.getPostcode()).build();
					userRepository.save(updateUser);
					return "";
				}else{
					return "invalid postcode";
				}
			}else{
				return "user not exist";
			}
		}else{
			return "user id null";
		}
	}

	private Boolean checkIfPostCodeExist(String postcode){
		if(allPostcodeService.contains(postcode)){
			return true;
		}else{
			return false;
		}
	}
}
