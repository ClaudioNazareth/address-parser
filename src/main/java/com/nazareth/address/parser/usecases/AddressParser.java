package com.nazareth.address.parser.usecases;

import com.nazareth.address.parser.usecases.exceptions.AddressNullOrEmptyException;
import com.nazareth.address.parser.utils.AddressRegexUtils;

public interface AddressParser {

  /**
   * @param address to be parsed
   * @return String [] composed of String of street and string of street-number
   */
  String[] parse(String address);

  /**
   * Validate if the address is not null or empty and remove Special Characters like (",")
   *
   * @param address - String address
   * @return Address not null or empty and without Special characters like (",")
   * @throws AddressNullOrEmptyException - if the address is null or empty
   */
  default String validateAndClean(String address) throws AddressNullOrEmptyException {
    if (address == null || address.isEmpty()) {
      throw new AddressNullOrEmptyException();
    }
    return AddressRegexUtils.removeSpecialCharacters(address);
  }
}
