package ru.agiletech.project.service.domain.project;

import java.util.Set;

public interface ProjectRepository {

    void save(Project project);

    Project projectOfKey(Key key);

    boolean isProjectExistsWithKey(Key key);

    void deleteProject(Project project);

    Set<Project> allProjects();

}
