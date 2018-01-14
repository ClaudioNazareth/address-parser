package com.nazareth.address.parser.controllers;

import static com.nazareth.address.parser.fixtures.AddressFixture.AUF_DER_VOGELWIESE_ADDRESS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nazareth.address.parser.controllers.jsons.AddressRequest;
import com.nazareth.address.parser.usecases.ParseAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParseAddressControllerTest {

  @InjectMocks private ParseAddressController parseAddressController;

  @Mock private ParseAddress parseAddress;

  @Test
  public void parse() {
    parseAddressController.parse(new AddressRequest(AUF_DER_VOGELWIESE_ADDRESS));

    verify(parseAddress, times(1)).execute(AUF_DER_VOGELWIESE_ADDRESS);
  }
}
