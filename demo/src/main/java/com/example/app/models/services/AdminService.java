package com.example.app.models.services;


import com.example.app.models.dtos.response.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    UserResponse promoteToAdmin(int userId);
    void deleteNonAdminUser(int userId);
}
