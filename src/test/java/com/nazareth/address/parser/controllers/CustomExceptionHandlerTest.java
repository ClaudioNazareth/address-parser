package com.nazareth.address.parser.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.nazareth.address.parser.controllers.jsons.ErrorResponse;
import com.nazareth.address.parser.usecases.exceptions.AddressNullOrEmptyException;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class CustomExceptionHandlerTest {

  private CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();

  @Test
  public void handleCustomException() {

    ErrorResponse errorResponse =
        customExceptionHandler.handleCustomException(
            new MockHttpServletRequest(), new AddressNullOrEmptyException());
    assertThat(errorResponse.getMessage())
        .isEqualTo("Address is null or empty and can not be parsed");
  }
}
