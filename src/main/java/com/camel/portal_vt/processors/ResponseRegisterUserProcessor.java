package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import com.camel.portal_vt.dtos.UserRegisterResponseDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseRegisterUserProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String message = exchange.getIn().getBody(String.class);
        ReturnStatusDTO returnStatus = new ReturnStatusDTO();

        returnStatus.setCode(exchange.getProperty(Exchange.HTTP_RESPONSE_CODE, Integer.class));
        returnStatus.setDescription(message);
        returnStatus.setHttpStatus("CREATED");

        exchange.getIn().setBody(returnStatus);
    }
}
