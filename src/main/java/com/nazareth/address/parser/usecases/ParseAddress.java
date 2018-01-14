package com.nazareth.address.parser.usecases;

import static com.nazareth.address.parser.utils.AddressRegexUtils.removeSpecialCharacters;

import com.nazareth.address.parser.usecases.exceptions.AddressNullOrEmptyException;
import com.nazareth.address.parser.usecases.factories.AddressParserFactory;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Usecase to parse an Address with concatenated street names and numbers into a normalized Address
 * composed of String of street and String of street-number
 */
@Service
public class ParseAddress {

  private static final Logger logger = LoggerFactory.getLogger(ParseAddress.class);

  private AddressParserFactory addressParserFactory;

  private AddressParser addressParser;

  @Autowired
  public ParseAddress(AddressParserFactory addressParserFactory) {
    this.addressParserFactory = addressParserFactory;
  }

  /**
   * @param address - Address string concatenated street names and numbers.
   * @return String[] - the address parsed as Array String - Position 0 Street, Av, etc.. Position 1
   *     - Address Number
   * @throws AddressNullOrEmptyException
   */
  public String[] execute(String address) throws AddressNullOrEmptyException {

    if (address == null || address.isEmpty()) {
      throw new AddressNullOrEmptyException();
    }

    address = removeSpecialCharacters(address);

    logger.debug("Address received to parse {}", address);

    addressParser = addressParserFactory.getParser(address);

    final String[] addressParsed = addressParser.parse(address);

    logger.debug("Parsed address {}", Arrays.toString(addressParsed));

    return addressParsed;
  }
}
