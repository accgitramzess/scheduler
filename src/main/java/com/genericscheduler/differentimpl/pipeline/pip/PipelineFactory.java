package com.genericscheduler.differentimpl.pipeline.pip;

import com.genericscheduler.differentimpl.source.event.SourceEventType;

public interface PipelineFactory {

    SourceEventType getSourceEventType();

    Pipeline pipeline();
}
