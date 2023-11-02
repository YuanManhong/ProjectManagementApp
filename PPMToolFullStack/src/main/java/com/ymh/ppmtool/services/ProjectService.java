package com.ymh.ppmtool.services;

import com.ymh.ppmtool.domain.Project;
import com.ymh.ppmtool.exceptions.ProjectIdException;
import com.ymh.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        throw new ProjectIdException(normalizedIdentifier);

    }
}
