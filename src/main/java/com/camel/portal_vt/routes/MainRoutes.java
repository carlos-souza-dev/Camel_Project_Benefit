package com.camel.portal_vt.routes;

import com.camel.portal_vt.dtos.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.camel.portal_vt.routes.AcceptCampaignRoute.ACCEPT_CAMPAIGN_ROUTE;
import static com.camel.portal_vt.routes.GetAddressesRoute.GET_ADDRESSES_ROUTE;
import static com.camel.portal_vt.routes.AuthenticationRoute.AUTHENTICATION_ROUTE;
import static com.camel.portal_vt.routes.GetUserRoute.GET_USER_ROUTE;
import static com.camel.portal_vt.routes.RegisterUserRoute.REGISTER_ROUTE;
import static com.camel.portal_vt.routes.SaveUserRequestRoute.SAVE_USER_ROUTE;
import static com.camel.portal_vt.routes.TransportsInfoRoute.TRANSPORTS_INFO_ROUTE;
import static com.camel.portal_vt.routes.UserAlreadyExistsRoute.USER_ALREADY_EXISTS_ROUTE;

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
                .produces("application/json")
                .to(DIRECT+ GET_USER_ROUTE)

                .post("/")
                .type(UserDTO.class)
                .produces("application/json")
                .to(DIRECT+SAVE_USER_ROUTE)

                .get("/{userName}/exists")
                .produces("")
                .to(DIRECT+USER_ALREADY_EXISTS_ROUTE)

                .post("/login")
                .type(UserAuthDTO.class)
                .produces("application/json")
                .to(DIRECT+AUTHENTICATION_ROUTE)

                .post("/register")
                .type(UserRegisterDTO.class)
                .produces("application/json")
                .to(DIRECT+REGISTER_ROUTE)
                
                .put("/{userName}")
                .type(UserDTO.class)
                .produces("application/json")
                .to(DIRECT+ ACCEPT_CAMPAIGN_ROUTE)

                .get("/{userName}/addresses")
                .type(AddressesDTO.class)
                .produces("application/json")
                .to(DIRECT+GET_ADDRESSES_ROUTE)

                .post("/routes")
                .type(FullAddressDTO.class)
                .produces("application/json")
                .outType(RoutesDTO.class)
                .to(DIRECT+TRANSPORTS_INFO_ROUTE);

    }
}
