package com.example.app.models.services.utils;

import com.example.app.models.entities.User;

import java.nio.file.AccessDeniedException;

public interface FindAuthenticatedUser {
    User getAuthenticatedUser() throws AccessDeniedException;
}
