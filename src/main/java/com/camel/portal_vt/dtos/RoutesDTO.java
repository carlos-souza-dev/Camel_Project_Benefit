package com.camel.portal_vt.dtos;

import com.camel.portal_vt.dtos.google.Route;

public record RoutesDTO(
        String residentialAddress,
        String businessAddress,
        Route departureRoute,
        Route arrivalRoute
) {
    public RoutesDTO(String residentialAddress, String businessAddress, Route departureRoute, Route arrivalRoute){
        this.residentialAddress = residentialAddress;
        this.businessAddress = businessAddress;
        this.departureRoute = departureRoute;
        this.arrivalRoute = arrivalRoute;
    };
}
