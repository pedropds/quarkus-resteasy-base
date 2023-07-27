package exception;

import dto.ErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Arrays;

@Provider
public class ExceptionMapping implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return mapExceptionToResponse(e);
    }

    private Response mapExceptionToResponse(Exception e) {
        if (e instanceof BadRequestException)
            return getResponse(Response.Status.BAD_REQUEST, e);

        return getResponse(Response.Status.INTERNAL_SERVER_ERROR, e);
    }

    private Response getResponse(Response.Status status, Exception e) {
        return Response.status(status)
                .entity(ErrorDTO.builder()
                        .message(e.getMessage())
                        .type(e.getClass().getSimpleName())
                        .stackTrace(Arrays.toString(e.getStackTrace()))
                        .build())
                .build();
    }
}
