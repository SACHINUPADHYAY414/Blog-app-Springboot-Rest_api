package com.codewithsachin.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    String resourceName; // Name of the resource not found
    String fieldName; // Name of the field in the resource
    String fieldValue; // Value of the field that caused the exception

    // Constructor to create a ResourceNotFoundException with details of the resource, field, and field value
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        // Call the constructor of the superclass (RuntimeException) with a formatted message
        // %s represents a string placeholder, %l represents a long integer placeholder
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
        
        // Initialize instance variables with the provided values
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}