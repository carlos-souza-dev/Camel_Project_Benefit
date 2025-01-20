package com.camel.portal_vt.processors;

import com.camel.portal_vt.dtos.AddressDTO;
import com.camel.portal_vt.dtos.AddressesDTO;
import com.camel.portal_vt.dtos.UserDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseGetUserProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        UserDTO userDTO = exchange.getIn().getBody(UserDTO.class);
        AddressDTO homeAddress = userDTO.getAddresses().homeAddress().formatAddress();
        AddressDTO workAddress = userDTO.getAddresses().workAddress().formatAddress();
        AddressesDTO addressesDTO = new AddressesDTO(homeAddress,workAddress);

        userDTO.setAddresses(addressesDTO);

        exchange.getIn().setBody(userDTO);
    }
}
