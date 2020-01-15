package com.demo.postcode;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.demo.postcode.model.User;
import com.demo.postcode.repository.UserRepository;
import com.demo.postcode.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BDDMockito{

  @Mock
  UserRepository UserRepository;

  @InjectMocks
  UserService UserService ;


  @Test
  @DisplayName("mockito")
  public void compare_mockito(){
    when(UserRepository.existsById(44)).thenReturn(false);//先决条件： 不存在 44 这个用户
    User mData = User.builder().firstName("wei").lastName("wang").postcode("40223").build();
    UserService.addUser(mData);
    verify(UserRepository).save(mData);
  }

  @Test
  @DisplayName("bddmockito")
  public void compare_bddmockito(){

    given(UserRepository.existsById(44)).willReturn(false);
    User mData = User.builder().firstName(matches("^[a-zA-Z] {2,30}$")).lastName("wang").postcode("40223").build();
    UserService.addUser(mData);
    verify(UserRepository).save(mData);

  }

  @Test
  @DisplayName("mockito: when->thenReturn,...verify ")
  public void mockito(){
    Integer userId = anyInt();
    String firstName = matches("^.{2,30}$+");
    String lastName = matches("^.{1,30}$+");

    when(UserRepository.existsById(userId)).thenReturn(false);
    User mData = User.builder().userId(userId).firstName("wei").lastName("wang").postcode("40223").build();
    UserService.addUser(mData);
    verify(UserRepository).save(mData);
  }

  @Test
  @DisplayName("bddmockito: given->willreturn,...then->should")
  public void bddmockito() {
    Integer userId = anyInt();
    given(UserRepository.existsById(userId)).willReturn(false);
    User mData = User.builder().userId(userId).firstName("wei").lastName("wang").postcode("40223").build();
    UserService.addUser(mData);
    then(UserRepository).should().save(mData);
  }
}
