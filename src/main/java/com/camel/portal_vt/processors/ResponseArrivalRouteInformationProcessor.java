package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.AddressDTO;
import com.camel.portal_vt.dtos.RoutesDTO;
import com.camel.portal_vt.dtos.google.Route;
import com.camel.portal_vt.dtos.google.RouteInformation;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseArrivalRouteInformationProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(ResponseArrivalRouteInformationProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Start - Get response arrival route google api");

        RouteInformation routeInformation = exchange.getMessage().getBody(RouteInformation.class);

        if (routeInformation.status().equalsIgnoreCase("OK")){
            Route departureRoute = exchange.getProperty("departureRoute", Route.class);
            RoutesDTO routesDTO = new RoutesDTO(departureRoute, routeInformation.routes().get(0));

            exchange.getIn().setBody(routesDTO);
        } else {
            exchange.setProperty("arrivalRoute", false);
        }

        logger.info("Finish - Get response arrival route google api");
    }
}
