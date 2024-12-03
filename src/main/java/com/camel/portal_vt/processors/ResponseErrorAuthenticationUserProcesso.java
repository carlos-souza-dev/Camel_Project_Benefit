package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;

public class ResponseErrorAuthenticationUserProcesso implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String message = exchange.getIn().getBody(String.class);
        ReturnStatusDTO returnStatus = new ReturnStatusDTO();

        returnStatus.setCode(HttpStatus.UNAUTHORIZED.value());
        returnStatus.setDescription(message);
        returnStatus.setHttpStatus("UNAUTHORIZED");

        exchange.getIn().setBody(returnStatus);
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.UNAUTHORIZED.value());
    }
}
