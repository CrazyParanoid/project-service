package ru.agiletech.project.service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.agiletech.project.service.domain.project.Key;
import ru.agiletech.project.service.domain.project.Project;

import java.util.Optional;

public interface ProjectDAO extends JpaRepository<Project, Long> {

    Optional<Project> findByKey(Key key);

    boolean existsByKey(Key key);

}
