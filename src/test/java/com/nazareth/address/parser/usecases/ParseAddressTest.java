package com.nazareth.address.parser.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

import com.nazareth.address.parser.usecases.exceptions.AddressNullOrEmptyException;
import com.nazareth.address.parser.usecases.factories.AddressParserFactory;
import com.nazareth.address.parser.usecases.impl.ParseAddressWithTextNumber;
import com.nazareth.address.parser.usecases.impl.ParseGermanAddress;
import com.nazareth.address.parser.usecases.impl.ParseInternationalAddress;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

@RunWith(MockitoJUnitRunner.class)
public class ParseAddressTest {

  private ParseAddress parseAddress;

  private AddressParserFactory addressParserFactory;

  @Mock private ApplicationContext applicationContext;

  @Before
  public void setUp() throws Exception {
    addressParserFactory = new AddressParserFactory(applicationContext);
    parseAddress = new ParseAddress(addressParserFactory);
  }

  @Test
  public void shouldParseCorrectlySimpleCases() {

    when(applicationContext.getBean(ParseGermanAddress.class)).thenReturn(new ParseGermanAddress());

    String adress = "Winterallee 3";
    String[] adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Winterallee", "3");

    adress = "Musterstrasse 45";
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Musterstrasse", "45");

    adress = "Blaufeldweg 123B";
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Blaufeldweg", "123B");
  }

  @Test
  public void shouldParseCorrectlyMoreComplicatedCases() {

    when(applicationContext.getBean(ParseGermanAddress.class)).thenReturn(new ParseGermanAddress());

    String adress = "Am Bächle 23";
    String[] adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Am Bächle", "23");

    adress = "Auf der Vogelwiese 23 b";
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Auf der Vogelwiese", "23 b");
  }

  @Test
  public void shouldParseCorrectlyOtherCountries() {

    when(applicationContext.getBean(ParseInternationalAddress.class))
        .thenReturn(new ParseInternationalAddress());

    String adress = "4, rue de la revolution";
    String[] adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("rue de la revolution", "4");

    adress = "200 Broadway Av";
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Broadway Av", "200");

    when(applicationContext.getBean(ParseGermanAddress.class)).thenReturn(new ParseGermanAddress());

    adress = "Calle Aduana, 29";
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Calle Aduana", "29");

    when(applicationContext.getBean(ParseAddressWithTextNumber.class))
        .thenReturn(new ParseAddressWithTextNumber());

    adress = "Calle 39 No 1540";
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Calle 39", "No 1540");
  }

  @Test
  public void shouldThrowExceptionWhenAddressIsNull() {
    Throwable thrown = catchThrowable(() -> parseAddress.execute(null));
    assertThat(thrown).isExactlyInstanceOf(AddressNullOrEmptyException.class);
    assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");
  }

  @Test
  public void shouldThrowExceptionWhenAddressIsEmpty() {
    Throwable thrown = catchThrowable(() -> parseAddress.execute(""));
    assertThat(thrown).isExactlyInstanceOf(AddressNullOrEmptyException.class);
    assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");
  }
}
