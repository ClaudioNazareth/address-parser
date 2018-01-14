package com.nazareth.address.parser.controllers.jsons;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = -1677346290852609123L;

  private String message;

  public ErrorResponse(String message) {
    this.message = message;
  }

  public ErrorResponse() {}

  public String getMessage() {
    return message;
  }
}
