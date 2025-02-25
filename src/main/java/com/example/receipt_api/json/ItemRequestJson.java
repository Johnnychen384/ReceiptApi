package com.example.receipt_api.json;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemRequestJson {
    @NotBlank(message = "Short description is required")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Short description contains invalid characters")
    private String shortDescription;

    @NotBlank(message = "Price is required")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must be in format X.XX")
    private String price;
}
