package com.ainura.finance_tracker.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {
    private String message;
    private Map<String, String> errors;
}
