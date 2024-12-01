package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.UserDTO;
import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.ResponseSaveUserProcessor;
import com.camel.portal_vt.processors.RequestSaveUserProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class SaveUserRequestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:saveUserRoute")
                .routeId("Route - Save User")
                .process(new RequestSaveUserProcessor())
                .marshal().json()
                .log("Send to rest Api java-portal-vt/api")
                .process(new HeaderConfigJavaProcessor(HttpMethods.POST))
                .doTry()
                    .to("http://localhost:5000/java-portal-vt/api/user?bridgeEndpoint=true")
                    .unmarshal().json(JsonLibrary.Jackson, UserDTO.class)
                    .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                    .process(new ResponseSaveUserProcessor())
                .doCatch(Exception.class)
                    .log("Unhandled HTTP error occurred.")
                    .setBody(simple("Error: ${exception.message}"))
                    .process(new BackEndErrorProcessor());

    }
}
