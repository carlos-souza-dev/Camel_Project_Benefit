package com.camel.portal_vt.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMethods;

public class HeaderConfigProcessor implements Processor {

    private final HttpMethods httpMethod;

    public HeaderConfigProcessor(HttpMethods httpMethod){
        this.httpMethod = httpMethod;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.getIn().setHeader("Content-Type", "application/json");
        exchange.getIn().setHeader("Accept", "application/json");
        exchange.getIn().setHeader(Exchange.HTTP_METHOD, this.httpMethod);
        exchange.getIn().removeHeader(Exchange.HTTP_PATH);
        exchange.getIn().setHeader(Exchange.HTTP_QUERY, "bridgeEndpoint=true");
        exchange.getIn().setHeader(Exchange.HTTP_URI, "http://localhost:5000/java-portal-vt/api/user");

        System.out.println("Headers java-portal-vt configurados: " + Exchange.HTTP_URI);
    }
}
