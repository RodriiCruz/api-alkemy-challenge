package com.challenge.alkemy.api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Rodrigo Cruz
 */
@Data
@AllArgsConstructor
public class ErrorDTO {
    
    private HttpStatus status;
    private String type;
    private List<String> errors;
    
}
