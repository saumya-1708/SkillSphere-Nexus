package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.entity.Contact;
import com.skillspherenexus.skillmanagementservice.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Integer contactId) {
        return contactRepository.findById(contactId).orElse(null);
    }

    @Override
    public Contact updateContact(Integer contactId, Contact contact) {

        Contact existingContact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        existingContact.setUserId(contact.getUserId());
        existingContact.setPhone(contact.getPhone());
        existingContact.setAddress(contact.getAddress());

        return contactRepository.save(existingContact);
    }

    @Override
    public void deleteContact(Integer contactId) {
        contactRepository.deleteById(contactId);
    }
}