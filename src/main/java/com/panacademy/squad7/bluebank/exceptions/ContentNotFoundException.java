package com.panacademy.squad7.bluebank.exceptions;

public class ContentNotFoundException extends RuntimeException {

  public ContentNotFoundException(String errorMessage) {
    super(errorMessage);
  }

}
