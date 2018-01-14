package com.nazareth.address.parser.controllers.jsons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

@ApiModel(value = "AddressRequest", description = "Contains the address to be parsed")
public class AddressRequest implements Serializable {

  private static final long serialVersionUID = 1402303199473401187L;

  @NotNull
  @ApiModelProperty(value = "The address to be parsed", required = true)
  private String address;

  public String getAddress() {
    return address;
  }
}
