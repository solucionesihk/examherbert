/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.herbert.bussiness.impl;

import com.exam.herbert.bussiness.UserService;
import static com.exam.herbert.constants.Constants.RESPONSE;
import com.exam.herbert.model.response.UserResponse;
import com.exam.herbert.restclient.UserRestClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Herbert
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRestClient userRestClient;
    
    @Override
    public UserResponse getUsers() {
        ObjectNode objectNode = userRestClient.getData();
        JsonNode jsonNode = objectNode.get(RESPONSE);
        ArrayNode dataUserList = ( ArrayNode ) jsonNode.get("data");
        UserResponse userResponse = new UserResponse();
        ArrayList<String> arrayList = new ArrayList<>();
        dataUserList.forEach((t)->{
            String data = t.get("id").asText() + "|" + t.get("last_name").asText() + "|" + t.get("email").asText();
            arrayList.add(data);
        });
        userResponse.setData(arrayList);
        
       return userResponse;
    }
    
}
