/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.herbert.config;

import java.io.Serializable;
import org.springframework.stereotype.Component;

/**
 *
 * @author Herbert
 */
@Component
public class ConfigSettingCollection implements Serializable{
    
    private final String urlUsers;
    
    public ConfigSettingCollection(ConfigProperties configProp) {
        urlUsers = configProp.getUsers();
    }

    public String getUrlUsers() {
        return urlUsers;
    }
    
}
