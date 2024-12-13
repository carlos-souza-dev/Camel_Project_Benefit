package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpStatus;

public class BackEndErrorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        HttpOperationFailedException exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
        String message = exception.getResponseBody();
        Integer responseCode = exception.getStatusCode();

        ReturnStatusDTO returnStatus = new ReturnStatusDTO();

        returnStatus.setCode(responseCode);
        returnStatus.setDescription(message);
        returnStatus.setHttpStatus(HttpStatus.valueOf(responseCode));

        exchange.getMessage().setBody(returnStatus);
        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, responseCode);
    }
}
