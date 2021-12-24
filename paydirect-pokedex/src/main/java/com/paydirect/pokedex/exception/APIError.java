package com.paydirect.pokedex.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}
