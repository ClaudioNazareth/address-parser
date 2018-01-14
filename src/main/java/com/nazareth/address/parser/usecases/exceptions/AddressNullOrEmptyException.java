package com.nazareth.address.parser.usecases.exceptions;

public class AddressNullOrEmptyException extends RuntimeException {

  public AddressNullOrEmptyException() {
    super("Address is null or empty and can not be parsed");
  }
}
