package com.ymh.ppmtool.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String projectName;
    private String projectIdentifier;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date createAt;
    private Date updatedAt;

    // everytime create/update the object, it stores the date
    @PrePersist
    protected void onCreate(){
        this.createAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}

/*
Annotation: @PrePersist
    Purpose: To specify a callback method that should be called before an entity is inserted (persisted) into the database.
Annotation: @PreUpdate
    Purpose: To specify a callback method that should be called before an existing entity is updated in the database.
 */