package com.distribuida;

import com.distribuida.Interface.ServicioAuthorProxy;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

public class AuthorServiceFactory {

    static final String URL="http://127.0.0.1:8081";
    public static ServicioAuthorProxy create(){
        ResteasyClientBuilder builder=(ResteasyClientBuilder) ResteasyClientBuilder.newBuilder();
        ResteasyClient client= builder.build();
        ServicioAuthorProxy servicio=client.target(URL).proxy(ServicioAuthorProxy.class);
        return servicio;
    }
}
