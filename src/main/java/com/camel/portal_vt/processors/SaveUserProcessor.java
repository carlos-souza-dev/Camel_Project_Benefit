package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import static org.apache.camel.builder.Builder.simple;

//@Component
public class SaveUserProcessor implements Processor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        UserDTO userDTO = exchange.getIn().getBody(UserDTO.class);
//        exchange.getIn().setHeader(Exchange.HTTP_QUERY, "bridgeEndpoint=true");

        exchange.getIn().setBody(userDTO);
    }
}
