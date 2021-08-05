/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.herbert.restclient;

import com.exam.herbert.config.ConfigSettingCollection;
import com.exam.herbert.util.ResponseRestClientUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Herbert
 */

@Service
public class UserRestClient {
    
        @Autowired
        ResponseRestClientUtil responseRestClientUtil;
    
	@Autowired
	ConfigSettingCollection configSettingCollect;
        
        public ObjectNode getData(){
            return urlResponse( configSettingCollect.getUrlUsers() );
        }
        
    	private ObjectNode urlResponse( String url ) {
		return responseRestClientUtil.responseGet( new HttpGet( url ) );
	}
}
