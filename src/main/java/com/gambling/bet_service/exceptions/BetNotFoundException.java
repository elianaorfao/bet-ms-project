package com.gambling.bet_service.exceptions;

public class BetNotFoundException extends RuntimeException{

  public BetNotFoundException(String message) {
    super(message);
  }

}
