package com.github.nmsilos.backendtcc.exception;

import com.github.nmsilos.backendtcc.exception.custom.ErroServidorException;
import com.github.nmsilos.backendtcc.exception.custom.LivroJaCadastradoException;
import com.github.nmsilos.backendtcc.exception.custom.LoginInvalidoException;
import com.github.nmsilos.backendtcc.exception.custom.TokenInvalidoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ErroServidorException.class)
    public ResponseEntity<MensagemErroPadrao> erroServidorException(ErroServidorException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemErroPadrao(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<MensagemErroPadrao> loginInvalidoException(LoginInvalidoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MensagemErroPadrao(request, HttpStatus.FORBIDDEN, ex.getMessage()));
    }

    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<MensagemErroPadrao> tokenInvalidoException(TokenInvalidoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErroPadrao(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(LivroJaCadastradoException.class)
    public ResponseEntity<MensagemErroPadrao> livroJaCadastradoException(LivroJaCadastradoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MensagemErroPadrao(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MensagemErroPadrao> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErroPadrao(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensagemErroPadrao> illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErroPadrao(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

}
