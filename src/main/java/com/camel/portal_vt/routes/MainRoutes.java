package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.UserDTO;
import com.camel.portal_vt.dtos.google.RouteInformation;
import com.camel.portal_vt.processors.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MainRoutes extends RouteBuilder {

    private final Environment env;

    public MainRoutes(Environment env) {
        this.env = env;
    }

    @Override
    public void configure() {
        // Configuração REST do Camel
        restConfiguration()
                .component("servlet")
//                .contextPath("calme-portal-vt/api")   // Define o caminho base para a API
                .contextPath(this.env.getProperty("camel.spring.application.name"))
                .component("netty-http")
                .host("localhost")
                .port(this.env.getProperty("camel.server.port"))
                .bindingMode(RestBindingMode.json);  // Ativa a resposta para JSON
//                .dataFormatProperty("prettyPrint", "true");

//      Salvar usuário - POST
        rest().path("/user")
                .post("/")
                .type(UserDTO.class)
                .produces("application/json") // Define o tipo de conteúdo de resposta como JSON
                .to("direct:saveUserRoute");

        rest().path("/route")
                .get("/")
//                .type(RouteInformation.class)
                .produces("application/json")
                .to("direct:routeInformation");

        from("direct:routeInformation")
                .routeId("Route - Route information")
                .setHeader("Content-Type", constant("application/json"))
                .log("Request to rest maps.googleapis.com/maps/api/directions")
                .log("Request body to Api ${body}")
                .process(new HeaderConfigGoogleApiProcessor(HttpMethods.GET, this.env))
                .to("https://maps.googleapis.com/maps/api/directions/json")
                .log("Response Api ${body}")
                .unmarshal().json(JsonLibrary.Jackson, RouteInformation.class)
                .process(new ResponseRouteInformationProcessor());
    }
}
