package controller;

import dto.TokenDTO;
import dto.UserDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import service.UserService;


@Path("user")
@RequestScoped
public class UserController {

    @Inject
    UserService userService;

    @GET
    @RolesAllowed({"ADMIN", "USER"})
    public Response findAll() {
        return Response.ok(userService.findAllDTO()).build();
    }

    @GET
    @Path("email")
    public Response findByEmail(@QueryParam("email") String email) {
        return Response.ok(userService.findByEmail(email)).build();
    }

    @POST
    @Path("login")
    public Response login(final UserDTO user) {
        var token = TokenDTO.builder()
                .token(userService.authenticate(user))
                .build();

        return token != null ? Response.ok(token).build() : Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
