package controller;

import dto.TokenDTO;
import dto.UserDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import service.UserService;


@Path("user")
@RequestScoped
@SuppressWarnings("unused")
public class UserController {

    @Inject
    UserService userService;

    @GET
    //@RolesAllowed({"ADMIN", "USER"})
    public Response findAll() {
        return Response.ok(userService.findAllDTO()).build();
    }

    @POST
    public Response create(final UserDTO user) {
        return Response.ok(userService.create(user)).build();
    }

    @PUT
    public Response update(final UserDTO user) {
        return Response.ok(userService.update(user)).build();
    }

    @GET
    @Path("email/{email}")
    public Response findByEmail(@PathParam("email") String email) {
        return Response.ok(userService.findByEmail(email)).build();
    }

    @POST
    @Path("login")
    public Response login(final UserDTO user) {
        var token = TokenDTO.builder()
                .token(userService.login(user))
                .build();

        return token != null ? Response.ok(token).build() : Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
