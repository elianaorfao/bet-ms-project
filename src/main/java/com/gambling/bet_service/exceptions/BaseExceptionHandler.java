package com.gambling.bet_service.exceptions;

import com.gambling.bet_service.model.BetStatus;
import com.gambling.bet_service.model.BetType;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class BaseExceptionHandler {

  @ExceptionHandler(BetNotFoundException.class)
  public ResponseEntity<String> handleBetNotFoundException(BetNotFoundException ex) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e){
    String errors = e.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.joining(", "));
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Validation errors: " + errors);
  }
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
    String error = String.format(
        "Invalid value for parameter '%s'. Expected type: %s",
        ex.getName(),
        ex.getRequiredType().getSimpleName()
    );
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(error);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleInvalidEnumValue(HttpMessageNotReadableException e) {
    if (e.getMessage() != null && e.getMessage().contains("BetStatus")) {
      String allowedValues = Arrays.stream(BetStatus.values())
          .map(Enum::name)
          .collect(Collectors.joining(", "));

      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Invalid value for the 'status' field. Allowed values are: " + allowedValues);
    }
    if (e.getMessage().contains("BetType")) {
      String allowedValues = Arrays.stream(BetType.values())
          .map(Enum::name)
          .collect(Collectors.joining(", "));
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Invalid value for the 'betType' field. Allowed values are: " + allowedValues);
    }
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Invalid request payload. Please verify your input.");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An unexpected error occurred: " + ex.getMessage());
  }

}
