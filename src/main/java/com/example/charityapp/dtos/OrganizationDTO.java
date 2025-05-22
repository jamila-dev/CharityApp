package com.example.charityapp.dtos;




import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrganizationDTO {
    @NotBlank
    private String name;

    private String description;
}

