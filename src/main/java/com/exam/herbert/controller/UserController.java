/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.herbert.controller;

import com.exam.herbert.bussiness.UserService;
import com.exam.herbert.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Herbert
 */

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponse getUserList(){
            return userService.getUsers();
        }
}
