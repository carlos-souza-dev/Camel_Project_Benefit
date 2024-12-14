package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpStatus;


public class ResponseUserExistsProcessor implements Processor {

    @Override
    public void process(Exchange exchange){
        int responseCode = exchange.getIn().getHeader("CamelHttpResponseCode", Integer.class);
        String description = exchange.getIn().getBody(String.class);

        ReturnStatusDTO returnStatus = new ReturnStatusDTO();
        returnStatus.setCode(responseCode);
        returnStatus.setDescription(description);
        returnStatus.setHttpStatus(HttpStatus.valueOf(responseCode));

        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, returnStatus.getCode());
        exchange.getIn().setBody(returnStatus);
    }
}
