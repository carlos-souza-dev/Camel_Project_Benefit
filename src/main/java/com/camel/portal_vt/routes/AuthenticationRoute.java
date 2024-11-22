package com.camel.portal_vt.routes;

import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.ResponseAuthenticationUserProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:authenticationRoute")
                .routeId("Route - Authentication User")
                .log("Body -- ${body}")
                .log("Send to rest Api java-portal-vt/api/user/login")
                                .marshal().json()
                .process(new HeaderConfigJavaProcessor(HttpMethods.POST))
                .to("http://localhost:5000/java-portal-vt/api/user/login?bridgeEndpoint=true")
                .process(new ResponseAuthenticationUserProcessor())
                .log("Autenticando usuário");

    }
}
