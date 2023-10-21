package com.genericscheduler.differentimpl.pipeline.pip;

import com.genericscheduler.differentimpl.source.event.SourceEvent;

public class PipelineLifeCycleEventContext {
    private Pipeline pipeline;

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }
    public SourceEvent<?> getSourceEvent() {
        return pipeline.getSourceEvent();
    }
}
