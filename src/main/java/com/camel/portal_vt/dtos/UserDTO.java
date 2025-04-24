package com.camel.portal_vt.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    String name;

    String userName;

    Boolean acceptCampaign;

    String dateAcceptCampaign;

    AddressesDTO addresses;

    Boolean acceptRoute;

    String dateAcceptRoute;

    RoutesDTO routes;
}
