package ru.agiletech.project.service.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import ru.agiletech.project.service.domain.project.Key;
import ru.agiletech.project.service.domain.project.Project;
import ru.agiletech.project.service.domain.project.ProjectRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectDAO projectDAO;

    @Override
    public void save(Project project) {
        try{
            projectDAO.save(project);

        } catch (DataAccessException ex){
            log.error(ex.getMessage());

            throw new RepositoryAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public Project projectOfKey(Key key) {
        try{
            return projectDAO.findByKey(key)
                    .orElseThrow(() -> new ProjectNotFoundException(String.format("Project with key %s is not found", key.getValue())));

        } catch (DataAccessException ex){
            log.error(ex.getMessage());

            throw new RepositoryAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean isProjectExistsWithKey(Key key) {
        try{
            return projectDAO.existsByKey(key);

        } catch (DataAccessException ex){
            log.error(ex.getMessage());

            throw new RepositoryAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteProject(Project project) {
        try{
             projectDAO.delete(project);

        } catch (DataAccessException ex){
            log.error(ex.getMessage());

            throw new RepositoryAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public Set<Project> allProjects() {
        try{
            return new HashSet<>(projectDAO.findAll());

        } catch (DataAccessException ex){
            log.error(ex.getMessage());

            throw new RepositoryAccessException(ex.getMessage(), ex);
        }
    }

}
