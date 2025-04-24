package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.AddressRequestDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RequestSaveAddressProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        AddressRequestDTO alterAddress = exchange.getIn().getBody(AddressRequestDTO.class);

        exchange.getIn().setBody(alterAddress);
    }
}
