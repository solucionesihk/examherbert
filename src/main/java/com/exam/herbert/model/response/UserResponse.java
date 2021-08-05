/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.herbert.model.response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Herbert
 */
public class UserResponse implements Serializable{
    
    private ArrayList<String> data;


    public UserResponse(ArrayList<String> data) {
        this.data = data;
    }
    
    public UserResponse() {
        
    }
    public ArrayList<String> getData() {
        return data;
    }
    public void setData(ArrayList<String> data) {
        this.data = data;
    }
    
}
