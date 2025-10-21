package com.github.nmsilos.backendtcc.exception;

import com.github.nmsilos.backendtcc.exception.custom.*;
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

    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<MensagemErroPadrao> usuarioInvalidoException(UsuarioInvalidoException ex, HttpServletRequest request) {
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

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<MensagemErroPadrao> livroNaoEncontradoException(LivroNaoEncontradoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErroPadrao(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MensagemErroPadrao> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErroPadrao(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensagemErroPadrao> illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErroPadrao(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(NumeroPaginasInvalidoException.class)
    public ResponseEntity<MensagemErroPadrao> numeroPaginasInvalidoException(NumeroPaginasInvalidoException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErroPadrao(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

}
