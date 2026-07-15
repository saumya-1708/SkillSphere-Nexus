package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;
import com.skillspherenexus.skillmanagementservice.entity.User;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer userId);

    User updateUser(Integer userId, User user);

    void deleteUser(Integer userId);
}