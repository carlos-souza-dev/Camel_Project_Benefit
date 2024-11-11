package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.UserDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseSaveUserProcessor implements Processor  {

    @Override
    public void process(Exchange exchange){
        UserDTO userDTO = exchange.getIn().getBody(UserDTO.class);

        exchange.getIn().setBody(userDTO);
    }
}
