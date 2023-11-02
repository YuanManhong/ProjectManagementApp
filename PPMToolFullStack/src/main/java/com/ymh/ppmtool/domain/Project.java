package com.ymh.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Project name is required")
    private String projectName;


    @NotBlank(message = "Project identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;


    @NotBlank(message = "Project description is required")
    private String description;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime startDate;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime endDate;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime createAt;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime updatedAt;

    // everytime create/update the object, it stores the date
    @PrePersist
    protected void onCreate(){
        this.createAt = ZonedDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        ZonedDateTime.now();
    }

}

/*
Annotation: @PrePersist
    Purpose: To specify a callback method that should be called before an entity is inserted (persisted) into the database.
Annotation: @PreUpdate
    Purpose: To specify a callback method that should be called before an existing entity is updated in the database.
 */