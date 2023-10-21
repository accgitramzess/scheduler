package com.genericscheduler.event;

import java.util.Set;

import org.springframework.context.ApplicationEvent;

public interface EventFilter {

    Set<ApplicationEvent> filter(Set<ApplicationEvent> events);
}
