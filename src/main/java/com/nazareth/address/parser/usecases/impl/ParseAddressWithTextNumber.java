package com.nazareth.address.parser.usecases.impl;

import static com.nazareth.address.parser.utils.AddressRegexUtils.INTERNATIONAL_ADDRESS_WITH_TEXT_NUMBER;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ParseAddressWithTextNumber extends AbstractAddressParser {

  public ParseAddressWithTextNumber() {
    super(INTERNATIONAL_ADDRESS_WITH_TEXT_NUMBER);
  }

  private static final Logger logger = LoggerFactory.getLogger(ParseAddressWithTextNumber.class);

  @Override
  protected String[] parseAddress() {
    final String[] addressParsed =
        Arrays.stream(address.split(INTERNATIONAL_ADDRESS_WITH_TEXT_NUMBER))
            .map(String::trim)
            .toArray(String[]::new);

    logger.debug("Parsed address {}", Arrays.toString(addressParsed));
    return addressParsed;
  }
}
