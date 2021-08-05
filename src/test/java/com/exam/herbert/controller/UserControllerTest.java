/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.herbert.controller;

import com.exam.herbert.bussiness.UserService;
import com.exam.herbert.model.response.UserResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import static junit.framework.Assert.assertEquals;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


/**
 *
 * @author Herbert
 */

@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "40000")
@AutoConfigureMockMvc

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MockMvc mvc;
    
    private static final Logger LOGGER = LogManager.getLogger();

    
    private static UserResponse userResponse;
    
    @BeforeAll
          static void setup() throws IOException{
            ObjectMapper mapper = new ObjectMapper();
		ClassLoader classLoader = UserControllerTest.class.getClassLoader();

		TypeReference<UserResponse> userResponses = new TypeReference<UserResponse>() {};

		userResponse = mapper
			.readValue(new File(classLoader.getResource("user/UserResponse.json").getFile()), 
					userResponses);
         }
         
         
        @Before
        void userTest(){
            
                userService = spy(userService);

                Mockito.when(userService.getUsers()).thenReturn(userResponse);
        }
        
        @Test
        void userListControllerOk() throws IOException, Exception{

            LOGGER.info("***** Testing userListControllerOk() *****");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/v1/getUsers")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.SC_OK, response.getStatus());
                
        }
        
}