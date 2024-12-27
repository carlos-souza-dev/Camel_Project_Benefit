package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.AddressesDTO;
import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.RequestGetAddressesProcessor;
import com.camel.portal_vt.processors.ResponseGetAddressesProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class AddressesRoute extends RouteBuilder {

    public final static String GET_ADDRESSES_ROUTE = "getAddressesRoute";

    @Override
    public void configure() throws Exception {
        from("direct:getAddressesRoute")
                .routeId("Route - Get Addresses")
                .process(new RequestGetAddressesProcessor())
                .process(new HeaderConfigJavaProcessor(HttpMethods.GET))
                .log("Updated headers configs")
                .doTry()
                    .toD("http://localhost:5000/java-portal-vt/api/user/${exchangeProperty.pathParam}/addresses?bridgeEndpoint=true")
                    .unmarshal().json(JsonLibrary.Jackson, AddressesDTO.class)
                    .process(new ResponseGetAddressesProcessor())
                .doCatch(Exception.class)
                    .log("HTTP request error")
                .process(new BackEndErrorProcessor())
                .endDoTry()
                .end();;
    }
}
