package com.genericscheduler.differentimpl.source.event;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.genericscheduler.differentimpl.source.handler.SourceEventHandler;
import com.genericscheduler.differentimpl.pipeline.registry.PipelineRegistry;

@Slf4j
@Component
@RequiredArgsConstructor
public class SourceEventListener {

    private final Map<SourceEventType, SourceEventHandler> sourceEventHandlers;

    private final PipelineRegistry pipelineRegistry;

    @EventListener
    public void onNewSourceEvent(SourceEvent<?> sourceEvent) {
        run(sourceEvent);
    }

    @Async
    private void run(SourceEvent<?> sourceEvent) {
        pipelineRegistry.onNewSourceEvent(sourceEvent);
    }
}
