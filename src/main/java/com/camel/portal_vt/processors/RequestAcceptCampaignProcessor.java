package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.UserDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RequestAcceptCampaignProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        UserDTO userDTO = exchange.getIn().getBody(UserDTO.class);
        String userName = exchange.getIn().getHeader("userName", String.class);

        exchange.setProperty("pathParam", userName);
        exchange.getIn().setBody(userDTO);
    }
}
