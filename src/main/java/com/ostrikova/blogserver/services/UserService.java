package com.ostrikova.blogserver.services;

import com.ostrikova.blogserver.model.User;
import com.ostrikova.blogserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUser(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsers() {
        Iterable<User> userIterable = userRepository.findAll();
        List<User> users = new ArrayList<>();

        for (User user : userIterable) {
            users.add(user);
        }

        return users;
    }
}
