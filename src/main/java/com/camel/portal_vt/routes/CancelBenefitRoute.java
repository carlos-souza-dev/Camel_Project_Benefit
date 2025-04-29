package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.UserDTO;
import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.ResponseCancelBenefitProcessor;
import com.camel.portal_vt.processors.ResponseUserExistsProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class CancelBenefitRoute extends RouteBuilder {

    public static final String CANCEL_BENEFIT_ROUTE = "cancelBenefitRoute";
    @Override
    public void configure() throws Exception {
        from("direct:"+CANCEL_BENEFIT_ROUTE)
                .routeId("Route - Cancel Benefit")
                .log("Send to rest Api java-portal-vt/api")
                .process(new HeaderConfigJavaProcessor(HttpMethods.GET))
                .log("Updated headers configs")
                .doTry()
                    .toD("http://localhost:5000/java-portal-vt/api/user/cancel-benefit/${header.userName}?bridgeEndpoint=true")
                    .unmarshal().json(JsonLibrary.Jackson, UserDTO.class)
                    .process(new ResponseCancelBenefitProcessor())
                .doCatch(Exception.class)
                    .log("Unhandled HTTP error occurred on route " + CANCEL_BENEFIT_ROUTE)
                    .setBody(simple("Error: ${exception.message}"))
                    .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                    .process(new BackEndErrorProcessor())
                .end();
    }
}
