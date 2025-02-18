package com.test.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.criteria.UserCriteria;
import com.test.demo.dto.UserDto;
import com.test.demo.form.user.CreateUserForm;
import com.test.demo.form.user.UpdateUserForm;
import com.test.demo.mapper.UserMapper;
import com.test.demo.model.User;
import com.test.demo.repository.UserRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String putMethodName(@PathVariable Long id, @RequestBody UpdateUserForm updateUserForm) {
        // TODO: process PUT request
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userMapper.fromUpdateFormToEntity(updateUserForm, user);
            userRepository.save(user);
            return "Update success!";
        }
        return "User not found!";
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody CreateUserForm createUserForm) {
        // TODO: process POST request
        User user = userMapper.fromCreateFormToUser(createUserForm);
        user.setCreatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        userRepository.save(user);
        UserDto dto = userMapper.fromUserToDto(user);
        return dto;
    }

    @GetMapping("/list")
    public List<UserDto> listUser(UserCriteria userCriteria, Pageable pageable) {
        Page<User> userPage = userRepository.findAll(userCriteria.getSpecification(), pageable);
        return userMapper.fromEntityListToDtoList(userPage.getContent());
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return "Delete success!";
        }
        return "User not found!";
    }
}
