package com.nazareth.address.parser.usecases.exceptions;

public class NoParserFoundException extends RuntimeException {

  public NoParserFoundException(String address) {
    super(String.format("No parser found for address %s", address));
  }
}
