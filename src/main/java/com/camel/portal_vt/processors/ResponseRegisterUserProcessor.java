package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseRegisterUserProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        ReturnStatusDTO returnStatus = exchange.getIn().getBody(ReturnStatusDTO.class);

        exchange.getIn().setBody(returnStatus);
    }
}
