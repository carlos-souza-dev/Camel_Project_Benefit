package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.PageDTO;
import com.camel.portal_vt.dtos.ProcessingHistDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

public class ResponseGetProcessingHistProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        PageDTO<ProcessingHistDTO> pageDTO = exchange.getIn().getBody(PageDTO.class);

        exchange.getIn().setBody(pageDTO);
    }
}
