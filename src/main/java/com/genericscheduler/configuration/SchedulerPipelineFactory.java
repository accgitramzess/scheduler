package com.genericscheduler.configuration;

import java.util.List;

import org.springframework.stereotype.Component;

import com.genericscheduler.differentimpl.pipeline.handler.PipelineHandler;
import com.genericscheduler.differentimpl.pipeline.pip.Pipeline;
import com.genericscheduler.differentimpl.pipeline.pip.PipelineFactory;
import com.genericscheduler.differentimpl.pipeline.pip.SequentialPipeline;
import com.genericscheduler.differentimpl.source.event.SourceEventType;

@Component
public class SchedulerPipelineFactory implements PipelineFactory {

    @Override
    public SourceEventType getSourceEventType() {
        return SourceEventType.SCHEDULER;
    }

    @Override
    public Pipeline pipeline() {
        return new SequentialPipeline(handlers());
    }

    public List<PipelineHandler> handlers() {
        return List.of(aphctx -> {}, act -> {});
    }
}
