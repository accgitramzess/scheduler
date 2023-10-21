package com.genericscheduler.scheduler;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.genericscheduler.differentimpl.source.scheduler.GenericSchedulerService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

import com.genericscheduler.event.EventFactory;
import com.genericscheduler.event.EventFilter;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
public class GenericScheduler {

    private  ApplicationEventPublisher applicationEventPublisher;
    private  List<EventFactory<?>> eventFactories;
    private  List<EventFilter> eventFilters;

    @Autowired
    private GenericSchedulerService genericSchedulerService;

    @Scheduled(cron = "0 * * * * *")
    public void publishEventsIfAny() {
        System.out.println("Cron!!!");
        try {
            genericSchedulerService.removeCronTask(ScheduledAnnotationBeanPostProcessor.class, "scheduledTasks");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        Instant now = Instant.now();
        for (ApplicationEvent applicationEvent : collectEvents(now)) {
            applicationEventPublisher.publishEvent(applicationEvent);
        }
    }

    private Set<ApplicationEvent> collectEvents(Instant now) {
        Set<ApplicationEvent> events = new HashSet<>();

        for (EventFactory<?> eventFactory : eventFactories) {
            eventFactory.createEventAtIfAny(now).ifPresent(events::add);
        }

        for (EventFilter eventFilter : eventFilters) {
            events = eventFilter.filter(events);
        }

        return events;
    }
}
