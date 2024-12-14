package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.UserAuthDTO;
import com.camel.portal_vt.dtos.UserDTO;
import com.camel.portal_vt.dtos.UserRegisterDTO;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.camel.portal_vt.routes.AcceptCampaignRoute.ACCEPT_CAMPAIGN_ROUTE;
import static com.camel.portal_vt.routes.GetUserRoute.GET_USER_ROUTE;

@Component
public class MainRoutes extends RouteBuilder {

    private final Environment env;
    private static final String DIRECT = "direct:";
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

        String GetUserRoute;
        rest("/user")
                .get("/{userName}")
                .produces("")
                .to(DIRECT+ GET_USER_ROUTE)

                .post("/")
                .type(UserDTO.class)
                .produces("application/json")
                .to(DIRECT+"saveUserRoute")

                .get("/{userName}/exists")
                .produces("")
                .to(DIRECT+"userAlreadyExistsRoute")

                .post("/login")
                .type(UserAuthDTO.class)
                .produces("application/json")
                .to(DIRECT+"authenticationRoute")

                .post("/register")
                .type(UserRegisterDTO.class)
                .produces("application/json")
                .to(DIRECT+"registerRoute")
                
                .put("/{userName}")
                .type(UserDTO.class)
                .produces("application/json")
                        .to(DIRECT+ ACCEPT_CAMPAIGN_ROUTE);
                

        rest().path("/route")
                .get("/")
                .produces("application/json")
                .to(DIRECT+"transportsInfoRoute");

    }
}
