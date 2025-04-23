package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.exception.UserNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceEdgeCasesTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

// getUserById edge cases

    @Test(expectedExceptions = NullPointerException.class)
    public void testGetUserById_NullId() {
        userService.getUserById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetUserById_NegativeId() {
        userService.getUserById(-1L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetUserById_ZeroId() {
        userService.getUserById(0L);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserById_NonExistentId() {
        when(userRepository.findById(42L)).thenReturn(Optional.empty());
        userService.getUserById(42L);
    }

// createUser edge cases

    @Test(expectedExceptions = NullPointerException.class)
    public void testCreateUser_NullUser() {
        userService.createUser(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUser_MissingName() {
        User noName = new User(null, null, "foo@example.com");
        userService.createUser(noName);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUser_MissingEmail() {
        User noEmail = new User(null, "Alice", null);
        userService.createUser(noEmail);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUser_InvalidEmailFormat() {
        User badEmail = new User(null, "Bob", "not-an-email");
        userService.createUser(badEmail);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testCreateUser_DuplicateEmail() {
        User dup = new User(null, "Jane", "jane@example.com");
        when(userRepository.save(dup))
                .thenThrow(new IllegalStateException("email already in use"));
        userService.createUser(dup);
    }
}