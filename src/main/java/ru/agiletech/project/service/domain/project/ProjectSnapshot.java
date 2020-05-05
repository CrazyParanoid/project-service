package ru.agiletech.project.service.domain.project;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import ru.agiletech.project.service.domain.supertype.ValueObject;
import ru.agiletech.project.service.domain.task.TaskId;
import ru.agiletech.project.service.domain.teammate.TeammateId;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ProjectSnapshot implements ValueObject {

    private final Set<TaskId>       tasks;
    private final Set<TeammateId>   teammates;
    private final TeammateId        lead;

    public String getLead(){
        return this.lead.getId();
    }

    public Set<String> getTeammates(){
        Set<String> teammates = new HashSet<>();

        if(CollectionUtils.isNotEmpty(this.teammates))
            this.teammates.forEach(teammateId
                    -> teammates.add(teammateId.getId()));

        return teammates;
    }

    public Set<String> getTasks(){
        Set<String> tasks = new HashSet<>();

        if(CollectionUtils.isNotEmpty(this.tasks))
            this.tasks.forEach(taskId
                    -> tasks.add(taskId.getId()));

        return tasks;
    }

}
