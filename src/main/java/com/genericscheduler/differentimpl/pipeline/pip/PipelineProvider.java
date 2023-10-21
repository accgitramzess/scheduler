package com.genericscheduler.differentimpl.pipeline.pip;

import java.util.Optional;

import com.genericscheduler.differentimpl.source.event.SourceEvent;

public interface PipelineProvider<PIPELINE_METADATA extends SourceEvent<?>> {

    Optional<Pipeline> getPipeline(PIPELINE_METADATA pipelineMetadata);
}
