package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.google.RouteInformation;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseRouteInformationProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        RouteInformation routeInformation = exchange.getIn().getBody(RouteInformation.class);
        System.out.println("Body - " + exchange.getIn().getBody());
        exchange.getIn().setBody(routeInformation);
    }
}
