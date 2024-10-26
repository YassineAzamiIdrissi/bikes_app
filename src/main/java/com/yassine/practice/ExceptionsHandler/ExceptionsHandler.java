package com.yassine.practice.ExceptionsHandler;

import com.yassine.practice.CustomExceptions.*;
import com.yassine.practice.ExceptionResponse.ExceptionResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<ExceptionResponse> UsernameNotFoundException
            (UsernameNotFoundException exp) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(400).
                body(resp);
    }

    @ExceptionHandler(MessagingException.class)
    ResponseEntity<ExceptionResponse> messagingException(
            MessagingException exp
    ) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage("An inner email service encountered an error, " +
                "try again soon if the issue persists consider consulting " +
                "a member of the support team");
        return ResponseEntity.status(500).
                body(resp);
    }


    @ExceptionHandler(ActivationCodeNotFoundException.class)
    ResponseEntity<ExceptionResponse> activationCodeNotFoundException(
            ActivationCodeNotFoundException exp
    ) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(ActivationCodeExpiredException.class)
    ResponseEntity<ExceptionResponse> activationCodeExpiredException(
            ActivationCodeExpiredException exp
    ) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ExceptionResponse> methodArgumentNotValidException
            (MethodArgumentNotValidException exp) {
        ExceptionResponse resp = new ExceptionResponse();
        Set<String> errors = new HashSet<String>();
        exp.getBindingResult().getAllErrors().
                forEach((error) -> {
                    errors.add(error.getDefaultMessage());
                });
        resp.setErrors(errors);
        return ResponseEntity.status(400).
                body(resp);
    }


    @ExceptionHandler(AuthorityNotFoundException.class)
    ResponseEntity<ExceptionResponse> authorityNotFoundException(
            AuthorityNotFoundException exp
    ) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(DisabledException.class)
    ResponseEntity<ExceptionResponse> disabledException(DisabledException exp) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage("This account is currently disabled !");
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(LockedException.class)
    ResponseEntity<ExceptionResponse> lockedException(LockedException exp) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage("This account is currently locked !");
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    ResponseEntity<ExceptionResponse> sQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException exp
    ) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage("This email is already used by another user");
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ExceptionResponse> badCredentialsException(BadCredentialsException exp) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage("Mail or password is incorrect");
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(RecoveryCodeNotFoundException.class)
    ResponseEntity<ExceptionResponse> recoveryCodeNodeFoundException(
            RecoveryCodeNotFoundException exp
    ) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(RecoveryCodeExpiredException.class)
    ResponseEntity<ExceptionResponse> recoveryCodeExpiredException(
            RecoveryCodeExpiredException exp
    ) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).body(
                resp
        );
    }

    @ExceptionHandler(BikeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> bikeNotFoundException
            (BikeNotFoundException exp) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(400).
                body(resp);
    }

    @ExceptionHandler(BikeNotBorrowableException.class)
    public ResponseEntity<ExceptionResponse> bikeNotBorrowableException(
            BikeNotBorrowableException exp
    ) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(BikeNotReturnedYetException.class)
    public ResponseEntity<ExceptionResponse> bikeNotReturnedYetException(
            BikeNotReturnedYetException exp
    ) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(RecoveryErrorException.class)
    ResponseEntity<ExceptionResponse> recoveryErrorException(RecoveryErrorException exp) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    ResponseEntity<ExceptionResponse> operationNotPermittedException(OperationNotPermittedException
                                                                             exp) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }
}
