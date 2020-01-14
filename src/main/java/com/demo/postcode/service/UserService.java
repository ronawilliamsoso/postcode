package com.demo.postcode.service;

import com.demo.postcode.model.User;
import com.demo.postcode.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class UserService{

	private  UserRepository userRepository;

	public User findById(Integer userId) {

		return userRepository.findById(userId).get();
	}


	public void add(User user) {

		 userRepository.save(user);
	}


	public void deleteById(Integer userId){
		userRepository.deleteById(userId);
	}



//	public List<User> findByAgeSmallerThan(Integer age) {
//		Integer size = 10;
//		Integer page = 0;
//		String sort1 = "firstName";
//		Sort sort = new Sort(Sort.Direction.ASC,sort1);
//		Pageable pageable = PageRequest.of(page,size,sort);
//
//		List<Customer> list = customerRepository.findByAgeLessThanEqual(age, pageable);
//		return list;
//
//	}

}
