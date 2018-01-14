package com.nazareth.address.parser.controllers;

import com.nazareth.address.parser.controllers.jsons.AddressRequest;
import com.nazareth.address.parser.usecases.ParseAddress;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/address-parser")
public class ParseAddressController {

  private ParseAddress parseAddress;

  @Autowired
  public ParseAddressController(ParseAddress parseAddress) {
    this.parseAddress = parseAddress;
  }

  @PostMapping(
    value = "/parse",
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  @ApiOperation(
    value =
        "Parse an Address with concatenated street names and numbers into a normalized Address\n"
            + " * composed of String of street and String of street-number"
  )
  @ApiResponses({
    @ApiResponse(code = 200, message = "Ok"),
    @ApiResponse(code = 400, message = "Bad Request")
  })
  public String[] parse(@RequestBody @Valid AddressRequest addressRequest) {
    return parseAddress.execute(addressRequest.getAddress());
  }
}
