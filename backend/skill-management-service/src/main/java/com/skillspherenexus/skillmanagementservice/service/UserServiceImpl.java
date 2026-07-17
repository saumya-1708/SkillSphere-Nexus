package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.dto.UserRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.UserResponseDTO;
import com.skillspherenexus.skillmanagementservice.entity.User;
import com.skillspherenexus.skillmanagementservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO saveUser(UserRequestDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return convertToResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(this::convertToResponse)
                .orElse(null);
    }

    @Override
    public UserResponseDTO updateUser(Integer userId, UserRequestDTO request) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.warn("User not found: userId={}", userId);
                    return new RuntimeException("User not found");
                });

        existingUser.setName(request.getName());
        existingUser.setEmail(request.getEmail());

        return convertToResponse(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    private UserResponseDTO convertToResponse(User user) {
        UserResponseDTO response = new UserResponseDTO();
        response.setUserId(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}