package com.nazareth.address.parser.usecases.factories;

import static com.nazareth.address.parser.fixtures.AddressFixture.AUF_DER_VOGELWIESE_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.BROADWAY_AV_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.CALLE_39_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.EMPTY_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.NO_PARSER_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.NULL_ADDRESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

import com.nazareth.address.parser.usecases.AddressParser;
import com.nazareth.address.parser.usecases.exceptions.AddressNullOrEmptyException;
import com.nazareth.address.parser.usecases.exceptions.NoParserFoundException;
import com.nazareth.address.parser.usecases.impl.ParseAddressWithTextNumber;
import com.nazareth.address.parser.usecases.impl.ParseGermanAddress;
import com.nazareth.address.parser.usecases.impl.ParseInternationalAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

@RunWith(MockitoJUnitRunner.class)
public class AddressParserFactoryTest {

  @InjectMocks private AddressParserFactory addressParserFactory;

  @Mock private ApplicationContext applicationContext;

  @Test
  public void when_address_with_text_number_should_return_ParseAddressWithTextNumber_class() {
    when(applicationContext.getBean(ParseAddressWithTextNumber.class))
        .thenReturn(new ParseAddressWithTextNumber());

    final AddressParser addressParser = addressParserFactory.getParser(CALLE_39_ADDRESS);

    assertThat(addressParser)
        .as("addressParser should not be null")
        .isNotNull()
        .as("The instance of address parser should be ParseAddressWithTextNumber")
        .isExactlyInstanceOf(ParseAddressWithTextNumber.class);
  }

  @Test
  public void when_address_is_german_should_return_ParseGermanAddress_class() {
    when(applicationContext.getBean(ParseGermanAddress.class)).thenReturn(new ParseGermanAddress());

    final AddressParser addressParser = addressParserFactory.getParser(AUF_DER_VOGELWIESE_ADDRESS);

    assertThat(addressParser)
        .as("addressParser should not be null")
        .isNotNull()
        .as("The instance of address parser should be ParseGermanAddress")
        .isExactlyInstanceOf(ParseGermanAddress.class);
  }

  @Test
  public void when_address_is_international_should_return_ParseInternationalAddress_class() {
    when(applicationContext.getBean(ParseInternationalAddress.class))
        .thenReturn(new ParseInternationalAddress());

    final AddressParser addressParser = addressParserFactory.getParser(BROADWAY_AV_ADDRESS);

    assertThat(addressParser)
        .as("addressParser should not be null")
        .isNotNull()
        .as("The instance of address parser should be ParseInternationalAddress")
        .isExactlyInstanceOf(ParseInternationalAddress.class);
  }

  @Test
  public void when_no_parser_found_for_address_should_throw_NoParserFoundException() {
    Throwable thrown = catchThrowable(() -> addressParserFactory.getParser(NO_PARSER_ADDRESS));
    assertThat(thrown).isExactlyInstanceOf(NoParserFoundException.class);
    assertThat(thrown).hasMessageContaining("No parser found for address");
  }

  @Test
  public void when_address_is_null_or_empty_should_throw_AddressNullOrEmptyException() {
    Throwable thrown = catchThrowable(() -> addressParserFactory.getParser(NULL_ADDRESS));
    assertThat(thrown).isExactlyInstanceOf(AddressNullOrEmptyException.class);
    assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");

    thrown = catchThrowable(() -> addressParserFactory.getParser(EMPTY_ADDRESS));
    assertThat(thrown).isExactlyInstanceOf(AddressNullOrEmptyException.class);
    assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");
  }
}
