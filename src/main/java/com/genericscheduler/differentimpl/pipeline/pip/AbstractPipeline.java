package com.genericscheduler.differentimpl.pipeline.pip;

import java.util.List;

import com.genericscheduler.differentimpl.pipeline.handler.PipelineHandler;
import com.genericscheduler.differentimpl.source.event.SourceEvent;

public abstract class AbstractPipeline implements Pipeline {

    protected SourceEvent<?> sourceEvent;

    protected final List<PipelineHandler> handlers;

    public AbstractPipeline(List<PipelineHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public SourceEvent<?> getSourceEvent() {
        return sourceEvent;
    }

    @Override
    public void setSourceEvent(SourceEvent<?> sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    @Override
    public List<PipelineHandler> getPipelineHandlers() {
        return null;
    }
}
