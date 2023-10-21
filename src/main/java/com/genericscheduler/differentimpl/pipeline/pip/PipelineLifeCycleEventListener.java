package com.genericscheduler.differentimpl.pipeline.pip;

public interface PipelineLifeCycleEventListener {

    void onPipelineSelectedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent);

    void onPipelineCanNotSelectedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent);

    void onPipelineStartedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent);

    void onPipelineFinishedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent);

    void onPipelineHandlerStartedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent);

    void onPipelineHandlerFinishedEvent(PipelineLifeCycleEventContext pipelineLifeCycleEvent);
}
