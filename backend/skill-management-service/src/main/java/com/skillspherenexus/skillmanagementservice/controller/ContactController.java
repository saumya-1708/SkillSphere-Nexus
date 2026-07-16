package com.skillspherenexus.skillmanagementservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillspherenexus.skillmanagementservice.dto.ContactRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.ContactResponseDTO;
import com.skillspherenexus.skillmanagementservice.service.ContactService;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ContactResponseDTO saveContact(@RequestBody ContactRequestDTO request) {
        return contactService.saveContact(request);
    }

    @GetMapping
    public List<ContactResponseDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ContactResponseDTO getContactById(@PathVariable Integer id) {
        return contactService.getContactById(id);
    }

    @PutMapping("/{id}")
    public ContactResponseDTO updateContact(@PathVariable Integer id,
                                            @RequestBody ContactRequestDTO request) {
        return contactService.updateContact(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Integer id) {
        contactService.deleteContact(id);
        return "Contact deleted successfully";
    }
}