package com.demo.postcode;

import com.mifmif.common.regex.Generex;

/**
 * @program: postcode
 * @description:
 * @author: Wei.Wang
 * @create: 2020-01-15 23:53
 **/

public class test{

  public static void main(String[] args){

      String first_name ,last_name,postcode;
      Generex generex;


      generex = new Generex("[a-zA-ZäöüÄÖÜß]{2,30}");
      first_name = generex.random();
      System.out.println(first_name);

      generex = new Generex(".{1,30}");
      last_name = generex.random();
      System.out.println(last_name);

      generex = new Generex("\\d{5}");
      postcode = generex.random();
      System.out.println(postcode);





  }

}

