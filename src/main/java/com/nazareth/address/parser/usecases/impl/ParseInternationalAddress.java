package com.nazareth.address.parser.usecases.impl;

import static com.nazareth.address.parser.utils.AddressRegexUtils.INTERNATIONAL_ADDRESS;

import java.util.Arrays;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ParseInternationalAddress extends AbstractAddressParser {

  private static final Logger logger = LoggerFactory.getLogger(ParseInternationalAddress.class);

  public ParseInternationalAddress() {
    super(INTERNATIONAL_ADDRESS);
  }

  @Override
  protected String[] parseAddress() {
    String[] addressParsed = new String[2];
    for (int i = 1; i <= matcher.groupCount(); i++) {
      final String data = matcher.group(i).trim();
      if (NumberUtils.isNumber(data)) {
        addressParsed[1] = data;
      }
      addressParsed[0] = data;
    }
    logger.debug("Parsed address {}", Arrays.toString(addressParsed));
    return addressParsed;
  }
}
