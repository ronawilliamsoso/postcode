package com.demo.postcode.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Result< T >{

  @ApiModelProperty( value = "200:success,201:fail" )
  private int status;

  @ApiModelProperty( value = "object,list..." )
  private T data;

  @ApiModelProperty( value = "message" )
  private String message;
}
