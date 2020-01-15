package com.demo.postcode;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.demo.postcode.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifmif.common.regex.Generex;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = PostcodeApplication.class)
@WebAppConfiguration
public class UserControllerTests{

  @Autowired
  private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;
  private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
  private String first_name ,last_name,postcode;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  @DisplayName("/user/register")
  public void test_create_one_user() throws Exception {
    refreshParams();
    User newUser = User.builder().firstName(first_name).lastName(last_name).postcode( postcode).build();
    mockMvc.perform(post("/user/register").contentType(CONTENT_TYPE).content(asJsonString(newUser))).andDo(print())
                                 .andExpect(status().isOk())
                                 .andExpect(jsonPath("$.message").value("success"));
  }

  @Test
  @DisplayName("/user/findOne")
  public void test_find_a_user_by_id() throws Exception {
    mockMvc.perform(get("/user/findOne/1").accept(CONTENT_TYPE)).andDo(print())
                                 .andExpect(status().isOk())
                                 .andExpect(jsonPath("$.data").isNotEmpty());
  }

  @Test
  @DisplayName("/user/update partially update")
  public void test_update_partially_one_user() throws Exception {
    refreshParams();
    User newUser = User.builder().userId(1).postcode(postcode).build();
    mockMvc.perform(post("/user/register").contentType(CONTENT_TYPE).content(asJsonString(newUser))).andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.message").value("success"));
  }

  @Test
  @DisplayName("/user/deleteOne")
  public void test_delete_one_user_by_id() throws Exception {
    mockMvc.perform(delete("/user/deleteOne/1").accept(CONTENT_TYPE)).andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.message").value("success"));
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void refreshParams() throws Exception {
    first_name = new Generex(".{2,30}").random();

    last_name = new Generex(".{1,30}").random();

    postcode = new Generex("\\d{5}").random();
  }
}