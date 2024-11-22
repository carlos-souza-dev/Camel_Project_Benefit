package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.google.RouteInformation;
import com.camel.portal_vt.processors.HeaderConfigGoogleApiProcessor;
import com.camel.portal_vt.processors.ResponseRouteInformationProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TransportsInfoRequestRoute extends RouteBuilder {

    private final Environment env;

    public TransportsInfoRequestRoute(Environment env) {
        this.env = env;
    }

    @Override
    public void configure() throws Exception {
        from("direct:transportsInfoRoute")
                .routeId("Route - Route information")
                .setHeader("Content-Type", constant("application/json"))
                .log("Request to rest maps.googleapis.com/maps/api/directions")
                .process(new HeaderConfigGoogleApiProcessor(HttpMethods.GET, this.env))
                .to("https://maps.googleapis.com/maps/api/directions/json")
                .unmarshal().json(JsonLibrary.Jackson, RouteInformation.class)
                .process(new ResponseRouteInformationProcessor());
    }
}
