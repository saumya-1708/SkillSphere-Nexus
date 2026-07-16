package com.skillspherenexus.skillmanagementservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactRequestDTO {

    @NotNull
    private Integer userId;

    @NotBlank
    private String phone;

    private String address;
}
