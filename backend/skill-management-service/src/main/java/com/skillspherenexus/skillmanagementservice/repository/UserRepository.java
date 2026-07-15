package com.skillspherenexus.skillmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillspherenexus.skillmanagementservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}