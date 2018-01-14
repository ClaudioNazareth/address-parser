package com.nazareth.address.parser.usecases.impl;

import static com.nazareth.address.parser.fixtures.AddressFixture.AUF_DER_VOGELWIESE_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.NO_PARSER_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.NULL_ADDRESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

import com.nazareth.address.parser.usecases.exceptions.AddressNullOrEmptyException;
import com.nazareth.address.parser.usecases.exceptions.MatcherNotFoundException;
import org.junit.Test;

public class ParseGermanAddressTest {

  @Test
  public void should_parse_an_german_address_() {
    ParseGermanAddress parseGermanAddress = new ParseGermanAddress();
    final String[] addressParsed = parseGermanAddress.parse(AUF_DER_VOGELWIESE_ADDRESS);
    assertThat(addressParsed)
        .as("The size of array must be 2")
        .hasSize(2)
        .as("The array should contains Auf der Vogelwiese and 23 b")
        .containsExactly("Auf der Vogelwiese", "23 b");
  }

  @Test
  public void should_throw_MatcherNotFoundException() {
    ParseGermanAddress parseGermanAddress = new ParseGermanAddress();
    Throwable thrown = catchThrowable(() -> parseGermanAddress.parse(NO_PARSER_ADDRESS));
    assertThat(thrown).isExactlyInstanceOf(MatcherNotFoundException.class);
    assertThat(thrown).hasMessageContaining("No Matcher found for address");
  }

  @Test
  public void with_address_null_should_throw_AddressNullOrEmptyException() {
    ParseGermanAddress parseGermanAddress = new ParseGermanAddress();
    Throwable thrown = catchThrowable(() -> parseGermanAddress.parse(NULL_ADDRESS));
    assertThat(thrown).isExactlyInstanceOf(AddressNullOrEmptyException.class);
    assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");
  }
}
