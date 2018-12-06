package br.com.backend.Backend.Exception;

import br.com.backend.Backend.Model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EcommerceException.class})
    public ResponseEntity<ResponseError> handleProdutoNotFoundException(EcommerceException ex, WebRequest wr){
        ResponseError responseError = new ResponseError(ex.getMessage(),ex.getCode());

        if(ex.getCode() != 409){ //bem, não é tão bonito (ainda), mas funciona
            return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(responseError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ResponseError> handleNullPointerException(NullPointerException ex, WebRequest wr){
        ResponseError responseError = new ResponseError(ex.getMessage(), 400);

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
}