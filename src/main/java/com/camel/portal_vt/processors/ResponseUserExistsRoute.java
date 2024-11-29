package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpStatus;


public class ResponseUserExistsRoute implements Processor {

    @Override
    public void process(Exchange exchange){
        String message = exchange.getIn().getBody(String.class);
        HttpOperationFailedException exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
        ReturnStatusDTO returnStatus = new ReturnStatusDTO();

        if(exception != null && exception.getStatusCode() == 404){
            returnStatus.setCode(HttpStatus.OK.value());
            returnStatus.setDescription(message);
            returnStatus.setHttpStatus("NOT_FOUND");
        } else {
            returnStatus.setCode(HttpStatus.OK.value());
            returnStatus.setDescription(message);
            returnStatus.setHttpStatus("SUCCESS");
        }

        exchange.getMessage().setBody(returnStatus);
    }
}
