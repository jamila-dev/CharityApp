package com.example.charityapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CampaignDTO {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Double goalAmount;

    @NotNull
    private Long organizationId;
}
