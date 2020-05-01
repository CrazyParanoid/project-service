package ru.agiletech.project.service.domain.task;

import lombok.*;
import ru.agiletech.project.service.domain.supertype.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskId implements ValueObject {

    private String id;

    public static TaskId identifyTaskFrom(String id){
        return new TaskId(id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null
                || getClass() != object.getClass())
            return false;

        TaskId taskId = (TaskId) object;
        return Objects.equals(id,
                taskId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
