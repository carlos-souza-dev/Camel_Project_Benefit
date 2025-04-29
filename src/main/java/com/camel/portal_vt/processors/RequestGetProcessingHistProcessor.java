package com.camel.portal_vt.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RequestGetProcessingHistProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String userName = exchange.getIn().getHeader("userName", String.class);
        Integer page = exchange.getIn().getHeader("page", Integer.class);
        Integer size = exchange.getIn().getHeader("number", Integer.class);
        String sortFirst = exchange.getIn().getHeader("sortFirst", String.class);
        String sortLast = exchange.getIn().getHeader("sortLast", String.class);
        boolean asc = exchange.getIn().getHeader("asc", Boolean.class);

        exchange.setProperty("userName", userName);
        exchange.setProperty("page", page);
        exchange.setProperty("size", size);
        exchange.setProperty("sortFirst", sortFirst);
        exchange.setProperty("sortLast", sortLast);
        exchange.setProperty("asc", asc);
    }
}
