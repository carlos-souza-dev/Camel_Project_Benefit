package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.UserRegisterResponseDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseRegisterUserProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        UserRegisterResponseDTO userRegister = exchange.getIn().getBody(UserRegisterResponseDTO.class);

        exchange.getIn().setBody(userRegister);
    }
}
