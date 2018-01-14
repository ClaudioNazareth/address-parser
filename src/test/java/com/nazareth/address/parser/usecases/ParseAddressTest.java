package com.nazareth.address.parser.usecases;

import static com.nazareth.address.parser.fixtures.AddressFixture.AM_BACHLE_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.AUF_DER_VOGELWIESE_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.BLAUFELDWEG_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.BROADWAY_AV_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.CALLE_39_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.CALLE_ADUANNA;
import static com.nazareth.address.parser.fixtures.AddressFixture.MUSTERSTRASSE_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.RUE_DE_LA_REVOLUTION_ADDRESS;
import static com.nazareth.address.parser.fixtures.AddressFixture.WINTERALLEE_ADDRESS;
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

    String adress = WINTERALLEE_ADDRESS;
    String[] adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Winterallee", "3");

    adress = MUSTERSTRASSE_ADDRESS;
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Musterstrasse", "45");

    adress = BLAUFELDWEG_ADDRESS;
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Blaufeldweg", "123B");
  }

  @Test
  public void shouldParseCorrectlyMoreComplicatedCases() {

    when(applicationContext.getBean(ParseGermanAddress.class)).thenReturn(new ParseGermanAddress());

    String adress = AM_BACHLE_ADDRESS;
    String[] adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Am Bächle", "23");

    adress = AUF_DER_VOGELWIESE_ADDRESS;
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Auf der Vogelwiese", "23 b");
  }

  @Test
  public void shouldParseCorrectlyOtherCountries() {

    when(applicationContext.getBean(ParseInternationalAddress.class))
        .thenReturn(new ParseInternationalAddress());

    String adress = RUE_DE_LA_REVOLUTION_ADDRESS;
    String[] adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("rue de la revolution", "4");

    adress = BROADWAY_AV_ADDRESS;
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Broadway Av", "200");

    when(applicationContext.getBean(ParseGermanAddress.class)).thenReturn(new ParseGermanAddress());

    adress = CALLE_ADUANNA;
    adressParsed = parseAddress.execute(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Calle Aduana", "29");

    when(applicationContext.getBean(ParseAddressWithTextNumber.class))
        .thenReturn(new ParseAddressWithTextNumber());

    adress = CALLE_39_ADDRESS;
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
