package com.camel.portal_vt.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMethods;

import static org.apache.camel.language.constant.ConstantLanguage.constant;

public class HeaderConfigJavaProcessor implements Processor {

    private final HttpMethods httpMethod;

    public HeaderConfigJavaProcessor(HttpMethods httpMethod){
        this.httpMethod = httpMethod;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.getIn().setHeader("Content-Type", "application/json");
        exchange.getIn().setHeader("Accept", "application/json");
        exchange.getIn().setHeader(Exchange.HTTP_METHOD, this.httpMethod);
        exchange.getIn().removeHeader(Exchange.HTTP_PATH);
//        exchange.getIn().setHeader(Exchange.HTTP_QUERY, "bridgeEndpoint=true");
//        exchange.getIn().setHeader(Exchange.HTTP_URI, "http://localhost:5000/java-portal-vt/api/");

        System.out.println("Headers java-portal-vt configurados: " + Exchange.HTTP_URI);
    }
}
