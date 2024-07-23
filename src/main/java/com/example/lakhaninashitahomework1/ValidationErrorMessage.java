/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lakhaninashitahomework1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author NASHITA LAKHANI
 */
public class ValidationErrorMessage implements Serializable {
    
    private String message = "Spring Validation Error";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    private ArrayList<FieldErrorMessage> errors = new ArrayList();

    public ArrayList<FieldErrorMessage> getErrors() {
        return errors;
    }

    public void addFieldErrorMessage(FieldErrorMessage message) {
        errors.add(message);
    }
    
}
