package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.dto.ContactRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.ContactResponseDTO;
import com.skillspherenexus.skillmanagementservice.entity.Contact;
import com.skillspherenexus.skillmanagementservice.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactResponseDTO saveContact(ContactRequestDTO request) {
        Contact contact = new Contact();
        contact.setUserId(request.getUserId());
        contact.setPhone(request.getPhone());
        contact.setAddress(request.getAddress());

        return convertToResponse(contactRepository.save(contact));
    }

    @Override
    public List<ContactResponseDTO> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ContactResponseDTO getContactById(Integer contactId) {
        return contactRepository.findById(contactId)
                .map(this::convertToResponse)
                .orElse(null);
    }

    @Override
    public ContactResponseDTO updateContact(Integer contactId, ContactRequestDTO request) {

        Contact existingContact = contactRepository.findById(contactId)
                .orElseThrow(() -> {
                    logger.warn("Contact not found: contactId={}", contactId);
                    return new RuntimeException("Contact not found");
                });

        existingContact.setUserId(request.getUserId());
        existingContact.setPhone(request.getPhone());
        existingContact.setAddress(request.getAddress());

        return convertToResponse(contactRepository.save(existingContact));
    }

    @Override
    public void deleteContact(Integer contactId) {
        contactRepository.deleteById(contactId);
    }

    private ContactResponseDTO convertToResponse(Contact contact) {
        ContactResponseDTO response = new ContactResponseDTO();
        response.setContactId(contact.getContactId());
        response.setUserId(contact.getUserId());
        response.setPhone(contact.getPhone());
        response.setAddress(contact.getAddress());
        return response;
    }
}