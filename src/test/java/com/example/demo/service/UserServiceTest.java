// src/test/java/com/example/demo/service/UserServiceTest.java
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.exception.UserNotFoundException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService    userService;

    @BeforeMethod
    public void setup() {
        // create a _fresh_ mock each time
        userRepository = mock(UserRepository.class);
        // inject it by hand
        userService    = new UserService(userRepository);
    }

    @Test
    public void testGetUserById_ReturnsUser_WhenExists() {
        User john = new User(1L, "John", "john@example.com");
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(john));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(result.getName(),  "John");
        assertEquals(result.getEmail(), "john@example.com");
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserById_Throws_WhenNotFound() {
        when(userRepository.findById(99L))
                .thenReturn(Optional.empty());

        userService.getUserById(99L);
    }

    @Test
    public void testCreateUser_Success() {
        User toSave = new User(null, "Jane", "jane@example.com");
        User saved  = new User(2L,   "Jane", "jane@example.com");
        when(userRepository.save(toSave))
                .thenReturn(saved);

        User result = userService.createUser(toSave);

        assertEquals(result.getId(), Long.valueOf(2));
        assertEquals(result.getName(), "Jane");
    }
}