package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.AddressesDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseGetAddressesProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        AddressesDTO addressesDTO = exchange.getMessage().getBody(AddressesDTO.class);

        exchange.getMessage().setBody(addressesDTO);
    }
}
