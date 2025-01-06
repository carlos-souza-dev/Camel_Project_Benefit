package com.camel.portal_vt.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderConfigGoogleApiProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(HeaderConfigGoogleApiProcessor.class);
    private final HttpMethods httpMethod;
    private String direction;
    private String origin;
    private String destiny;

    @Autowired
    private Environment env;

    public HeaderConfigGoogleApiProcessor(HttpMethods httpMethod, String direction, Environment env){
        this.httpMethod = httpMethod;
        this.env = env;
        this.direction = direction;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        if(direction.equalsIgnoreCase("work")) {
            origin =  exchange.getProperty("homeAddress", String.class);
            destiny =  exchange.getProperty("workAddress", String.class);
        }

        if(direction.equalsIgnoreCase("home")){
            origin =  exchange.getProperty("workAddress", String.class);
            destiny =  exchange.getProperty("homeAddress", String.class);
        }

        String queryParams = String.format("region=%s&language=%s&units=%s&mode=%s&origin=%s&destination=%s&key=%s",
                this.env.getProperty("google-api.region"),
                this.env.getProperty("google-api.language"),
                this.env.getProperty("google-api.units"),
                this.env.getProperty("google-api.mode"),
                origin,
                destiny,
                this.env.getProperty("google-api.key-api"));

        exchange.getMessage().setHeader("Content-Type", "application/json");
        exchange.getMessage().setHeader("Accept", "application/json");
        exchange.getMessage().setHeader(Exchange.HTTP_METHOD, this.httpMethod);
        exchange.getMessage().removeHeader(Exchange.HTTP_PATH);
        exchange.getMessage().setHeader(Exchange.HTTP_QUERY, queryParams);
        exchange.getMessage().setHeader(Exchange.HTTP_URI, this.env.getProperty("google-api-directions.url"));

        logger.info("Google directions api header configured");
    }
}
