package com.camel.portal_vt.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.core.env.Environment;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HeaderConfigGoogleApiProcessor implements Processor {

    private final HttpMethods httpMethod;
    private final Environment env;

    public HeaderConfigGoogleApiProcessor(HttpMethods httpMethod, Environment env){
        this.httpMethod = httpMethod;
        this.env = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String originAddress = URLEncoder.encode(
                "R. Brasílio Machado, 600 - Centro, São Bernardo do Campo - SP, 09715-140, Brasil",
                StandardCharsets.UTF_8
        );
        String destinationAddress = URLEncoder.encode(
                "R. Amador Bueno, 474 - Santo Amaro, São Paulo - SP, 04752-005, Brasil",
                StandardCharsets.UTF_8
        );

        String queryParams = String.format("region=%s&language=%s&units=%s&mode=%s&origin=%s&destination=%s&key=%s",
                this.env.getProperty("google-api.region"),
                this.env.getProperty("google-api.language"),
                this.env.getProperty("google-api.units"),
                this.env.getProperty("google-api.mode"),
                originAddress,
                destinationAddress,
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
