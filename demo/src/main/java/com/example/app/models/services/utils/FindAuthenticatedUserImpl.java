package com.example.app.models.services.utils;

import com.example.app.models.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.nio.file.AccessDeniedException;

@Service
public class FindAuthenticatedUserImpl implements FindAuthenticatedUser{

    @Override
    public User getAuthenticatedUser() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException("Autenticazione richiesta");
        }
        return (User) authentication.getPrincipal();
    }
}
