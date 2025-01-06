package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;

public class GoogleApiErrorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ReturnStatusDTO returnStatus = new ReturnStatusDTO();

        returnStatus.setCode(HttpStatus.NOT_FOUND.value());
        returnStatus.setDescription("Departure route not found");
        returnStatus.setHttpStatus(HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()));

        exchange.getMessage().setBody(returnStatus);
        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.NOT_FOUND.value());
    }
}
