package com.example.receipt_api.json;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReceiptRequestJson {
    @NotBlank(message = "Retailer is required")
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Retailer contains invalid characters")
    private String retailer;

    @NotBlank(message = "Purchase date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Purchase date must be in format YYYY-MM-DD")
    private String purchaseDate;

    @NotBlank(message = "Purchase time is required")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Purchase time must be in 24-hour format HH:MM")
    private String purchaseTime;

    @NotEmpty(message = "Items cannot be empty")
    @Valid
    private List<ItemRequestJson> items;

    @NotBlank(message = "Total is required")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Total must be in format xx.xx")
    private String total;
}
