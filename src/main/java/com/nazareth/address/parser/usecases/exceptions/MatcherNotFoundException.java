package com.nazareth.address.parser.usecases.exceptions;

public class MatcherNotFoundException extends RuntimeException {

  public MatcherNotFoundException(String address) {
    super(String.format("No Matcher found for address %s", address));
  }
}
