package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.RouteDetailsDTO;
import com.camel.portal_vt.dtos.SummaryRouteDTO;
import com.camel.portal_vt.dtos.google.RouteInformation;
import com.camel.portal_vt.enums.Destiny;
import com.camel.portal_vt.utils.NumberUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseDepartureRouteInformationProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(ResponseDepartureRouteInformationProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Start - Get response departure route google api");

        RouteInformation routeInformation = exchange.getMessage().getBody(RouteInformation.class);

        SummaryRouteDTO departureRoute = new SummaryRouteDTO(
                Destiny.GOING.getValue(),
                routeInformation.getLeg().distance().text,
                routeInformation.getLeg().duration().text,
                RouteDetailsDTO.transformListTransitDatailToRouteDetails(routeInformation.getLeg().transitDetails()),
                NumberUtils.sumTotalValueRoute(routeInformation.getLeg().transitDetails()));

        if (routeInformation.status().equalsIgnoreCase("OK")) {
            exchange.setProperty("departureRoute", true);
            exchange.setProperty("departureRoute", departureRoute);
        } else {
            exchange.setProperty("departureRoute", false);
        }

        logger.info("Finish - Get response departure route google api");
    }
}
