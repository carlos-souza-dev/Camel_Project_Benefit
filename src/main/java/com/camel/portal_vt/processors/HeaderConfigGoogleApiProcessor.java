package com.camel.portal_vt.processors;

import com.camel.portal_vt.enums.Destiny;
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
    private Destiny destiny;
    private String origin;
    private String destination;

    @Autowired
    private Environment env;

    public HeaderConfigGoogleApiProcessor(HttpMethods httpMethod, Destiny destiny, Environment env){
        this.httpMethod = httpMethod;
        this.origin = origin;
        this.destination = destination;
        this.env = env;
        this.destiny = destiny;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        if(destiny.getValue().equalsIgnoreCase("GO-HOME")) {
            origin =  exchange.getProperty("homeAddress", String.class);
            destination =  exchange.getProperty("workAddress", String.class);
        }

        if(destiny.getValue().equalsIgnoreCase("GO-WORK")){
            origin =  exchange.getProperty("workAddress", String.class);
            destination =  exchange.getProperty("homeAddress", String.class);
        }

        String queryParams = String.format("region=%s&language=%s&units=%s&mode=%s&origin=%s&destination=%s&key=%s",
                this.env.getProperty("google-api.region"),
                this.env.getProperty("google-api.language"),
                this.env.getProperty("google-api.units"),
                this.env.getProperty("google-api.mode"),
                origin,
                destination,
                this.env.getProperty("google-api.key-api"));

        exchange.getMessage().removeHeader(Exchange.HTTP_PATH);
        exchange.getMessage().setHeader("Content-Type", "application/json");
        exchange.getMessage().setHeader("Accept", "application/json");
        exchange.getMessage().setHeader(Exchange.HTTP_METHOD, this.httpMethod);
        exchange.getMessage().setHeader(Exchange.HTTP_QUERY, queryParams);
        exchange.getMessage().setHeader(Exchange.HTTP_URI, this.env.getProperty("google-api-directions.url"));

        logger.info("Google directions api header configured");
    }
}
