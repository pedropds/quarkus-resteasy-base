package exception;

import dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionMapping implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorDTO.builder()
                        .message(e.getMessage())
                        .type(e.getClass().getSimpleName())
                        .build())
                .build();
    }
}
