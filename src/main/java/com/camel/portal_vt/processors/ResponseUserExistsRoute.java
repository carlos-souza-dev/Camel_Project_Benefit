package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;


public class ResponseUserExistsRoute implements Processor {

    @Override
    public void process(Exchange exchange){
        String message = exchange.getIn().getBody(String.class);

        ReturnStatusDTO returnStatus = new ReturnStatusDTO();
        returnStatus.setCode(HttpStatus.OK.value());
        returnStatus.setDescription(message);
        returnStatus.setHttpStatus("SUCCESS");

        exchange.getMessage().setBody(returnStatus);
    }
}
