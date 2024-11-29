package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ErrorTemplateDTO;
import com.camel.portal_vt.dtos.ResponseBodyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class BackEndErrorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        HttpOperationFailedException exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBodyDTO responseBodyDTO = objectMapper.readValue(exception.getResponseBody(), ResponseBodyDTO.class);

        ErrorTemplateDTO errorTemplate = new ErrorTemplateDTO();
        errorTemplate.set_erroCode(responseBodyDTO.getStatus());
        errorTemplate.set_datails(responseBodyDTO.getMessage());
        errorTemplate.set_httpStatus(responseBodyDTO.getError());
        errorTemplate.set_message("error occurred request api rest '..." + responseBodyDTO.getPath() + "'");

        exchange.getMessage().setBody(errorTemplate);
        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, responseBodyDTO.getStatus());
    }
}
