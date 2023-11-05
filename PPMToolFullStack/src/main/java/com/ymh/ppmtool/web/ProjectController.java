package com.ymh.ppmtool.web;

import com.ymh.ppmtool.domain.Project;
import com.ymh.ppmtool.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project) {
        return new ResponseEntity<>(projectService.createProject(project), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.findProjectById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAllProjects(){
        return new ResponseEntity<>(projectService.findAllProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectById(@PathVariable Long id){
        projectService.deleteById(id);
        return new ResponseEntity<>("Project with ID: " + id + " was deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateProjectById(@PathVariable Long id,
                                               @RequestBody     Project project){
        Project updatedProject = projectService.updateProjectById(project, id);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }
/*




*/
/**//*

@PutMapping("/{projectIdentifier}")
public ResponseEntity<?> updateProjectByIdentifier(@Valid @RequestBody Project newProjectData, @PathVariable String projectIdentifier) {
    return new ResponseEntity<>(projectService.updateProjectByIdentifier(newProjectData, projectIdentifier), HttpStatus.OK);
}*/
}

//做到update, tested