package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.exception.UserNotFoundException;

import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    // simple email‐regex – tweak as needed
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User getUserById(Long id) {
        Objects.requireNonNull(id, "ID must not be null");
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
    }

    public User createUser(User user) {
        Objects.requireNonNull(user, "User cannot be null");

        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // let repo propagate exceptions (e.g. duplicate‐email IllegalStateException)
        return repo.save(user);
    }
}