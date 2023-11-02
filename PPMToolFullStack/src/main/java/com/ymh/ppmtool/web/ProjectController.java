package com.ymh.ppmtool.web;

import com.ymh.ppmtool.domain.Project;
import com.ymh.ppmtool.exceptions.ProjectIdException;
import com.ymh.ppmtool.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project) {
        if (projectService.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()) != null){
            throw new ProjectIdException(project.getProjectIdentifier(), true);  // Project ID already exists
        }
        return new ResponseEntity<>(projectService.saveOrUpdateProject(project), HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> findByProjectIndentifier(@PathVariable String projectIdentifier) {
        Project project = projectService.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project == null) {
            throw new ProjectIdException(projectIdentifier, false);  // Project ID does not exist
        }
        return new ResponseEntity<>(projectService.findByProjectIdentifier(projectIdentifier), HttpStatus.OK);
    }

}
