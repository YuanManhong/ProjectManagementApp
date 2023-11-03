package com.ymh.ppmtool.services;

import com.ymh.ppmtool.domain.Project;
import com.ymh.ppmtool.exceptions.ProjectIdException;
import com.ymh.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project){
        String normalizedIdentifier = project.getProjectIdentifier().toUpperCase();
        Project existingProject = projectRepository.findByProjectIdentifier(normalizedIdentifier);
        if (existingProject == null) {
            project.setProjectIdentifier(normalizedIdentifier);
            return projectRepository.save(project);
        }
        throw new ProjectIdException(normalizedIdentifier, true);
    }

    public Project findByProjectIdentifier(String projectIdentifier){
        String normalizedIdentifier = projectIdentifier.toUpperCase();
        Project existingProject = projectRepository.findByProjectIdentifier(normalizedIdentifier);
        if (existingProject == null) {
            throw new ProjectIdException(projectIdentifier, false);  // Project ID does not exist
        }
        return projectRepository.findByProjectIdentifier(projectIdentifier);
    }

    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteByIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        if (project == null){
            throw new ProjectIdException(projectIdentifier, false);
        }
        projectRepository.delete(project);
    }

    public Project updateProjectByIdentifier(Project newProjectData, String projectIdentifier) {
        String normalizedIdentifier = projectIdentifier.toUpperCase();
        Project existingProject = projectRepository.findByProjectIdentifier(normalizedIdentifier);
        if (existingProject == null) {
            throw new ProjectIdException(projectIdentifier, false);  // Project ID does not exist
        }
        // Assuming Project has setters for all its fields.
        // Copy fields from newProjectData to existingProject.
        existingProject.setProjectName(newProjectData.getProjectName());
        existingProject.setDescription(newProjectData.getDescription());
        existingProject.setStartDate(newProjectData.getStartDate());
        existingProject.setEndDate(newProjectData.getEndDate());
        existingProject.setUpdatedAt(new Date());
        // ... set other fields ...

        return projectRepository.save(existingProject);
    }

/*    public Project updateByIdentifier(String projectIdentifier, Project project){
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
