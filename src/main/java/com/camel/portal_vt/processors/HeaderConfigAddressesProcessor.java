package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.AddressesDTO;
import com.camel.portal_vt.dtos.FullAddressDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HeaderConfigAddressesProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(HeaderConfigAddressesProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Start - Headers config addresses");

        FullAddressDTO fullAddressDTO = exchange.getIn().getBody(FullAddressDTO.class);

        String originAddress = URLEncoder.encode(fullAddressDTO.homeAddress(), StandardCharsets.UTF_8);
        String destinationAddress = URLEncoder.encode(fullAddressDTO.workAddress(),StandardCharsets.UTF_8);

        exchange.setProperty("homeAddress", originAddress);
        exchange.setProperty("workAddress", destinationAddress);

        logger.info("Finish - Headers config addresses");
    }
}
