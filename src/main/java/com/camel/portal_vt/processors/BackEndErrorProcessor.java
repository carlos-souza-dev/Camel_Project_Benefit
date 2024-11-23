package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ErrorTemplate;
import com.camel.portal_vt.dtos.ReturnStatus;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;

public class BackEndErrorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        String mensage =  exchange.getIn().getBody(String.class);

        ErrorTemplate errorTemplate = new ErrorTemplate();
        errorTemplate.set_erroCode(HttpStatus.NOT_FOUND.value());
        errorTemplate.set_datails(mensage);
        errorTemplate.set_httpStatus("NOT_FOUND");
        errorTemplate.set_message("User not exists on database");

        exchange.getMessage().setBody(errorTemplate);
        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.NOT_FOUND.value());
    }
}
