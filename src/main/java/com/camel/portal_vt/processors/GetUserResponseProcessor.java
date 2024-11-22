package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.UserDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class GetUserResponseProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String statusUser = exchange.getIn().getBody(String.class);

        exchange.getIn().setBody("message: " + statusUser);

        String jsonResponse = String.format("{\"message\": \"%s\"}", statusUser);

        exchange.getIn().setBody(jsonResponse);
    }
}
