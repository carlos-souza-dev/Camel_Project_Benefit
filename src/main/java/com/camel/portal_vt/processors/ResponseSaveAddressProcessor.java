package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.AddressDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseSaveAddressProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        AddressDTO addressFormated =  exchange.getIn().getBody(AddressDTO.class).formatAddress();

        exchange.getIn().setBody(addressFormated);
    }
}
