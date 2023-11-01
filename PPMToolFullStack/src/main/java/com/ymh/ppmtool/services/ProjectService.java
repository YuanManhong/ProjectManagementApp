package com.ymh.ppmtool.services;

import com.ymh.ppmtool.domain.Project;
import com.ymh.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project){

        //logic

        return projectRepository.save(project);
    }
}
