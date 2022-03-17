package org.acme.user.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import org.acme.user.repository.entity.Authenticable;
import org.acme.user.repository.service.AuthenticableService;
import org.acme.util.TokenHandler;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/users")
@RegisterRestClient
public class AuthenticableController {

    @Inject
    EntityManager em;

    @Inject
    AuthenticableService service;

    @GET
    @Path("{id}")
    public Authenticable findUser(@PathParam int id) {
        return service.findById(id);
    }

    @GET
    @Path("/token")
    public String getToken() {
        return TokenHandler.generate();
    }

    @GET
    @Path("/message")
    @RolesAllowed("vip")
    public String vipMessage() {
        return "opa vip";
    }

    @POST
    @Path("create")
    public Authenticable createUser(Authenticable user) {
        return service.createUser(user);
    }

    @POST
    @Path("validate")
    public Jws<Claims> validate(String jwt) {
        return TokenHandler.validate(jwt);
    }
}
