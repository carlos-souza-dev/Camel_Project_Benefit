package com.camel.portal_vt.dtos;

import com.camel.portal_vt.dtos.google.Route;

public record RoutesDTO(
        Route departureRoute,
        Route arrivalRoute
) {
    public RoutesDTO(Route departureRoute, Route arrivalRoute){
        this.departureRoute = departureRoute;
        this.arrivalRoute = arrivalRoute;
    };
}
