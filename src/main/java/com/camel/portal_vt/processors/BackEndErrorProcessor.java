package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ReturnStatusDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpStatus;

public class BackEndErrorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        HttpOperationFailedException httpOperationFailedException = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        if(httpOperationFailedException != null){
            String message = httpOperationFailedException.getResponseHeaders().get("message") == null ? httpOperationFailedException.getResponseBody() : httpOperationFailedException.getResponseHeaders().get("message");
            Integer responseCode = httpOperationFailedException.getStatusCode();

            ReturnStatusDTO returnStatus = new ReturnStatusDTO();

            returnStatus.setCode(responseCode);
            returnStatus.setDescription(message);
            returnStatus.setHttpStatus(HttpStatus.valueOf(responseCode));

            exchange.getMessage().setBody(returnStatus);
            exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, responseCode);
        }

        if (exception != null) {
            String message = exception.getMessage() == null ? exception.getCause().getMessage() : exception.getMessage();
            Integer responseCode = HttpStatus.BAD_REQUEST.value();

            ReturnStatusDTO returnStatus = new ReturnStatusDTO();

            returnStatus.setCode(responseCode);
            returnStatus.setDescription(message);
            returnStatus.setHttpStatus(HttpStatus.valueOf(responseCode));

            exchange.getMessage().setBody(returnStatus);
            exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, responseCode);
        }

    }
}
