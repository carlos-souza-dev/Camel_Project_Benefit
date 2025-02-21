package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.AddressesDTO;
import com.camel.portal_vt.processors.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class CancelSolicitationRoute extends RouteBuilder {

    public static final String CANCEL_SOLICITATION_ROUTE = "cancelSolicitationRoute";

    @Override
    public void configure() throws Exception {
        from("direct:cancelSolicitationRoute")
                .routeId("Route - Cancel Solicitation")
                .process(new RequestCancelSolicitationProcessor())
                .process(new HeaderConfigJavaProcessor(HttpMethods.GET))
                .log("Updated headers configs")
                .doTry()
                    .toD("http://localhost:5000/java-portal-vt/api/user/cancel-solicitation/${exchangeProperty.pathParam}?bridgeEndpoint=true")
                    .process(new ResponseCancelSolicitationProcessor())
                .doCatch(Exception.class)
                    .log("HTTP request error")
                    .process(new BackEndErrorProcessor())
                .endDoTry()
                .end();;
    }
}
