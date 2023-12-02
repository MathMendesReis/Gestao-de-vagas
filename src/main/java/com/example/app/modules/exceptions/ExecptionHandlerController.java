package com.example.app.modules.exceptions;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExecptionHandlerController {
  private MessageSource messageSource;

  public ExecptionHandlerController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErroMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    List<ErroMessageDTO> dto = new ArrayList<>();
    e.getBindingResult().getFieldErrors().forEach(err -> {
      String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
      ErroMessageDTO error = new ErroMessageDTO(message, err.getField());
      dto.add(error);
    });
    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);  }
}
