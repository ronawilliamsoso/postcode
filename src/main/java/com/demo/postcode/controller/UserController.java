package com.demo.postcode.controller;

import com.demo.postcode.common.Result;
import com.demo.postcode.common.ResultFactory;
import com.demo.postcode.model.User;
import com.demo.postcode.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/user" )
@RequiredArgsConstructor
public class UserController{

  private final UserRepository userRepository;

  @ApiOperation( value = "find by id" )
  @GetMapping( value = "/findOne/{userId}" )
  public Result findById(@PathVariable( value = "userId" ) @NonNull final Integer userId){

    Optional< User > user = userRepository.findById(userId);
    if( user.isPresent() ){
      return ResultFactory.getSuccessResult(user.get());
    }
    else{
      return ResultFactory.getFailResult("user not exist");
    }
  }

  @ApiOperation( value = "delete by id" )
  @DeleteMapping( value = "/deleteOne/{userId}" )
  public Result< User > deleteById(@PathVariable( value = "userId" ) @NonNull final Integer userId){

    Optional< User > optionalUser = userRepository.findById(userId);
    if( optionalUser.isPresent() ){
      userRepository.deleteById(userId);
      return ResultFactory.getSuccessResult();
    }
    else{
      return ResultFactory.getFailResult("user not exist");
    }
  }

  @ApiOperation( value = "add one user" )
  @PostMapping( value = "/register" )
  public Result register(@RequestBody @Validated final User user){
    if(user.getUserId()!=null){
      return ResultFactory.getFailResult("userId is not null");
    }
    else{
      userRepository.save(user);
      return ResultFactory.getSuccessResult();
    }
  }

  @ApiOperation( value = "modify one user" )
  @PostMapping( value = "/update" )
  public Result< Object > update(@RequestBody final User user){
    if( user.getUserId() != null ){
      User userOld = userRepository.getOne(user.getUserId());
      if( userOld != null ){
        copyNonNullProperties(user ,userOld);
        userRepository.save(userOld);
        return ResultFactory.getSuccessResult();
      }
      else{
        return ResultFactory.getFailResult("user not exist");
      }
    }
    else{
      return ResultFactory.getFailResult("user id is null");
    }
  }

  public static void copyNonNullProperties(Object src ,Object target){

    BeanUtils.copyProperties(src ,target ,getNullPropertyNames(src));
  }

  public static String[] getNullPropertyNames(Object source){
    final BeanWrapper src = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set< String > emptyNames = new HashSet< String >();
    for( PropertyDescriptor pd : pds ){
      Object srcValue = src.getPropertyValue(pd.getName());
      if( srcValue == null ){ emptyNames.add(pd.getName()); }
    }
    String[] result = new String[ emptyNames.size() ];
    return emptyNames.toArray(result);
  }

}
