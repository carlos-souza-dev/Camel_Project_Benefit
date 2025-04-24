package com.camel.portal_vt.dtos;

public record RoutesRequestDTO(
        String userName,
        SummaryRouteDTO departureRoute,
        SummaryRouteDTO arrivalRoute
) {}
