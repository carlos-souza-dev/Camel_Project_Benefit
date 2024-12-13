package com.camel.portal_vt.routes;

import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.ResponseAuthenticationUserProcessor;
import com.camel.portal_vt.processors.ResponseErrorAuthenticationUserProcesso;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:authenticationRoute")
                .routeId("Route - Authentication User")
                .log("Send to rest Api java-portal-vt/api/user/login")
                .marshal().json()
                .process(new HeaderConfigJavaProcessor(HttpMethods.POST))
                .doTry()
                    .to("http://localhost:5000/java-portal-vt/api/user/login?bridgeEndpoint=true")
                    .process(new ResponseAuthenticationUserProcessor())
                .doCatch(HttpOperationFailedException.class)
//                    .throwException(new IllegalArgumentException("Forced by me"))
                    .process(new BackEndErrorProcessor())
                    .log("Authenticate error")
                .end();
    }
}
