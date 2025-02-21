package com.camel.portal_vt.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RequestCancelSolicitationProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String pathParam = exchange.getIn().getHeader("userName", String.class);

        exchange.setProperty("pathParam", pathParam);
    }
}
