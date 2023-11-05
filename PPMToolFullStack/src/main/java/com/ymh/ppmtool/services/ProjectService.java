package com.ymh.ppmtool.services;

import com.ymh.ppmtool.domain.Project;
import com.ymh.ppmtool.exceptions.ProjectIdException;
import com.ymh.ppmtool.exceptions.ProjectNameException;
import com.ymh.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public Optional<Project>  findProjectById(Long id){

        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            return existingProject;
        }
        throw new ProjectIdException(id, false);  // Project ID does not exist
    }
    public void deleteById(Long id){
        Optional<Project> project = projectRepository.findById(id);
        if (!project.isPresent()){
            throw new ProjectIdException(id, false);
        }
        projectRepository.deleteById(id);
    }

    public Project updateProjectById(Project project, Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (!optionalProject.isPresent()) {
            throw new ProjectIdException(id, false);  // Project ID does not exist
        }
        // Assuming Project has setters for all its fields.
        // Copy fields from newProjectData to existingProject.
        Project existingProject = optionalProject.get();
        existingProject.setProjectName(project.getProjectName());
        existingProject.setDescription(project.getDescription());
        existingProject.setStartDate(project.getStartDate());
        existingProject.setEndDate(project.getEndDate());
        existingProject.setUpdatedAt(new Date());
        System.out.println(existingProject.getProjectName());
        // ... set other fields ...

        return projectRepository.save(existingProject);
    }
/*






*//*    public Project updateByIdentifier(String projectIdentifier, Project project){
        Project project1 = projectRepository.findByProjectIdentifier(projectIdentifier);
        if (project1 == null){
            throw new ProjectIdException(projectIdentifier, false);
        }
        project1.setProjectName(project.getProjectName());
        project1.setDescription(project.getDescription());
        project1.setUpdatedAt(new Date());
        Project updatedProject = projectRepository.save(project1);
        return updatedProject;
    }*/
}
