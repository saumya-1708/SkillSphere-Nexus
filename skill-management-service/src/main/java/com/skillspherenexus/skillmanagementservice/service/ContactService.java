package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import com.skillspherenexus.skillmanagementservice.entity.Contact;

public interface ContactService {

    Contact saveContact(Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(Integer contactId);

    Contact updateContact(Integer contactId, Contact contact);

    void deleteContact(Integer contactId);
}