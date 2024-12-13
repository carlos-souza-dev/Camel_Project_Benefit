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
        Integer responseCode = exchange.getIn().getHeader("CamelHttpResponseCode", Integer.class);
        ReturnStatusDTO returnStatus = new ReturnStatusDTO();

        if(exception != null && exception.getStatusCode() == 404){
            returnStatus.setCode(HttpStatus.OK.value());
            returnStatus.setDescription(message);
            returnStatus.setHttpStatus(HttpStatus.valueOf(responseCode));
        } else {
            returnStatus.setCode(HttpStatus.OK.value());
            returnStatus.setDescription(message);
            returnStatus.setHttpStatus(HttpStatus.valueOf(responseCode));
        }

        exchange.getMessage().setBody(returnStatus);
    }
}
