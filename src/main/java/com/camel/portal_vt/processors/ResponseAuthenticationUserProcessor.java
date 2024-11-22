package com.camel.portal_vt.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseAuthenticationUserProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String text = exchange.getIn().getBody(String.class);

        exchange.getIn().setBody("message: " + text);

        String jsonResponse = String.format("{\"message\": \"%s\"}", text);

//        exchange.getIn().setHeader("Content-Type", "application/json");
        exchange.getIn().setBody(jsonResponse);
    }
}
