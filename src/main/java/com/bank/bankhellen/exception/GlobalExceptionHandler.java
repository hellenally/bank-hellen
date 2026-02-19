package com.bank.bankhellen.exception;

import com.bank.bankhellen.model.dto.WebResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<String, Integer> FIELD_CODES = Map.of(
            "namaLengkap", 9010,
            "alamat", 9011,
            "tanggalLahir", 9012,
            "nomorHandphone", 9013,
            "tempatLahir", 9014,
            "nomorKtp", 9015
    );

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String field = ex.getBindingResult().getFieldError().getField();
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        int internalCode = FIELD_CODES.getOrDefault(field, 400);

        return ResponseEntity.badRequest().body(WebResponse.builder()
                .responseCode(internalCode)
                .status("FAILED")
                .message(message)
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<Object>> handleGeneralException(Exception ex) {
        String originalMessage = ex.getMessage();
        int internalCode = 400;
        String cleanMessage = originalMessage;

        if (originalMessage != null && originalMessage.contains(":")) {
            String[] parts = originalMessage.split(":", 2);
            try {
                internalCode = Integer.parseInt(parts[0]);
                cleanMessage = parts[1];
            } catch (NumberFormatException e) {
            }
        }

        return ResponseEntity.badRequest().body(WebResponse.builder()
                .responseCode(internalCode)
                .status("FAILED")
                .message(cleanMessage)
                .build());
    }
}