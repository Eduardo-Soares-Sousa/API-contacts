package com.eduardo.apiContact.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDto {
    @NotBlank(message = "Name is required")
    private String name;
    private String email;
    @NotBlank(message = "Phone is required")
    @Pattern(
            regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}",
            message = "Phone must be in format (00) 00000-0000"
    )
    private String phone;
    private String description;
}
