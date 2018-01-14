package com.nazareth.address.parser.usecases.factories;

import static com.nazareth.address.parser.utils.AddressRegexUtils.ADDRESS_WITH_TEXT_NUMBER;
import static com.nazareth.address.parser.utils.AddressRegexUtils.GERMAN_ADDRESS;
import static com.nazareth.address.parser.utils.AddressRegexUtils.INTERNATIONAL_ADDRESS;
import static com.nazareth.address.parser.utils.AddressRegexUtils.getMatcher;

import com.nazareth.address.parser.usecases.AddressParser;
import com.nazareth.address.parser.usecases.exceptions.AddressNullOrEmptyException;
import com.nazareth.address.parser.usecases.exceptions.NoParserFoundException;
import com.nazareth.address.parser.usecases.impl.ParseAddressWithTextNumber;
import com.nazareth.address.parser.usecases.impl.ParseGermanAddress;
import com.nazareth.address.parser.usecases.impl.ParseInternationalAddress;
import java.util.regex.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AddressParserFactory {

  private ApplicationContext applicationContext;

  @Autowired
  public AddressParserFactory(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  /**
   * @param address
   * @return Any implementation of AddressParser based on address parameter
   * @throws NoParserFoundException
   */
  public AddressParser getParser(String address) throws NoParserFoundException {

    if (address == null || address.isEmpty()) {
      throw new AddressNullOrEmptyException();
    }

    Matcher matcher = getMatcher(ADDRESS_WITH_TEXT_NUMBER, address);

    if (matcher.find()) {
      return applicationContext.getBean(ParseAddressWithTextNumber.class);
    }

    matcher = getMatcher(GERMAN_ADDRESS, address);

    if (matcher.find()) {
      return applicationContext.getBean(ParseGermanAddress.class);
    }

    matcher = getMatcher(INTERNATIONAL_ADDRESS, address);

    if (matcher.find()) {
      return applicationContext.getBean(ParseInternationalAddress.class);
    }

    throw new NoParserFoundException(address);
  }
}
