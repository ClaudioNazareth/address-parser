package com.nazareth.address.parser.usecases.impl;

import static com.nazareth.address.parser.utils.AddressRegexUtils.GERMAN_ADDRESS;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ParseGermanAddress extends AbstractAddressParser {

  private static final Logger logger = LoggerFactory.getLogger(ParseGermanAddress.class);

  public ParseGermanAddress() {
    super(GERMAN_ADDRESS);
  }

  @Override
  protected String[] parseAddress() {

    String[] addressParsed = new String[2];
    for (int i = 1; i <= matcher.groupCount(); i++) {
      addressParsed[i - 1] = matcher.group(i).trim();
    }
    logger.debug("Parsed address {}", Arrays.toString(addressParsed));
    return addressParsed;
  }
}
