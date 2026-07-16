package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import com.skillspherenexus.skillmanagementservice.dto.ContactRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.ContactResponseDTO;

public interface ContactService {

    ContactResponseDTO saveContact(ContactRequestDTO request);

    List<ContactResponseDTO> getAllContacts();

    ContactResponseDTO getContactById(Integer contactId);

    ContactResponseDTO updateContact(Integer contactId, ContactRequestDTO request);

    void deleteContact(Integer contactId);
}