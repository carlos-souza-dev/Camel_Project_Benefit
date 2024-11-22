package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.UserDTO;
import com.camel.portal_vt.processors.GetUserResponseProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class GetUserRequestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:getUserRoute")
                .routeId("Route - Get User")
                .log("Header ${header.userName}")
                .setHeader("Content-Type", constant("application/json"))
                .log("Send to rest Api java-portal-vt/api")
                .process(new HeaderConfigJavaProcessor(HttpMethods.GET))
                .toD("http://localhost:5000/java-portal-vt/api/user/${header.userName}/exists?bridgeEndpoint=true")
                .choice()
                    .when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(404))
                        .log("User not found, handling 404 error.")
                        .setBody(constant("{\"error\": \"User not found\"}")) // Resposta personalizada para 404
                        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404)) // Configura o código de status como 404
                    .otherwise()
                        .process(new GetUserResponseProcessor())
                .end();
    }
}
