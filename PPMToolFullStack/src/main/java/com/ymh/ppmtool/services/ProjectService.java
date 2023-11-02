package com.ymh.ppmtool.services;

import com.ymh.ppmtool.domain.Project;
import com.ymh.ppmtool.exceptions.ProjectIdException;
import com.ymh.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
