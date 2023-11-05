package com.ymh.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {
    private Long id;
    private boolean exists;

    public ProjectIdException(Long id, boolean exists) {
        super(String.format("Project with ID: '%s' %s", id, exists ? "already exists" : "does not exist"));
        this.id = id;
        this.exists = exists;
    }

    public Long getProjectId() {
        return this.id;
    }

    public boolean isExists() {
        return this.exists;
    }
}
