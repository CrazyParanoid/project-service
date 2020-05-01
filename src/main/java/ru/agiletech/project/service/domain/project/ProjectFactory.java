package ru.agiletech.project.service.domain.project;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.agiletech.project.service.domain.teammate.TeammateId;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProjectFactory {

    private final ProjectRepository projectRepository;

    public Project create(String        name,
                          String        description,
                          String        keyValue,
                          String        rawLeadId,
                          Set<String>   rawTeammateIds){
        Key key;

        if(StringUtils.isNotEmpty(keyValue))
            key = Key.createFromKeyValue(keyValue);
        else
            key = Key.createFromName(name);

        deduplicateProjectWithKey(key);

        TeammateId lead = TeammateId.identifyTeammateFrom(rawLeadId);
        Set<TeammateId> teammates = createTeammatesFrom(rawTeammateIds);

        return new Project(new Date(),
                name,
                description,
                key,
                lead,
                teammates);
    }

    private Set<TeammateId> createTeammatesFrom(Set<String> rawTeammateIds){
        Set<TeammateId> teammates = new HashSet<>();

        if(CollectionUtils.isNotEmpty(rawTeammateIds))
            rawTeammateIds.forEach(rawTeammateId -> teammates.add(TeammateId
                    .identifyTeammateFrom(rawTeammateId)));

        return teammates;
    }

    private void deduplicateProjectWithKey(Key key){
        if(projectRepository.isProjectExistsWithKey(key))
            throw new ProjectAlreadyExistsException(String.format("Project with key %s already exists", key.getValue()));
    }

}
