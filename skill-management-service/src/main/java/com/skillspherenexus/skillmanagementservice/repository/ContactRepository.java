package com.skillspherenexus.skillmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillspherenexus.skillmanagementservice.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}