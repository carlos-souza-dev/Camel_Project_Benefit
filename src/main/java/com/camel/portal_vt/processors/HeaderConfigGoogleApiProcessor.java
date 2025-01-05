package com.camel.portal_vt.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HeaderConfigGoogleApiProcessor implements Processor {

    private final HttpMethods httpMethod;
    private final String origin;
    private final String destination;
    @Autowired
    private Environment env;

    public HeaderConfigGoogleApiProcessor(HttpMethods httpMethod, String origin, String destination, Environment env){
        this.httpMethod = httpMethod;
        this.origin = origin;
        this.destination = destination;
        this.env = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
//        String originAddress = URLEncoder.encode(
//                "R. Brasílio Machado, 600 - Centro, São Bernardo do Campo - SP, 09715-140, Brasil",
//                StandardCharsets.UTF_8
//        );
//        String destinationAddress = URLEncoder.encode(
//                "R. Amador Bueno, 474 - Santo Amaro, São Paulo - SP, 04752-005, Brasil",
//                StandardCharsets.UTF_8
//        );

        String queryParams = String.format("region=%s&language=%s&units=%s&mode=%s&origin=%s&destination=%s&key=%s",
                this.env.getProperty("google-api.region"),
                this.env.getProperty("google-api.language"),
                this.env.getProperty("google-api.units"),
                this.env.getProperty("google-api.mode"),
                origin,
                destination,
                this.env.getProperty("google-api.key-api"));

        exchange.getIn().setHeader("Content-Type", "application/json");
        exchange.getIn().setHeader("Accept", "application/json");
        exchange.getIn().setHeader(Exchange.HTTP_METHOD, this.httpMethod);
        exchange.getIn().removeHeader(Exchange.HTTP_PATH);
        exchange.getIn().setHeader(Exchange.HTTP_QUERY, queryParams);
        exchange.getIn().setHeader(Exchange.HTTP_URI, this.env.getProperty("google-api-directions.url"));

        System.out.println("Headers google-api-directions configurados: " + Exchange.HTTP_URI);
    }
}
