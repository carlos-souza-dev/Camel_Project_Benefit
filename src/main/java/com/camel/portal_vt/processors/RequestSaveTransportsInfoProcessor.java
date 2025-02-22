package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.RoutesRequestDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RequestSaveTransportsInfoProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        RoutesRequestDTO routes = exchange.getIn().getBody(RoutesRequestDTO.class);

        exchange.getIn().setBody(routes);
    }
}
