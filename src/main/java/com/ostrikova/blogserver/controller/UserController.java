package com.ostrikova.blogserver.controller;

import com.ostrikova.blogserver.model.User;
import com.ostrikova.blogserver.param.UserParam;
import com.ostrikova.blogserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody UserParam request) {
        HttpHeaders headers = new HttpHeaders();
        User user = new User
                .Builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        User createdUser = userService.createUser(user);

        return new ResponseEntity<>(createdUser, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{email}/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> checkUserEmail(@PathVariable String email) {
        HttpHeaders headers = new HttpHeaders();
        User user = userService.findUser(email);

        if (user == null) {
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{email}/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> loginUser(@PathVariable String email, @RequestBody String password) {
        HttpHeaders headers = new HttpHeaders();
        User user = userService.findUser(email);

        if (user != null && user.getPassword().equals(password)) {
            return new ResponseEntity<>(user, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        HttpHeaders headers = new HttpHeaders();
        List<User> users = userService.getUsers();

        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }
}
