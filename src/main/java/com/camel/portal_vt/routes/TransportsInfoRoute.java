package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.AddressesDTO;
import com.camel.portal_vt.dtos.google.RouteInformation;
import com.camel.portal_vt.enums.Destiny;
import com.camel.portal_vt.processors.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TransportsInfoRoute extends RouteBuilder {

    public static final String INFO_ROUTES_ROUTE = "infoRoutesRoute";
    private final Environment env;

    public TransportsInfoRoute(Environment env) {
        this.env = env;
    }

    @Override
    public void configure() throws Exception {
        from("direct:"+INFO_ROUTES_ROUTE)
            .routeId("Route - Route information")
            .process(new HeaderConfigAddressesProcessor())
            .marshal().json(JsonLibrary.Jackson, AddressesDTO.class)
            .process(new HeaderConfigGoogleApiProcessor(HttpMethods.GET, Destiny.BACK, this.env))
            .doTry()
                .log("Request to rest maps.googleapis.com/maps/api/directions")
                .to("https://maps.googleapis.com/maps/api/directions/json")
                .unmarshal().json(JsonLibrary.Jackson, RouteInformation.class)
                .process(new ResponseDepartureRouteInformationProcessor())
                .choice()
                    .when(header("departureRoute"))
                        .process(new HeaderConfigGoogleApiProcessor(HttpMethods.GET, Destiny.GOING, this.env))
                        .to("https://maps.googleapis.com/maps/api/directions/json")
                        .unmarshal().json(JsonLibrary.Jackson, RouteInformation.class)
                        .process(new ResponseArrivalRouteInformationProcessor())
                    .otherwise()
                         .process(new GoogleApiErrorProcessor())
                    .endChoice()
                .endDoTry()
            .doCatch(Exception.class)
                .log("Unhandled HTTP error occurred on route " + INFO_ROUTES_ROUTE)
                .setBody(simple("Error: ${exception.message}"))
                .process(new BackEndErrorProcessor())
            .end();
    }
}
