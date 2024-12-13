package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;

public class ResponseAuthenticationUserProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String message = exchange.getIn().getBody(String.class);
        Integer responseCode = exchange.getIn().getHeader("CamelHttpResponseCode", Integer.class);

        ReturnStatusDTO returnStatus = new ReturnStatusDTO();

        returnStatus.setCode(responseCode);
        returnStatus.setDescription(message);
        returnStatus.setHttpStatus(HttpStatus.valueOf(responseCode));

        exchange.getIn().setBody(returnStatus);
    }
}
