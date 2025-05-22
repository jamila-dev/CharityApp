package com.example.charityapp.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DonationDTO {
    @NotNull
    private Double amount;

    @NotNull
    private Long campaignId;
}
