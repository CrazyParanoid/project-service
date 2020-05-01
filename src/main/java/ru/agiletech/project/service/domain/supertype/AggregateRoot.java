package ru.agiletech.project.service.domain.supertype;

import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot extends IdentifiedDomainObject
        implements Entity {

    @Getter
    private List<DomainEvent> domainEvents;

    protected void registerDomainEvent(DomainEvent domainEvent){
        if(CollectionUtils.isEmpty(this.domainEvents))
            this.domainEvents = new ArrayList<>();

        this.domainEvents.add(domainEvent);
    }

}
