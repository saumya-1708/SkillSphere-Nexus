package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import com.skillspherenexus.skillmanagementservice.dto.UserRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO saveUser(UserRequestDTO request);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Integer userId);

    UserResponseDTO updateUser(Integer userId, UserRequestDTO request);

    void deleteUser(Integer userId);
}