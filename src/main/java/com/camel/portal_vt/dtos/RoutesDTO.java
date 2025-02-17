package com.camel.portal_vt.dtos;

import com.camel.portal_vt.dtos.google.Route;
import com.camel.portal_vt.dtos.google.TransitDetails;

import java.util.List;

public record RoutesDTO(
        SummaryRouteDTO departureRoute,
        SummaryRouteDTO arrivalRoute
) {
    public RoutesDTO(SummaryRouteDTO departureRoute, SummaryRouteDTO arrivalRoute){
        this.departureRoute = departureRoute;
        this.arrivalRoute = arrivalRoute;
    };
}
