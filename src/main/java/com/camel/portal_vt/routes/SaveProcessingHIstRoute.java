package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.ProcessingHistDTO;
import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.RequestSaveProcessingHistProcessor;
import com.camel.portal_vt.processors.ResponseSaveProcessingHistProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class SaveProcessingHIstRoute extends RouteBuilder {

    public static final String SAVE_PROCESSING_HIST_ROUTE = "saveProcessingHistRoute";

    @Override
    public void configure() throws Exception {
        from("direct:"+SAVE_PROCESSING_HIST_ROUTE)
                .routeId("Route - Save Processing Hist")
                .process(new RequestSaveProcessingHistProcessor())
                .marshal().json()
                .log("Send to rest Api java-portal-vt/api")
                .process(new HeaderConfigJavaProcessor(HttpMethods.POST))
            .doTry()
                .to("http://localhost:5000/java-portal-vt/api/user/processing-history?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, ProcessingHistDTO.class)
                .process(new ResponseSaveProcessingHistProcessor())
            .doCatch(Exception.class)
                .log("Unhandled HTTP error occurred on route " + SAVE_PROCESSING_HIST_ROUTE)
                .setBody(simple("Error: ${exception}"))
                .process(new BackEndErrorProcessor())
                .end();
    }
}
