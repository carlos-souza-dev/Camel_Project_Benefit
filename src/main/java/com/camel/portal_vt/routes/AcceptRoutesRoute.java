package com.camel.portal_vt.routes;

import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.RequestSaveTransportsInfoProcessor;
import com.camel.portal_vt.processors.ResponseSaveTransportsInfoProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class AcceptRoutesRoute extends RouteBuilder {

    public static final String ACCEPT_ROUTES_ROUTE = "acceptRoutesRoute";

    @Override
    public void configure() throws Exception {
        from("direct:acceptRoutesRoute" )
                .routeId("Route - Routes accept")
                .process(new RequestSaveTransportsInfoProcessor())
                .marshal().json()
                .log("Send to rest Api java-portal-vt/api/user/routes")
                .process(new HeaderConfigJavaProcessor(HttpMethods.POST))
                .doTry()
                    .toD("http://localhost:5000/java-portal-vt/api/user/routes?bridgeEndpoint=true")
                    .process(new ResponseSaveTransportsInfoProcessor())
                .doCatch(Exception.class)
                    .log("Unhandled HTTP error occurred on route 'saveTransportsInfoRoute'")
                    .setBody(simple("Error: ${exception}"))
                    .process(new BackEndErrorProcessor());;
    }
}
