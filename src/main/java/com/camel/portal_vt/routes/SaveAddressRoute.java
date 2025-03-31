package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.AddressDTO;
import com.camel.portal_vt.dtos.UserDTO;
import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.RequestSaveAddressProcessor;
import com.camel.portal_vt.processors.ResponseSaveAddressProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class SaveAddressRoute extends RouteBuilder {

    public static String SAVE_ADDRESS_ROUTE = "saveAddressRoute";

    @Override
    public void configure() throws Exception {
        from("direct:saveAddressRoute")
                .routeId("Route - Save Address")
                .process(new RequestSaveAddressProcessor())
                .marshal().json()
                .log("Send to rest Api java-portal-vt/api/user/register")
                .process(new HeaderConfigJavaProcessor(HttpMethods.POST))
                .doTry()
                .to("http://localhost:5000/java-portal-vt/api/user/address?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, AddressDTO.class)
                .process(new ResponseSaveAddressProcessor())
                .doCatch(Exception.class)
                .log("Unhandled HTTP error occurred on route 'registerRoute'")
                .setBody(simple("Error: ${exception}"))
                .process(new BackEndErrorProcessor());
                ;
    }
}
