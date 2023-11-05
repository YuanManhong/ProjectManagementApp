package com.ymh.ppmtool.repositories;

import com.ymh.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Override
    List<Project> findAllById(Iterable<Long> iterable);
    @Override
    List<Project> findAll();
    @Override
    void deleteById(Long id);

}


/*

Iterable<Long> ids:
    This is you handing over the list of toy numbers to the helper.
    You use Iterable here because it’s like saying, "I don’t care if it’s a list, a set, or any other collection of numbers, just take it and find my toys."
 */