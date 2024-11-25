package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.UserRegisterDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResquestRegisterUserProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        UserRegisterDTO userRegister = exchange.getIn().getBody(UserRegisterDTO.class);

        exchange.getIn().setBody(userRegister);
    }
}
