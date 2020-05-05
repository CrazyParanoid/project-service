package ru.agiletech.project.service.domain.project;

import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import ru.agiletech.project.service.domain.supertype.AggregateRoot;
import ru.agiletech.project.service.domain.task.TaskId;
import ru.agiletech.project.service.domain.teammate.TeammateId;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Project extends AggregateRoot {

    private Date                createDate;
    private String              name;
    private String              description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value", column=@Column(name="project_key"))
    })
    private Key                 key;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="lead_id"))
    })
    private TeammateId          lead;

    @ElementCollection
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="task_id"))
    })
    private Set<TaskId>         tasks;

    @ElementCollection
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="teammate_id"))
    })
    private Set<TeammateId>     teammates;

    Project(Date                createDate,
            String              name,
            String              description,
            Key                 key,
            TeammateId          lead,
            Set<TeammateId>     teammates) {
        this.createDate     = createDate;
        this.name           = name;
        this.description    = description;
        this.key            = key;
        this.lead           = lead;
        this.teammates      = teammates;
    }

    public void addTeammate(TeammateId teammateId){
        if(CollectionUtils.isEmpty(this.teammates))
            this.teammates = new HashSet<>();

        this.teammates.add(teammateId);
    }

    public void addTask(TaskId taskId){
        if(CollectionUtils.isEmpty(this.tasks))
            this.tasks = new HashSet<>();

        this.tasks.add(taskId);
    }

    public void changeDescription(String description){
        this.description = description;
    }

    public void changeLead(TeammateId leadId){
        this.lead = leadId;
    }

    public ProjectSnapshot makeSnapshot(){
        return new ProjectSnapshot(this.tasks,
                this.teammates,
                this.lead);
    }

    public String key(){
        return this.key.getValue();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null
                || getClass() != object.getClass())
            return false;

        Project project = (Project) object;
        return Objects.equals(key, project.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

}
