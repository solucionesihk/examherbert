/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.herbert.controller.bussiness;

import com.exam.herbert.bussiness.UserService;
import com.exam.herbert.controller.UserControllerTest;
import com.exam.herbert.model.response.UserResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Herbert
 */
@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "40000")

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    
    private static final Logger LOGGER = LogManager.getLogger();
    
    private static UserResponse userResponse;
    
    @Autowired
    private UserService userService;
    
        @BeforeAll
          static void setup() throws IOException{
            ObjectMapper mapper = new ObjectMapper();
		ClassLoader classLoader = UserControllerTest.class.getClassLoader();

		TypeReference<UserResponse> userResponses = new TypeReference<UserResponse>() {};

		userResponse = mapper
			.readValue(new File(classLoader.getResource("user/UserResponse.json").getFile()), 
					userResponses);
         }
    
        @Test
        void userServiceTest() throws Exception {
            
            LOGGER.info("***** Testing userServiceTest() *****");

            ArrayList<String> arrays = new ArrayList<>();
                arrays.add("1|Bluth|george.bluth@reqres.in");
                arrays.add("2|Weaver|janet.weaver@reqres.in");
                arrays.add("3|Wong|emma.wong@reqres.in");
                arrays.add("4|Holt|eve.holt@reqres.in");
                arrays.add("5|Morris|charles.morris@reqres.in");
                arrays.add("6|Ramos|tracey.ramos@reqres.in");
            LOGGER.info("userService.getUsers():    {}", userService.getUsers().getData());
            LOGGER.info("userResponse:              {}", userResponse.getData());
            LOGGER.info("arrays:                    {}", arrays);

            assertEquals(userService.getUsers().getData(), userResponse.getData());       
        }
    
}
