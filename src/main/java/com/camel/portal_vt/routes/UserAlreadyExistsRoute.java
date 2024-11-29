package com.camel.portal_vt.routes;

//import com.camel.portal_vt.processors.GetUserResponseProcessor;
import com.camel.portal_vt.processors.BackEndErrorProcessor;
import com.camel.portal_vt.processors.HeaderConfigJavaProcessor;
import com.camel.portal_vt.processors.ResponseUserExistsRoute;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserAlreadyExistsRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:getUserRoute")
                .routeId("Route - Get User")
                .log("Header ${header.userName}")
                .setHeader("Content-Type", constant("application/json"))
                .log("Send to rest Api java-portal-vt/api")
                .process(new HeaderConfigJavaProcessor(HttpMethods.GET))
                .log("Updated headers configs")
                .doTry()
                    .toD("http://localhost:5000/java-portal-vt/api/user/${header.userName}/exists?bridgeEndpoint=true")
                    .process(new ResponseUserExistsRoute())
                .doCatch(Exception.class)
                    .log("Unhandled HTTP error occurred.")
                    .setBody(simple("Error: ${exception.message}"))
                    .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                    .process(new ResponseUserExistsRoute())
                .end();
    }
}
