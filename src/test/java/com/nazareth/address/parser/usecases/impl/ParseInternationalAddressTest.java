package com.nazareth.address.parser.usecases.impl;

import static com.nazareth.address.parser.fixtures.AddressFixture.BROADWAY_AV_ADDRESS;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ParseInternationalAddressTest {

  @Test
  public void should_parse_an_international_address_() {
    ParseInternationalAddress parseInternationalAddress = new ParseInternationalAddress();
    final String[] addressParsed = parseInternationalAddress.parse(BROADWAY_AV_ADDRESS);
    assertThat(addressParsed)
        .as("The size of array must be 2")
        .hasSize(2)
        .as("The array should contains Broadway Av and 200")
        .containsExactly("Broadway Av", "200");
  }
}
