package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.ProcessingHistDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RequestSaveProcessingHistProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        ProcessingHistDTO processingHistDTO = exchange.getIn().getBody(ProcessingHistDTO.class);

        exchange.getIn().setBody(processingHistDTO);
    }
}
