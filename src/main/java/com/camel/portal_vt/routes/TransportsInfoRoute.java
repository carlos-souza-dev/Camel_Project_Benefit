package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.google.RouteInformation;
import com.camel.portal_vt.processors.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.camel.portal_vt.routes.GetAddressesRoute.GET_ADDRESSES_ROUTE;

@Component
public class TransportsInfoRoute extends RouteBuilder {

    public static final String TRANSPORTS_INFO_ROUTE = "transportsInfoRoute";
    private final Environment env;

    public TransportsInfoRoute(Environment env) {
        this.env = env;
    }

    @Override
    public void configure() throws Exception {
        from("direct:transportsInfoRoute")
            .routeId("Route - Route information")
            .to("direct:"+GET_ADDRESSES_ROUTE)
            .process(new HeaderConfigAddressesProcessor())
            .process(new HeaderConfigGoogleApiProcessor(HttpMethods.GET, "${header.homeAddress}", "${header.workAddress}", this.env))
            .doTry()
                .log("Request to rest maps.googleapis.com/maps/api/directions")
                .to("https://maps.googleapis.com/maps/api/directions/json")
                .unmarshal().json(JsonLibrary.Jackson, RouteInformation.class)
                .process(new ResponseDepartureRouteInformationProcessor())
                .choice()
                    .when(header("departureRoute"))
                        .process(new HeaderConfigGoogleApiProcessor(HttpMethods.GET, "${header.workAddress}", "${header.homeAddress}", this.env))
                            .to("https://maps.googleapis.com/maps/api/directions/json")
                            .unmarshal().json(JsonLibrary.Jackson, RouteInformation.class)
                            .process(new ResponseArrivalRouteInformationProcessor())
                    .endChoice()
                .endDoTry()
            .doCatch(Exception.class)
                .process(new BackEndErrorProcessor())
            .end();
    }
}
