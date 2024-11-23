package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.UserAuthDTO;
import com.camel.portal_vt.dtos.UserDTO;
import com.camel.portal_vt.dtos.google.RouteInformation;
import org.apache.camel.builder.RouteBuilder;
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
        rest("/user")
                .post("/")
                .type(UserDTO.class)
                .produces("application/json") // Define o tipo de conteúdo de resposta como JSON
                .to("direct:saveUserRoute")

                .get("/{userName}/exists")
                .produces("")
                .to("direct:getUserRoute")

                .post("/login")
                .type(UserAuthDTO.class)
                .produces("application/json") // Define o tipo de conteúdo de resposta como JSON
                .to("direct:authenticationRoute");

        rest().path("/route")
                .get("/")
                .produces("application/json")
                .to("direct:transportsInfoRoute");

    }
}
