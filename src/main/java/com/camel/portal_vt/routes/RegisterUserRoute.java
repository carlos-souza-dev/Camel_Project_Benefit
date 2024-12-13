package com.camel.portal_vt.routes;

import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.ResponseRegisterUserProcessor;
import com.camel.portal_vt.processors.ResquestRegisterUserProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:registerRoute")
                .routeId("Route - Register User")
                .process(new ResquestRegisterUserProcessor())
                .marshal().json()
                .log("Send to rest Api java-portal-vt/api/user/register")
                .process(new HeaderConfigJavaProcessor(HttpMethods.POST))
                .doTry()
                    .to("http://localhost:5000/java-portal-vt/api/user/register?bridgeEndpoint=true")
//                    .unmarshal().json(JsonLibrary.Jackson, String.class)
                    .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                    .process(new ResponseRegisterUserProcessor())
                .doCatch(Exception.class)
                    .log("Unhandled HTTP error occurred on route 'registerRoute'")
                    .setBody(simple("Error: ${exception}"))
                    .process(new BackEndErrorProcessor());
    }
}
