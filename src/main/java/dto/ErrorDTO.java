package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorDTO {
    String message;
    String type;
}
