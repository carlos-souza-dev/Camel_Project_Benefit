package com.camel.portal_vt.routes;

import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.ResponseUserExistsProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class UserAlreadyExistsRoute extends RouteBuilder {

    public static final String USER_ALREADY_EXISTS_ROUTE = "userAlreadyExistsRoute";

    @Override
    public void configure() throws Exception {
        from("direct:"+USER_ALREADY_EXISTS_ROUTE)
            .routeId("Route - Get User if exisis")
            .log("Header ${header.userName}")
            .setHeader("Content-Type", constant("application/json"))
            .log("Send to rest Api java-portal-vt/api")
            .process(new HeaderConfigJavaProcessor(HttpMethods.GET))
            .log("Updated headers configs")
            .doTry()
                .toD("http://localhost:5000/java-portal-vt/api/user/${header.userName}/exists?bridgeEndpoint=true")
                .process(new ResponseUserExistsProcessor())
            .doCatch(Exception.class)
                .log("Unhandled HTTP error occurred on route " + USER_ALREADY_EXISTS_ROUTE)
                .setBody(simple("Error: ${exception.message}"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .process(new BackEndErrorProcessor())
            .end();
    }
}
