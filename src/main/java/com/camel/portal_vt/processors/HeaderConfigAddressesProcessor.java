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

        String originAddress = this.encodeAddress(fullAddressDTO.homeAddress());
        String destinationAddress = this.encodeAddress(fullAddressDTO.workAddress());

        exchange.setProperty("homeAddress", originAddress);
        exchange.setProperty("workAddress", destinationAddress);
        exchange.setProperty("residentialAddress", fullAddressDTO.homeAddress());
        exchange.setProperty("businessAddress", fullAddressDTO.workAddress());

        logger.info("Finish - Headers config addresses");
    }

    public String encodeAddress(String address){
        return URLEncoder.encode(address, StandardCharsets.UTF_8);
    }
}
