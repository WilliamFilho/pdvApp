package com.wnet.pdvapp.controller;

import com.wnet.pdvapp.UserService;
import com.wnet.pdvapp.entity.User;
import com.wnet.pdvapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repository;
    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

    public ResponseEntity create(@RequestBody User user){
        try {
            return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}