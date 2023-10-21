package com.genericscheduler.differentimpl.source.event.scheduler;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.genericscheduler.differentimpl.config.GenericSchedulerProperties;
import com.genericscheduler.differentimpl.source.event.SourceEvent;
import com.genericscheduler.differentimpl.source.event.SourceEventType;
import com.genericscheduler.differentimpl.flow.FlowId;

@Component
@AllArgsConstructor
public class SchedulerSourceEventPublisher implements Runnable {

   // private GenericSchedulerProperties.Cron cron;

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void run() {
        SourceEvent<?> sourceEvent = new SourceEvent<>(
                SourceEventType.SCHEDULER,
                null//cron
        );
        applicationEventPublisher.publishEvent(sourceEvent);
    }
}
