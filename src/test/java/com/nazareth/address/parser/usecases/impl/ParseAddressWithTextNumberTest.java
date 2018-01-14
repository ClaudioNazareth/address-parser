package com.nazareth.address.parser.usecases.impl;

import static com.nazareth.address.parser.fixtures.AddressFixture.CALLE_39_ADDRESS;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ParseAddressWithTextNumberTest {

  @Test
  public void should_parse_an_address_with_text_number() {
    ParseAddressWithTextNumber parseAddressWithTextNumber = new ParseAddressWithTextNumber();
    final String[] addressParsed = parseAddressWithTextNumber.parse(CALLE_39_ADDRESS);
    assertThat(addressParsed)
        .as("The size of array must be 2")
        .hasSize(2)
        .as("The array should contains Calle 39 and No 1540")
        .containsExactly("Calle 39", "No 1540");
  }
}
