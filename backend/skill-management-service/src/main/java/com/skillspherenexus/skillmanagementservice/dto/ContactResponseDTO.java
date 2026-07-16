package com.skillspherenexus.skillmanagementservice.dto;

import lombok.Data;

@Data
public class ContactResponseDTO {

    private Integer contactId;

    private Integer userId;

    private String phone;

    private String address;
}
