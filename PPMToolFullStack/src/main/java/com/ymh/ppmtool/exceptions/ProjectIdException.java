package com.ymh.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {
    private String projectIdentifier;
    private boolean exists;

    public ProjectIdException(String projectIdentifier, boolean exists) {
        super(String.format("Project ID '%s' %s", projectIdentifier.toUpperCase(), exists ? "already exists" : "does not exist"));
        this.projectIdentifier = projectIdentifier;
        this.exists = exists;
    }

    public String getProjectIdentifier() {
        return this.projectIdentifier;
    }

    public boolean isExists() {
        return this.exists;
    }
}
