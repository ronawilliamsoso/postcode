package com.demo.postcode.controller;


import com.demo.postcode.model.User;
import com.demo.postcode.repository.UserRepository;
import com.demo.postcode.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController{

	@Autowired
	private UserService userService;

	@ApiOperation(value = "find by id")
	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	public Object findById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();

		User  user = userService.findById(id);
		map.put("status", 200);
		map.put("user", Optional.empty());
		return map;

	}
}
