package ec.nttdatachallenge.challengean.infraestructure.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;
}
