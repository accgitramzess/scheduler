package com.genericscheduler.differentimpl.pipeline.registry;

import java.util.*;

import org.springframework.stereotype.Service;

import com.genericscheduler.differentimpl.source.event.SourceEventType;
import com.genericscheduler.differentimpl.source.event.SourceEvent;
import com.genericscheduler.differentimpl.pipeline.executor.PipelineExecutor;
import com.genericscheduler.differentimpl.pipeline.pip.*;

@Service
public class PipelineRegistry implements PipelineLifeCycleEventListener {

    private PipelineProvider<SourceEvent<?>> pipelineProvider;

    private Map<PipelineType, PipelineExecutor> pipelinesExecutors;

    private List<PipelineLifeCycleEventListener> listenersToAllPipeline;

    private Map<SourceEventType, PipelineLifeCycleEventListener> listenersToSpecificPipeline;

    private Map<String, Pipeline> activePipeline = new HashMap<>();

    public PipelineRegistry() {
        this.pipelinesExecutors = new HashMap<>();
    }

    public void onNewSourceEvent(SourceEvent<?> sourceEvent) {
        PipelineLifeCycleEventContext pipelineLifeCycleEvent = new PipelineLifeCycleEventContext();
        pipelineProvider.getPipeline(sourceEvent).ifPresentOrElse(pipeline -> {
            pipelineLifeCycleEvent.setPipeline(pipeline);
            onPipelineSelectedEvent(pipelineLifeCycleEvent);
            PipelineExecutor pipelineExecutor = pipelinesExecutors.get(pipeline.getPipelineType());
            pipelineExecutor.schedulePipeline(pipeline);
        }, () -> { throw new RuntimeException("Can not get Pipeline by SourceEvent = " + sourceEvent); });
    }

    @Override
    public void onPipelineSelectedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent) {
        listenersToAllPipeline.forEach(pipelineLifeCycleEventListener ->
                pipelineLifeCycleEventListener.onPipelineSelectedEvent(pipelineLifeCycleEvent));
    }

    @Override
    public void onPipelineCanNotSelectedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent) {

    }

    @Override
    public void onPipelineStartedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent) {
        listenersToAllPipeline.forEach(pipelineLifeCycleEventListener ->
                pipelineLifeCycleEventListener.onPipelineStartedEvent(pipelineLifeCycleEvent));
    }

    @Override
    public void onPipelineFinishedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent) {
        listenersToAllPipeline.forEach(pipelineLifeCycleEventListener ->
                pipelineLifeCycleEventListener.onPipelineFinishedEvent(pipelineLifeCycleEvent));
    }

    @Override
    public void onPipelineHandlerStartedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent) {
        listenersToAllPipeline.forEach(pipelineLifeCycleEventListener ->
                pipelineLifeCycleEventListener.onPipelineHandlerStartedEvent(pipelineLifeCycleEvent));
    }

    @Override
    public void onPipelineHandlerFinishedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent) {
        listenersToAllPipeline.forEach(pipelineLifeCycleEventListener ->
                pipelineLifeCycleEventListener.onPipelineHandlerFinishedEvent(pipelineLifeCycleEvent));
    }

    private void onPipelineEventToSpecificListeners(PipelineLifeCycleEventContext pipelineLifeCycleEvent) {
        SourceEventType sourceEventType = pipelineLifeCycleEvent.getSourceEvent().getSourceEventType();
        PipelineLifeCycleEventListener listeners = listenersToSpecificPipeline.get(sourceEventType);
        if (Objects.nonNull(listeners)) {
            listeners.onPipelineSelectedEvent(pipelineLifeCycleEvent);
        }
    }
}
