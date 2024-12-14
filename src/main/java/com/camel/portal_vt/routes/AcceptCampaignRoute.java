package com.camel.portal_vt.routes;

import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.RequestAcceptCampaignProcessor;
import com.camel.portal_vt.processors.ResponseAcceptCampaignProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class AcceptCampaignRoute extends RouteBuilder {

    public static final String ACCEPT_CAMPAIGN_ROUTE = "acceptCampaignRoute";

    @Override
    public void configure() throws Exception {
        from("direct:acceptCampaignRoute")
                .routeId("Route - Accept Campaign")
                .process(new RequestAcceptCampaignProcessor())
                .marshal().json()
                .process(new HeaderConfigJavaProcessor(HttpMethods.PUT))
                .doTry()
                    .toD("http://localhost:5000/java-portal-vt/api/user/${exchangeProperty.pathParam}?bridgeEndpoint=true")
                    .process(new ResponseAcceptCampaignProcessor())
                .doCatch(Exception.class)
                    .log("Request HTTP error occurred.")
                    .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                    .process(new BackEndErrorProcessor())
                .end();

    }
}
