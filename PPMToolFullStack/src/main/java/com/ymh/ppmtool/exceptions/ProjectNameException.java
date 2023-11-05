package com.ymh.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNameException extends RuntimeException {
    private String projectName;
    private boolean exists;

    public ProjectNameException(String projectName, boolean exists) {
        super(String.format("Project named '%s' %s", projectName.toUpperCase(), exists ? "already exists" : "does not exist"));
        this.projectName = projectName;
        this.exists = exists;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public boolean isExists() {
        return this.exists;
    }
}
