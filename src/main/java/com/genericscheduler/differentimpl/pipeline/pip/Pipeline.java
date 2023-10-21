package com.genericscheduler.differentimpl.pipeline.pip;

import java.util.List;

import com.genericscheduler.differentimpl.pipeline.handler.PipelineHandler;
import com.genericscheduler.differentimpl.source.event.SourceEvent;

public interface Pipeline {

    PipelineType getPipelineType();

    SourceEvent<?> getSourceEvent();

    void setSourceEvent(SourceEvent<?> sourceEvent);

    List<PipelineHandler> getPipelineHandlers();
}
