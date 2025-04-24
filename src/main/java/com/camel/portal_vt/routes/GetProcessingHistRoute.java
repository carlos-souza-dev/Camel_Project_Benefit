package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.ProcessingHistDTO;
import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.RequestGetProcessingHistProcessor;
import com.camel.portal_vt.processors.ResponseGetProcessingHistProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProcessingHistRoute extends RouteBuilder {

    public static final String GET_PROCESSING_HIST_ROUTE = "getProcessingHistRoute";

    @Override
    public void configure() throws Exception {
        from("direct:"+GET_PROCESSING_HIST_ROUTE)
            .process(new RequestGetProcessingHistProcessor())
            .process(new HeaderConfigJavaProcessor(HttpMethods.GET))
            .doTry()
                .toD("http://localhost:5000/java-portal-vt/api/user/processing-history/${exchangeProperty.pathParam}?bridgeEndpoint=true")
                .unmarshal(new ListJacksonDataFormat(ProcessingHistDTO.class))
                .process(new ResponseGetProcessingHistProcessor())
            .doCatch(Exception.class)
                .log("Unhandled HTTP error occurred on route " + GET_PROCESSING_HIST_ROUTE)
                .setBody(simple("Error: ${exception}"))
                .process(new BackEndErrorProcessor());

    }
}
