package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.AddressDTO;
import com.camel.portal_vt.dtos.AddressesDTO;
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

        AddressesDTO addressesDTO = exchange.getIn().getBody(AddressesDTO.class);
        AddressDTO homeAddress = addressesDTO.homeAddress();
        AddressDTO workAddress = addressesDTO.workAddress();

        String originAddress = String.format("%s, %d - %s, %s - %s, %s, Brasil",
                homeAddress.street(),
                homeAddress.number(),
                homeAddress.district(),
                homeAddress.state(),
                homeAddress.uf(),
                homeAddress.cep()
        );

        String destinationAddress = String.format("%s, %d - %s, %s - %s, %s, Brasil",
                workAddress.street(),
                workAddress.number(),
                workAddress.district(),
                workAddress.state(),
                workAddress.uf(),
                workAddress.cep()
        );

        originAddress = URLEncoder.encode(originAddress, StandardCharsets.UTF_8);
        destinationAddress = URLEncoder.encode(destinationAddress,StandardCharsets.UTF_8);

        exchange.setProperty("homeAddress", originAddress);
        exchange.setProperty("workAddress", destinationAddress);

        logger.info("Finish - Headers config addresses");
    }
}
