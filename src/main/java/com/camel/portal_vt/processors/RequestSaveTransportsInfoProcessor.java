package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.RoutesDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RequestSaveTransportsInfoProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        RoutesDTO routes = exchange.getIn().getBody(RoutesDTO.class);

        exchange.getIn().setBody(routes);
    }
}
