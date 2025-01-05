package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.AddressDTO;
import com.camel.portal_vt.dtos.AddressesDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HeaderConfigAddressesProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        AddressesDTO addressesDTO = exchange.getIn().getBody(AddressesDTO.class);
        AddressDTO homeAddress = addressesDTO.homeAddress();
        AddressDTO workAddress = addressesDTO.workAddress();

        String originAddress = String.format("%, % - %, % - %, %, Brasil",
                homeAddress.street(),
                homeAddress.number(),
                homeAddress.district(),
                homeAddress.state(),
                homeAddress.uf(),
                homeAddress.cep()
        );

        String destinationAddress = String.format("%, % - %, % - %, %, Brasil",
                workAddress.street(),
                workAddress.number(),
                workAddress.district(),
                workAddress.state(),
                workAddress.uf(),
                workAddress.cep()
        );

        originAddress = URLEncoder.encode(originAddress, StandardCharsets.UTF_8);
        destinationAddress = URLEncoder.encode(destinationAddress,StandardCharsets.UTF_8);

        exchange.getIn().setHeader("homeAddress", originAddress);
        exchange.getIn().setHeader("workAddress", destinationAddress);
    }
}
