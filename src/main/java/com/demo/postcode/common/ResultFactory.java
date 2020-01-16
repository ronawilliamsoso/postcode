package com.demo.postcode.common;


public class ResultFactory{

  public static < T > Result< T > getResult(int code ,T data ,String msg){

    return new Result< T >(code ,data ,msg);
  }

  public static < T > Result< T > getResult(int code ,String message){

    return new Result< T >(code ,null ,message);
  }

  public static < T > Result< T > getSuccessResult(T data){

    return new Result< T >(200 ,data ,null);
  }

  public static < T > Result< T > getSuccessResult(){

    return new Result(200 ,null ,"success");
  }

  public static < T > Result< T > getFailResult(String message){

    return new Result(201 ,null ,message);
  }

}
