package com.nazareth.address.parser.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** */
public class AddressRegexUtils {
  public static final String GERMAN_ADDRESS = "^([^0-9]+) ([\\w]+\\s?[a-zA-Z]?)";
  public static final String INTERNATIONAL_ADDRESS = "^([\\w]+) ([^0-9]+)";
  public static final String INTERNATIONAL_ADDRESS_WITH_TEXT_NUMBER = "(?= (?i)(No|Number)\\s\\d+)";
  public static final String ADDRESS_WITH_TEXT_NUMBER = "(?= (?i)No|Number)";
  public static final String CLEAN_ADDRESS = ",";

  public static String removeSpecialCharacters(String address) {
    return address.replaceAll(CLEAN_ADDRESS, " ");
  }

  public static Matcher getMatcher(String regex, String address) {
    Pattern pattern = Pattern.compile(regex);
    return pattern.matcher(address);
  }
}
