package com.camel.portal_vt.routes;

import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.ResponseAuthenticationUserProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationRoute extends RouteBuilder {

    public static final String AUTHENTICATION_ROUTE = "authenticationRoute";

    @Override
    public void configure() throws Exception {

        from("direct:"+AUTHENTICATION_ROUTE)
            .routeId("Route - Authentication User")
            .log("Send to rest Api java-portal-vt/api/user/login")
            .marshal().json()
            .process(new HeaderConfigJavaProcessor(HttpMethods.POST))
            .doTry()
                .to("http://localhost:5000/java-portal-vt/api/user/login?bridgeEndpoint=true")
                .process(new ResponseAuthenticationUserProcessor())
            .doCatch(HttpOperationFailedException.class)
                .log("Unhandled HTTP error occurred on route " + AUTHENTICATION_ROUTE)

//                    .throwException(new IllegalArgumentException("Forced by me"))
                .process(new BackEndErrorProcessor())
                .log("Authenticate error")
                .end();
    }
}
