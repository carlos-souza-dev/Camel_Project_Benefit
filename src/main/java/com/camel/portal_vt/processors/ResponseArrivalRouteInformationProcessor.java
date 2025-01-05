package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.google.RouteInformation;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseArrivalRouteInformationProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        RouteInformation routeInformation = exchange.getIn().getBody(RouteInformation.class);

        if (routeInformation.status().equalsIgnoreCase("OK")){
            exchange.setProperty("arrivalRoute", true);
            exchange.getIn().setBody(routeInformation.routes());
        } else {
            exchange.setProperty("arrivalRoute", false);
        }
    }
}
