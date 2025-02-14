package com.camel.portal_vt.dtos;

import com.camel.portal_vt.dtos.google.TransitDetails;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record RouteDetailsDTO (
        String direction,
        int sequence,
        String arrivalStop,
        String departureStop,
        String name,
        String shortName,
        String type,
        BigDecimal value,
        int numStops
){
    public static List<RouteDetailsDTO> transformListTransitDatailToRouteDetails(List<TransitDetails> transitDetailsList, String direction){
        List<RouteDetailsDTO> routeDetailsDTOList = new ArrayList<>();
        int seq = 1;

        for (TransitDetails item : transitDetailsList) {
            RouteDetailsDTO routeDetailsDTO = new RouteDetailsDTO(
                    direction,
                    seq,
                    item.arrival_stop().name(),
                    item.departure_stop().name(),
                    item.headsign(),
                    item.line().short_name(),
                    item.line().vehicle().type(),
                    item.line().vehicle().value(),
                    item.num_stops()
            );

            routeDetailsDTOList.add(routeDetailsDTO);
            seq++;
        }
        return routeDetailsDTOList;
    };
}
