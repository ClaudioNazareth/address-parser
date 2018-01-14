package com.nazareth.address.parser.usecases.impl;

import com.nazareth.address.parser.usecases.AddressParser;
import com.nazareth.address.parser.usecases.exceptions.MatcherNotFoundException;
import com.nazareth.address.parser.utils.AddressRegexUtils;
import java.util.regex.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractAddressParser implements AddressParser {

  private static final Logger logger = LoggerFactory.getLogger(AbstractAddressParser.class);

  private final String REGEX;

  protected String address;

  protected Matcher matcher;

  public AbstractAddressParser(String regex) {
    this.REGEX = regex;
  }

  /**
   * @param address - String address to to be parsed - Number and Street
   * @return String[] - the address parsed as Array String - Position 0 Street, Av, etc.. Position 1
   *     - Address Number
   * @throws MatcherNotFoundException
   */
  @Override
  public String[] parse(String address) {

    logger.debug("Address received to parse {}", address);

    validateAndClean(address);

    this.address = address;

    matcher = AddressRegexUtils.getMatcher(REGEX, address);

    if (matcher.find()) {
      logger.debug("Matcher found for address {}", address);
      return parseAddress();
    }

    logger.warn("No Matcher found for address {}", address);
    throw new MatcherNotFoundException(address);
  }

  protected abstract String[] parseAddress();
}
