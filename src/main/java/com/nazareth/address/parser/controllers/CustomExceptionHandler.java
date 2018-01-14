package com.nazareth.address.parser.controllers;

import com.nazareth.address.parser.controllers.jsons.ErrorResponse;
import com.nazareth.address.parser.usecases.exceptions.AddressNullOrEmptyException;
import com.nazareth.address.parser.usecases.exceptions.MatcherNotFoundException;
import com.nazareth.address.parser.usecases.exceptions.NoParserFoundException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = ParseAddressController.class)
public class CustomExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    AddressNullOrEmptyException.class,
    MatcherNotFoundException.class,
    NoParserFoundException.class
  })
  public ErrorResponse handleCustomException(HttpServletRequest req, Exception e) {
    logger.error("{}", e);
    return new ErrorResponse(e.getMessage());
  }
}
