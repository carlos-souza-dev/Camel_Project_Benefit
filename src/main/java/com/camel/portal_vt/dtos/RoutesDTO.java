package com.camel.portal_vt.dtos;

import com.camel.portal_vt.dtos.google.Route;
import com.camel.portal_vt.dtos.google.TransitDetails;

import java.util.List;

public record RoutesDTO(
        String residentialAddress,
        String businessAddress,
        SummaryRouteDTO departureRoute,
        SummaryRouteDTO arrivalRoute
) {
    public RoutesDTO(String residentialAddress, String businessAddress, SummaryRouteDTO departureRoute, SummaryRouteDTO arrivalRoute){
        this.residentialAddress = residentialAddress;
        this.businessAddress = businessAddress;
        this.departureRoute = departureRoute;
        this.arrivalRoute = arrivalRoute;
    };
}
