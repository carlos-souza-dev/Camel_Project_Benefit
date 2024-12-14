package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.UserDTO;
import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.RequestGetUserProcessor;
import com.camel.portal_vt.processors.ResponseGetUserProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class GetUserRoute extends RouteBuilder {

    public static final String GETUSERROUTE = "getUserRoute";

    @Override
    public void configure() throws Exception {
        from("getUserRoute")
            .routeId("Route - Get Data User")
            .process(new RequestGetUserProcessor())
            .marshal().json()
            .process(new HeaderConfigJavaProcessor(HttpMethods.GET))
            .log("Updated headers configs")
            .doTry()
            .toD("http://localhost:5000/java-portal-vt/api/user/${exchangeProperty.pathParam}?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, UserDTO.class)
                .process(new ResponseGetUserProcessor())
            .doCatch()
                .log("Request HTTP error occurred.")
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .process(new BackEndErrorProcessor())
            .endDoCatch()
        .end();
    }
}
