package com.ymh.ppmtool.web;

import com.ymh.ppmtool.domain.Project;
import com.ymh.ppmtool.exceptions.ProjectIdException;
import com.ymh.ppmtool.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project) {
        return new ResponseEntity<>(projectService.saveOrUpdateProject(project), HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> findByProjectIndentifier(@PathVariable String projectIdentifier) {
        return new ResponseEntity<>(projectService.findByProjectIdentifier(projectIdentifier), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAllProjects(){
        return new ResponseEntity<>(projectService.findAllProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier){
        projectService.deleteByIdentifier(projectIdentifier);
        return new ResponseEntity<>("Project with ID: " + projectIdentifier + " was deleted",HttpStatus.OK);
    }

/*    @PutMapping("/{projectIdentifier}")
    public ResponseEntity<?> UpdateProjectByIdentifier(@PathVariable String projectIdentifier,
                                                       Project project){
        Project updatedProject = projectService.saveOrUpdateProject(projectIdentifier, project);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }*/
@PutMapping("/{projectIdentifier}")
public ResponseEntity<?> updateProjectByIdentifier(@Valid @RequestBody Project newProjectData, @PathVariable String projectIdentifier) {
    return new ResponseEntity<>(projectService.updateProjectByIdentifier(newProjectData, projectIdentifier), HttpStatus.OK);
}
}
