package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.UserDTO;
import com.camel.portal_vt.processors.HeaderConfigProcessor;
import com.camel.portal_vt.processors.ResponseSaveUserProcessor;
import com.camel.portal_vt.processors.SaveUserProcessor;
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
                .process(new SaveUserProcessor())
                .setHeader("Content-Type", constant("application/json"))
                .log("Send to rest Api java-portal-vt/api")
                .marshal().json()
                .process(new HeaderConfigProcessor(HttpMethods.POST))
                .to("http://localhost:5000/java-portal-vt/api/user")
                .unmarshal().json(JsonLibrary.Jackson, UserDTO.class)
                .process(new ResponseSaveUserProcessor());
    }
}
