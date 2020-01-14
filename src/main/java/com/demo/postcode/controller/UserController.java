package com.demo.postcode.controller;

import com.demo.postcode.common.Result;
import com.demo.postcode.common.ResultFactory;
import com.demo.postcode.common.Status;
import com.demo.postcode.model.User;
import com.demo.postcode.repository.UserRepository;
import com.demo.postcode.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController{

	@Autowired
	private  UserService userService;

	@Autowired
	private UserRepository userRepository;

	private final BigDecimal MONTHLYCOST = new BigDecimal(12.5);

	@ApiOperation(value = "find by id")
	@GetMapping(value = "/findOne/{userId}")
	public Result<User> findById(@PathVariable(value = "userId") @NonNull Integer userId) {

		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()){
			return  ResultFactory.getInstance(user.get());
		}else{
			return  ResultFactory.getInstance(Status.FAIL,null,"user not exist");
		}
	}

	@ApiOperation(value = "delete by id")
	@GetMapping(value = "/deleteOne/{userId}")
	public Result<User> deleteById(@PathVariable(value = "userId") @NonNull Integer userId) {

		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()){
			userRepository.deleteById(userId);
			return ResultFactory.getInstance();
		}else{
			return ResultFactory.getFailInstance("user not exist");
		}
	}

	@ApiOperation(value = "add one user")
	@PostMapping(value="/create")
	public Object create(@RequestBody User user){ // @Validated

		userService.add(user);
		return  ResultFactory.getInstance(Status.SUCCESS,null,"success");
	}

	@ApiOperation(value = "deduct a user's cost")
	@GetMapping(value = "/deductMonthlyCost")
	public Object deductStock(@NonNull Integer userId){

		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()){
			User u = optionalUser.get();
			if(u.getBalance().compareTo(MONTHLYCOST) >=0 ){
				u.setBalance(u.getBalance().subtract(MONTHLYCOST));
				userRepository.save(u);
				return ResultFactory.getInstance();
			}else{
				return ResultFactory.getFailInstance("balance not sufficient");
			}
		}else{
			return ResultFactory.getFailInstance("user not exist");

		}
	}
}
