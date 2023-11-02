package com.ymh.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException{
    private String projectIdentifier;
    public ProjectIdException(String projectIdentifier){
        super(String.format("Project ID '" + projectIdentifier.toUpperCase() + "' already exists"));
        this.projectIdentifier = projectIdentifier;
    }
    public String getProjectIdentifier(){
        return this.projectIdentifier;
    }
}
